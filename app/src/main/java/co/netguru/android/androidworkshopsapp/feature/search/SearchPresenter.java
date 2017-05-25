package co.netguru.android.androidworkshopsapp.feature.search;

import android.support.annotation.NonNull;
import android.util.Log;

import co.netguru.android.androidworkshopsapp.data.search.SearchController;
import co.netguru.android.androidworkshopsapp.data.search.model.QuestionList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    private static final String MOCKED_SEARCH_QUERY = "android";
    private static final int PAGE_SIZE = 30;
    private static final int FIRST_PAGE = 1;

    private final SearchController searchController;
    private SearchContract.View view;

    private int pageNumber = FIRST_PAGE;
    private boolean hasMore;
    private boolean isLoadingInProgress = false;

    SearchPresenter(@NonNull SearchContract.View view, SearchController searchController) {
        this.view = view;
        this.view.setPresenter(this);
        this.searchController = searchController;
    }

    @Override
    public void getFirstQuestionsPage() {
        if (!isLoadingInProgress) {
            pageNumber = FIRST_PAGE;
            view.showProgressBar();
            getQuestions();
        }
    }

    @Override
    public void getMoreQuestions() {
        if (!isLoadingInProgress && hasMore) {
            getQuestions();
        }
    }

    @Override
    public void onQuestionItemClick(String link) {
        view.showQuestionDetails(link);
    }

    @Override
    public void detachView() {
        view = null;
    }

    private void getQuestions() {
        isLoadingInProgress = true;
        searchController.getQuestionsFromServer(MOCKED_SEARCH_QUERY, pageNumber, PAGE_SIZE)
                .enqueue(new Callback<QuestionList>() {
                    @Override
                    public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                        final QuestionList questionList = response.body();
                        if (questionList != null) {
                            if (pageNumber == FIRST_PAGE) {
                                view.showData(questionList.getQuestionList());
                            } else {
                                view.addMoreData(questionList.getQuestionList());
                            }
                            view.hideProgressBar();
                            pageNumber++;
                            isLoadingInProgress = false;
                            hasMore = questionList.isHasMore();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionList> call, Throwable t) {
                        Log.e(TAG, "Error while getting questions from server", t);
                        view.showErrorMessage();
                        isLoadingInProgress = false;
                    }
                });
    }
}

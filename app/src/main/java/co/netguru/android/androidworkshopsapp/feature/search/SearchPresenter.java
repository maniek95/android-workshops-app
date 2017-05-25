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

    private final SearchController searchController;
    private SearchContract.View view;

    SearchPresenter(@NonNull SearchContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.searchController = new SearchController();
    }

    @Override
    public void getQuestions() {
        searchController.getQuestionsFromServer(MOCKED_SEARCH_QUERY).enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                if (response.body() != null) {
                    view.showData(response.body().getQuestionList());
                }
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                Log.e(TAG, "Error while getting questions from server", t);
            }
        });
    }

    @Override
    public void onQuestionItemClick(String link) {
        view.showQuestionInWebView(link);
    }

    @Override
    public void detachView() {
        view = null;
    }
}

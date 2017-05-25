package co.netguru.android.androidworkshopsapp.feature.search;

import java.util.List;

import co.netguru.android.androidworkshopsapp.data.search.model.Question;
import co.netguru.android.androidworkshopsapp.feature.common.BasePresenter;
import co.netguru.android.androidworkshopsapp.feature.common.BaseView;

interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showProgressBar();

        void hideProgressBar();

        void showData(List<Question> questionList);

        void addMoreData(List<Question> questionList);

        void showErrorMessage();

        void showQuestionDetails(String link);
    }

    interface Presenter extends BasePresenter {

        void getFirstQuestionsPage();

        void getMoreQuestions();

        void onQuestionItemClick(String link);
    }
}

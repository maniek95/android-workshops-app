package co.netguru.android.androidworkshopsapp.feature.search;

import java.util.List;

import co.netguru.android.androidworkshopsapp.data.search.model.Question;
import co.netguru.android.androidworkshopsapp.feature.common.BasePresenter;
import co.netguru.android.androidworkshopsapp.feature.common.BaseView;

interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showData(List<Question> questionList);

        void showQuestionInWebView(String link);
    }

    interface Presenter extends BasePresenter {

        void getQuestions();

        void onQuestionItemClick(String link);
    }
}

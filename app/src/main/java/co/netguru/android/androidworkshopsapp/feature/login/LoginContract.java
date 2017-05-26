package co.netguru.android.androidworkshopsapp.feature.login;

import co.netguru.android.androidworkshopsapp.feature.common.BasePresenter;
import co.netguru.android.androidworkshopsapp.feature.common.BaseView;

interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showDOauthView(String uri);
    }

    interface Presenter extends BasePresenter{

        void onLoginButtonClicked();
    }
}

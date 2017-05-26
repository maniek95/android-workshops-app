package co.netguru.android.androidworkshopsapp.feature.login;

import co.netguru.android.androidworkshopsapp.BuildConfig;
import co.netguru.android.androidworkshopsapp.data.network.model.Scope;

class LoginPresenter implements LoginContract.Presenter {

    private static final String STACKOVERFLOW_OAUTH_URI =
            "https://stackexchange.com/oauth?client_id=%s&redirect_uri=%s&scope=%s";

    private LoginContract.View view;

    LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onLoginButtonClicked() {
        view.showDOauthView(String.format(STACKOVERFLOW_OAUTH_URI, BuildConfig.STACKOVERFLOW_CLIENT_ID,
                BuildConfig.STACKOVERFLOW_OAUTH_REDIRECT, Scope.WRITE_ACCESS.getValue()));
    }
}

package co.netguru.android.androidworkshopsapp.feature.login.oauth;

import android.net.Uri;

import co.netguru.android.androidworkshopsapp.feature.common.BasePresenter;
import co.netguru.android.androidworkshopsapp.feature.common.BaseView;

public interface OauthContract {

    interface View extends BaseView<Presenter> {

        void showError();

        void showSearchActivity();
    }

    interface Presenter extends BasePresenter {
        boolean handleUri(Uri uri);
    }
}

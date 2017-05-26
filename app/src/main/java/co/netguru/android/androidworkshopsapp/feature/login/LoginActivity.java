package co.netguru.android.androidworkshopsapp.feature.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import co.netguru.android.androidworkshopsapp.R;
import co.netguru.android.androidworkshopsapp.data.common.TokenStorage;
import co.netguru.android.androidworkshopsapp.data.login.oauth.OauthController;
import co.netguru.android.androidworkshopsapp.feature.login.oauth.OauthDialogFragment;
import co.netguru.android.androidworkshopsapp.feature.login.oauth.OauthPresenter;

public class LoginActivity extends AppCompatActivity
        implements LoginFragment.OnLoginButtonClickListener {

    private LoginPresenter loginPresenter;
    private OauthPresenter oauthPresenter;


    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.login_fragment_container);

        if (fragment == null) {
            fragment = LoginFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, fragment)
                    .commit();
        }

        loginPresenter = new LoginPresenter(fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
        oauthPresenter.detachView();
    }

    @Override
    public void onLoginButtonClick(String uri) {
        final OauthDialogFragment fragment = OauthDialogFragment.newInstance(uri);
        fragment.show(getSupportFragmentManager(), OauthDialogFragment.TAG);
        oauthPresenter = new OauthPresenter(fragment, new OauthController(),
                new TokenStorage(PreferenceManager.getDefaultSharedPreferences(this)));
    }
}

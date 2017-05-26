package co.netguru.android.androidworkshopsapp.feature.login.oauth;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import co.netguru.android.androidworkshopsapp.BuildConfig;
import co.netguru.android.androidworkshopsapp.data.common.TokenStorage;
import co.netguru.android.androidworkshopsapp.data.login.oauth.OauthController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OauthPresenter implements OauthContract.Presenter {

    private static final String TAG = OauthPresenter.class.getSimpleName();

    private static final String ERROR_KEY = "error";
    private static final String CODE_KEY = "code";

    private final OauthController oauthController;
    private final TokenStorage tokenStorage;
    private OauthContract.View view;

    public OauthPresenter(OauthContract.View view, OauthController oauthController,
                          TokenStorage tokenStorage) {
        this.view = view;
        this.oauthController = oauthController;
        this.tokenStorage = tokenStorage;
        this.view.setPresenter(this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean handleUri(Uri uri) {
        if (uri.toString().contains(BuildConfig.STACKOVERFLOW_OAUTH_REDIRECT)) {
            handleRedirectUri(uri);
            return true;
        }
        return false;
    }

    private void handleRedirectUri(Uri uri) {
        final String oauthErrorMessage = uri.getQueryParameter(ERROR_KEY);
        if (oauthErrorMessage != null && oauthErrorMessage.isEmpty()) {
            view.showError();
        } else {
            handleRedirectUriWithoutError(uri);
        }
    }

    private void handleRedirectUriWithoutError(Uri uri) {
        final String codeKey = uri.getQueryParameter(CODE_KEY);
        if (!codeKey.isEmpty()) {
            requestToken(codeKey);
        } else {
            view.showError();
        }
    }

    private void requestToken(String codeKey) {
        oauthController.getToken(BuildConfig.STACKOVERFLOW_CLIENT_ID,
                BuildConfig.STACKOVERFLOW_CLIENT_SECRET,
                codeKey, BuildConfig.STACKOVERFLOW_OAUTH_REDIRECT).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                final String token = getTokenFromResponseBody(response.body());
                if (token != null) {
                    Log.d(TAG, "Storing token");
                    tokenStorage.storeToken(token);
                    view.showSearchActivity();
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error while trying to get token", t);
                view.showError();
            }
        });
    }

    @Nullable
    private String getTokenFromResponseBody(ResponseBody responseBody) {
        try {
            return responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package co.netguru.android.androidworkshopsapp.data.login.oauth;

import co.netguru.android.androidworkshopsapp.data.network.NetworkProvider;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class OauthController {

    private final OauthApi oauthApi;

    public OauthController() {
        this.oauthApi = NetworkProvider.provideLoginApi();
    }

    public Call<ResponseBody> getToken(String clientId, String clientSecret, String code, String redirectUri) {
       return oauthApi.getToken(clientId, clientSecret, code, redirectUri);
    }
}

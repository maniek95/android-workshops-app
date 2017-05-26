package co.netguru.android.androidworkshopsapp.data.login.oauth;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OauthApi {

    @FormUrlEncoded
    @POST("oauth/access_token")
    Call<ResponseBody> getToken(@Field("client_id")String clientId, @Field("client_secret") String clientSecret,
                                @Field("code")String code, @Field("redirect_uri") String redirectUri);
}

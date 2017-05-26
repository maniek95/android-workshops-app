package co.netguru.android.androidworkshopsapp.data.network;

import java.io.IOException;

import co.netguru.android.androidworkshopsapp.data.search.SearchApi;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {

    private static final String BASE_URL = "https://api.stackexchange.com/2.2/";
    private static final String SITE_KEY = "site";
    private static final String SITE_PARAM = "stackoverflow";

    public static SearchApi provideSearchApi() {
        return provideRetrofit().create(SearchApi.class);
    }

    private static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideRequestInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static Interceptor provideRequestInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request originalRequest = chain.request();
                final HttpUrl originalHttpUrl = originalRequest.url();

                final HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter(SITE_KEY, SITE_PARAM)
                        .build();

                final Request.Builder requestBuilder = originalRequest.newBuilder()
                        .url(url);

                return chain.proceed(requestBuilder.build());
            }
        };
    }
}

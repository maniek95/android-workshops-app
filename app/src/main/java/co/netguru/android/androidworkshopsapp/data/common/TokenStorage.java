package co.netguru.android.androidworkshopsapp.data.common;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class TokenStorage {

    private static final String ACCESS_TOKEN_KEY = "token_access_token";

    private final SharedPreferences sharedPreferences;

    public TokenStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void storeToken(String token) {
        sharedPreferences.edit()
                .putString(ACCESS_TOKEN_KEY, parseToken(token))
                .apply();
    }

    @Nullable
    public String getToken() {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null);
    }

    public void deleteToken() {
        sharedPreferences.edit().clear().apply();
    }

    private String parseToken(String token) {
        final int beginPos = token.indexOf("=");
        final int endPos = token.indexOf("&");
        return token.substring(beginPos + 1, endPos);
    }
}

package co.netguru.android.androidworkshopsapp.feature.login.oauth;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.netguru.android.androidworkshopsapp.R;
import co.netguru.android.androidworkshopsapp.feature.search.SearchActivity;

public class OauthDialogFragment extends DialogFragment implements OauthContract.View {

    public static final String TAG = OauthDialogFragment.class.getSimpleName();

    private static final String OAUTH_URL = "oauthUrl";

    @BindView(R.id.oauth_view)
    WebView oauthView;

    private OauthContract.Presenter presenter;

    public static OauthDialogFragment newInstance(String url) {
        final OauthDialogFragment fragment = new OauthDialogFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(OAUTH_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_oauth, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        oauthView.setWebViewClient(getWebViewClient());
        oauthView.loadUrl(getArguments().getString(OAUTH_URL));
        oauthView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void setPresenter(@NonNull OauthContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchActivity() {
        SearchActivity.startActivity(getContext());
        dismiss();
        getActivity().finish();
    }

    private WebViewClient getWebViewClient() {
        return new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                final Uri uri = Uri.parse(url);
                return handleUri(uri);
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                final Uri uri = request.getUrl();
                return handleUri(uri);
            }

            private boolean handleUri(final Uri uri) {
                return presenter.handleUri(uri);
            }
        };
    }
}

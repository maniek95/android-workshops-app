package co.netguru.android.androidworkshopsapp.feature.search.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.netguru.android.androidworkshopsapp.R;

public class SearchDetailsFragment extends DialogFragment {

    public static final String TAG = SearchDetailsFragment.class.getSimpleName();

    private static final String QUESTION_LINK = "questionLink";

    @BindView(R.id.search_details_view)
    WebView detailsView;

    public static SearchDetailsFragment newInstance(String questionLink) {
        final SearchDetailsFragment fragment = new SearchDetailsFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(QUESTION_LINK, questionLink);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
        detailsView.setWebViewClient(new WebViewClient());
        detailsView.loadUrl(getArguments().getString(QUESTION_LINK));
    }
}

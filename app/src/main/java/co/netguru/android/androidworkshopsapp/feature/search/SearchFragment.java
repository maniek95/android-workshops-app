package co.netguru.android.androidworkshopsapp.feature.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.netguru.android.androidworkshopsapp.R;

public class SearchFragment extends Fragment implements SearchContract.View {

    private SearchContract.Presenter presenter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void setPresenter(@NonNull SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

package co.netguru.android.androidworkshopsapp.feature.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.netguru.android.androidworkshopsapp.R;
import co.netguru.android.androidworkshopsapp.data.search.model.Question;
import co.netguru.android.androidworkshopsapp.feature.search.adapter.SearchAdapter;
import co.netguru.android.androidworkshopsapp.feature.search.details.SearchDetailsFragment;

public class SearchFragment extends Fragment implements SearchContract.View {

    @BindView(R.id.search_recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private SearchAdapter adapter;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initializeRecyclerView();
        presenter.getQuestions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(@NonNull SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showData(List<Question> questionList) {
        adapter.setQuestionList(questionList);
    }

    @Override
    public void showQuestionInWebView(String link) {
        SearchDetailsFragment.newInstance(link)
                .show(getFragmentManager(), SearchDetailsFragment.TAG);
    }

    private void initializeRecyclerView() {
        adapter = new SearchAdapter(new SearchAdapter.OnQuestionClickListener() {
            @Override
            public void onClick(String link) {
                presenter.onQuestionItemClick(link);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}

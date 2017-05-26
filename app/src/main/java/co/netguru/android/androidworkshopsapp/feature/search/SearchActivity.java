package co.netguru.android.androidworkshopsapp.feature.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import co.netguru.android.androidworkshopsapp.R;
import co.netguru.android.androidworkshopsapp.data.search.SearchController;

public class SearchActivity extends AppCompatActivity {

    private SearchPresenter presenter;

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchFragment fragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.search_fragment_container);

        if (fragment == null) {
            fragment = SearchFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.search_fragment_container, fragment)
                    .commit();
        }

        presenter = new SearchPresenter(fragment, new SearchController());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}

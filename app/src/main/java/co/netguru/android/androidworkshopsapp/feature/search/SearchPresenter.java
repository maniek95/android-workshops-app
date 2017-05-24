package co.netguru.android.androidworkshopsapp.feature.search;

import android.support.annotation.NonNull;

class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    SearchPresenter(@NonNull SearchContract.View view) {
        this.view = view;

        this.view.setPresenter(this);
    }

    @Override
    public void detachView() {
        view = null;
    }
}

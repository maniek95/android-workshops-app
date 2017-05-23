package co.netguru.android.androidworkshopsapp.feature.common;

import android.support.annotation.NonNull;

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);
}

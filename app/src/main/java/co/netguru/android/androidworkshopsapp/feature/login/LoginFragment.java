package co.netguru.android.androidworkshopsapp.feature.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.netguru.android.androidworkshopsapp.R;

public class LoginFragment extends Fragment implements LoginContract.View {

    private OnLoginButtonClickListener onLoginButtonClickListener;
    private LoginContract.Presenter presenter;
    private Unbinder unbinder;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onLoginButtonClickListener = (OnLoginButtonClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnLoginButtonClickListener ");
        }
    }

        @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick(R.id.login_button)
    void OnLoginButtonClick() {
        presenter.onLoginButtonClicked();
    }

    @Override
    public void showDOauthView(String uri) {
        onLoginButtonClickListener.onLoginButtonClick(uri);
    }

    interface OnLoginButtonClickListener {

        void onLoginButtonClick(String uri);
    }
}

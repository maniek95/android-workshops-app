package co.netguru.android.androidworkshopsapp.feature.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.netguru.android.androidworkshopsapp.R;
import co.netguru.android.androidworkshopsapp.data.search.model.Question;
import co.netguru.android.androidworkshopsapp.data.search.model.User;

class SearchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_search_title_text_view)
    TextView titleTextView;
    @BindView(R.id.item_search_user_photo)
    ImageView userPhotoImageView;
    @BindView(R.id.item_search_user_name)
    TextView usernameTextView;
    @BindView(R.id.item_search_views_count_text_view)
    TextView viewsCountTextView;
    @BindView(R.id.item_search_votes_count_text_view)
    TextView answerCountTextView;

    SearchViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    void bind(Question question) {
        loadUserInfo(question.getOwner());
        titleTextView.setText(question.getTitle());
        viewsCountTextView.setText(String.valueOf(question.getViewCount()));
        answerCountTextView.setText(String.valueOf(question.getAnswerCount()));

    }

    private void loadUserInfo(User user) {
        usernameTextView.setText(user.getName());
        Glide.with(itemView.getContext())
                .load(user.getProfileImage())
                .into(userPhotoImageView);
    }
}

package co.netguru.android.androidworkshopsapp.feature.search.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import co.netguru.android.androidworkshopsapp.R;
import co.netguru.android.androidworkshopsapp.data.search.model.Question;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private final List<Question> questionList;
    private final OnQuestionClickListener onQuestionClickListener;

    public SearchAdapter(@NonNull OnQuestionClickListener onQuestionClickListener) {
        this.onQuestionClickListener = onQuestionClickListener;
        questionList = new LinkedList<>();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        final SearchViewHolder viewHolder = new SearchViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuestionClickListener.onClick(questionList.get(viewHolder.getAdapterPosition())
                        .getLink());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.bind(questionList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void setQuestionList(@NonNull List<Question> questionList) {
        this.questionList.clear();
        this.questionList.addAll(questionList);
        notifyDataSetChanged();
    }

    public void addMoreData(@NonNull List<Question> questionList) {
        final int currentSize = this.questionList.size();
        this.questionList.addAll(questionList);
          notifyItemRangeInserted(currentSize, questionList.size());
    }

    public interface OnQuestionClickListener {

        void onClick(String link);
    }
}

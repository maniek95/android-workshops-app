package co.netguru.android.androidworkshopsapp.data.search.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionList {

    @SerializedName("items")
    private List<Question> questionList;
    @SerializedName("has_more")
    private boolean hasMore;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}

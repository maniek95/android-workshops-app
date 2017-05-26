package co.netguru.android.androidworkshopsapp.data.search.model;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question_id")
    private long id;
    private User owner;
    @SerializedName("is_answered")
    private boolean isAnswered;
    @SerializedName("view_count")
    private int viewCount;
    @SerializedName("bounty_amount")
    private int bountyAmount;
    @SerializedName("answer_count")
    private int answerCount;
    private String link;
    private String title;

    public long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }
}

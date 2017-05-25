package co.netguru.android.androidworkshopsapp.data.search.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    private long id;
    private int reputation;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("display_name")
    private String name;
    private String link;

    public long getId() {
        return id;
    }

    public int getReputation() {
        return reputation;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}

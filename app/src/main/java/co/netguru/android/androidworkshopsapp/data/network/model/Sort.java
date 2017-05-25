package co.netguru.android.androidworkshopsapp.data.network.model;

public enum Sort {

    ACTIVITY("activity"),
    VOTES("votes"),
    CREATION("creation"),
    RELEVANCE("relevance");

    private final String value;

    Sort(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

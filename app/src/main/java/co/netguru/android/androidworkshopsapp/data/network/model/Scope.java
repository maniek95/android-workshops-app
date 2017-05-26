package co.netguru.android.androidworkshopsapp.data.network.model;

public enum Scope {

    READ_INBOX("read_inbox"),
    NO_EXPIRY("no_expiry"),
    WRITE_ACCESS("write_access"),
    PRIVATE_INFO("private_info");

    private final String value;

    Scope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

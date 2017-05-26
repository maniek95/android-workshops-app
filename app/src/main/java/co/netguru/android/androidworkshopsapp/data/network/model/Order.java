package co.netguru.android.androidworkshopsapp.data.network.model;

public enum Order {

    DESC("desc"),
    ASC("asc");

    private final String value;

    Order(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

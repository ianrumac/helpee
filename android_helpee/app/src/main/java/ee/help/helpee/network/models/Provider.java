package ee.help.helpee.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ian on 19/04/15.
 */
public class Provider {

    public static final String PROVIDER_URL = "PROVIDER_URL";

    @SerializedName("Name")
    String name;
    @SerializedName("Url")
    String url;

    @SerializedName("State")
    String state;

    public Provider() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

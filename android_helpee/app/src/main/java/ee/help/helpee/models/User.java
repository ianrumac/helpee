package ee.help.helpee.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ian on 12/04/15.
 */
public class User {

    int id;

    @SerializedName("userName")
    String fullName;

    @SerializedName("access_token")
    String token;

    @SerializedName("model.token")
    String fbToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }
}

package ee.help.helpee.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ian on 12/04/15.
 */
public class User implements Serializable {


    @SerializedName("Name")
    String fullName;

    @SerializedName("userid")
    String userId;

    @SerializedName("access_token")
    String token;

    @SerializedName("model.token")
    String fbToken;

    @SerializedName("email")
    String email;

    @SerializedName("ImageUri")
    String imageUri;

    @SerializedName("Points")

    int points;

    String userCity;

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUri() {

        if (imageUri != null)
            return "http://helpee.azurewebsites.net/Images/".concat(imageUri);
        else
            return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

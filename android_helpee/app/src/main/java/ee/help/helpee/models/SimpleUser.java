package ee.help.helpee.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by infinum on 01/05/15.
 */
public class SimpleUser {

    @SerializedName("Email")
    String email;
    @SerializedName("Password")
    String password;
    @SerializedName("ConfirmPassword")
    String confirmedPassword;

    @SerializedName("Name")
    String name;

    public SimpleUser(String email, String password, String confirmedPassword, String name) {
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.name = name;
    }
}

package ee.help.helpee.network;

import ee.help.helpee.models.User;
import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by ian on 17/3/14.
 */
public interface ApiService {


    @POST("/Account/FacebookLogin")
    void postAccountInfo(@Query("access_token") String accessToken, Callback<User> userResponseCallback);

}

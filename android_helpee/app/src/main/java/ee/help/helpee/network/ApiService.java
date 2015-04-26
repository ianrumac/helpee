package ee.help.helpee.network;

import ee.help.helpee.models.User;
import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by ian on 17/3/14.
 */
public interface ApiService {


    @POST("/Account/FacebookLogin")
    void postAccountInfo(@Query("access_token") String accessToken, Callback<User> userResponseCallback);

    @Multipart
    @POST("/upload")
    void upload(@Part("myfile") TypedFile file,
            @Part("description") String description,
            Callback<String> cb);

}

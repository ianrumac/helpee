package ee.help.helpee.network;

import java.util.List;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.SimpleEvent;
import ee.help.helpee.models.SimpleUser;
import ee.help.helpee.models.User;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
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

    @POST("/Events/CreateEvent")
    void postEvent(@Body SimpleEvent event,@Header("Authorization") String token, Callback<Response> simpleCallback);

    @POST("/Account/Register")
    void registerUster(@Body SimpleUser user, Callback<User> userRegisteredCallback);

    @GET("/Events/GetEventsByCity")
    void getEventsByCity(@Query("city") String city, @Query("userid") String userId, @Header("Authorization") String token, Callback<List<Event>> eventList);
}

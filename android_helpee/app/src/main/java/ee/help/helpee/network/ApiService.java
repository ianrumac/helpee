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

    /**
     * Login user via facebook using fbAccessToken and parseDeviceId
     * */

    @POST("/Account/FacebookLogin")
    void postAccountInfo(@Query("access_token") String accessToken, @Query("deviceid") String deviceId, Callback<User> userLoginCallback);

    /**
     * Login user via username and password
     * */

    @POST("/Account/SignIn")
    void userSignIn(@Query("username") String username, @Query("password") String password, Callback<User> userLoginCallback);

    /**
     * Upload image
     * */

    @Multipart
    @POST("/Account/ImageUpload")
    void uploadUserImage(@Part("myfile") TypedFile file,
                         @Query("userid") String userId,
                         @Header("Authorization") String token,
                         Callback<String> urlCallback);

    /**
     * Create event
     * */

    @POST("/Events/CreateEvent")
    void postEvent(@Body SimpleEvent event, @Header("Authorization") String token, Callback<Response> simpleCallback);

    /**
     * Register user via username and password
     * */

    @POST("/Account/Register")
    void registerUser(@Body SimpleUser user, Callback<User> userRegisteredCallback);

    /**
     * Get events by city, except user created events
     * */

    @GET("/Events/GetEventsByCity")
    void getEventsByCity(@Query("city") String city, @Query("userid") String userId, @Header("Authorization") String token, Callback<List<Event>> eventList);


    /**
    * Get events by user - used to fetch current user events
    * */
    @GET("/Events/GetEventsByUser")
    void getEventsByUser(@Query("userid") String userId, @Header("Authorization") String token, Callback<List<Event>> eventsList);

    /**
     * Get events where user is helping
     * */

    @GET("/Events/WhereIHelp")
    void getEventsWhereUserHelps(@Query("userid") String userId, @Header("Authorization") String token, Callback<List<Event>> eventsList);

    /**
     * Get top 10 helpees
    * */
    @GET("/Events/TopHelpees")
    void getTopHelpees(@Header("Authorization") String token, Callback<List<User>> heroesCallback);

    @POST("/Events/IntoTheFuckingTrash")
    void cancelEvent(@Query("eventid") int eventId, @Header("Authorization") String token, Callback<Response> callback );

    @POST("/Events/BeAHelpee")
    void joinEvent(@Query("eventid") int eventid, @Query("userid") String userId, @Header("Authorization") String token, Callback<Response> callback );

    @POST("/Events/CantHelp")
    void cancelHelp(@Query("eventid") int eventId,@Query("userid") String userId, @Header("Authorization") String token, Callback<Response> callback );

    @POST("/Events/HelpCompleted")
    void completeEvent(@Query("eventid") int eventId, @Header("Authorization") String token, Callback<Response> callback);

    @POST("/Events/HelpCompleted")
    void failedEvent(@Query("eventid") int eventId, @Header("Authorization") String token, Callback<Response> callback);

    @POST("/Events/ChipIn")
    void chipIn(@Query("eventid") int eventId, @Query("points") int points, @Query("userid") String userId, @Header("Authorization") String token, Callback<Response> callback );


}


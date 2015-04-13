package ee.help.helpee.network;

import co.infinum.azerfon.network.models.Article;
import co.infinum.azerfon.network.models.AuthorizationCode;
import co.infinum.azerfon.network.models.BasePaginatedResponse;
import co.infinum.azerfon.network.models.BaseResponseSingle;
import co.infinum.azerfon.network.models.LoginObject;
import co.infinum.azerfon.network.models.TokenNumberCodeCombo;
import co.infinum.azerfon.network.models.TokenNumberCombo;
import co.infinum.azerfon.network.models.User;
import co.infinum.azerfon.network.models.Venue;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ian on 17/3/14.
 */
public interface ApiService {


    @POST("/api/1/users/login")
    void loginUser(@Body LoginObject volounteerCode, Callback<BaseResponseSingle<User>> userCallback);

    @POST("/api/1/users/authorization_code")
    void getAuthorizationCode(@Body TokenNumberCombo phoneNumber, Callback<BaseResponseSingle<AuthorizationCode>> codeCallback);


    @POST("/api/1/users/authorize")
    void checkIfCodeIsValid(@Body TokenNumberCodeCombo combo, Callback<BaseResponseSingle<String>> callback);

    @GET("/api/1/venues")
    void getVenues(@Query("token") String token, @Query("page") int page, @Query("timestamp") long timestamp,
            Callback<BasePaginatedResponse<Venue>> venuesCallback);

    @GET("/api/1/articles")
    void getAllArticles(@Query("token") String token, @Query("page") int page, @Query("timestamp") long timestamp,
            Callback<BasePaginatedResponse<Article>> articlesCallback);

    @GET("/api/1/quiz/questions")
    void getAllQuizQuestions(@Query("token") String token, @Query("page") int page);

    @GET("/api/1/quiz/questions/{id}?token={token}")
    void getSingleQuestion(@Path("id") int id, @Path("token") String token);


}

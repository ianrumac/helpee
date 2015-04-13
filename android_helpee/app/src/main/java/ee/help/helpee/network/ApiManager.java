package ee.help.helpee.network;

/**
 * Created by ian on 25/11/14.
 */

import com.squareup.okhttp.OkHttpClient;

import ee.help.helpee.BuildConfig;
import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


public class ApiManager {

    /**
     * Static instance of adapter.
     */

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private static final OkClient okClient = new OkClient(okHttpClient);

    private final Endpoint publicEndpoint;

    RestAdapter.LogLevel currentLogLevel;

    private static RestAdapter baseRestAdapter;


    private final ApiService apiService;

    public ApiManager(Endpoint publicEndpoint) {
        this.publicEndpoint = publicEndpoint;
        apiService = createBaseService(publicEndpoint);

    }

    public ApiService createBaseService(Endpoint endpoint) {


        if (BuildConfig.DEBUG) {
            currentLogLevel = RestAdapter.LogLevel.FULL;
        } else {
            currentLogLevel = RestAdapter.LogLevel.NONE;
        }
        baseRestAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_ENDPOINT)
                .setClient(okClient)
                .setLogLevel(currentLogLevel)
                .build();
        return baseRestAdapter.create(ApiService.class);

    }


    public RestAdapter getDefaultRestAdapter() {

        return baseRestAdapter;
    }

    public RestAdapter.LogLevel getCurrentLogLevel() {
        return currentLogLevel;
    }

    public void setCurrentLogLevel(RestAdapter.LogLevel currentLogLevel) {
        this.currentLogLevel = currentLogLevel;
    }

    public static OkClient getOkClient() {
        return okClient;
    }

    public ApiService getApiService() {
        return apiService;
    }
}

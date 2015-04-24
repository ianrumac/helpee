package ee.help.helpee.network.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.ApiService;
import retrofit.Endpoint;

/**
 * Created by Ian on 26/03/15.
 */
@Module
public class ApiModule {


    private ApiManager apiManager;

    @Provides
    public ApiManager provideApiManager(@Named(value="publicEndpoint")Endpoint publicEndpoint){
        if(apiManager == null){
            apiManager = new ApiManager(publicEndpoint);

        }
        return apiManager;
    }

    @Provides
    public ApiService provideApiService(ApiManager apiManager){
        return apiManager.getApiService();
    }

}

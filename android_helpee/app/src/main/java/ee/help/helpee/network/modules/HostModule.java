package ee.help.helpee.network.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.custom.Constants;
import retrofit.Endpoint;
import retrofit.Endpoints;

/**
 * Created by Jerry on 26/03/15.
 */
@Module
public class HostModule {

    @Provides
    @Named(value = "publicEndpoint")
    public Endpoint provudePublicEndpoint(){
        return Endpoints.newFixedEndpoint(Constants.API_ENDPOINT);
    }


}

package ee.help.helpee.network.modules;

import javax.inject.Named;

import co.infinum.azerfon.custom.Constants;
import dagger.Module;
import dagger.Provides;
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

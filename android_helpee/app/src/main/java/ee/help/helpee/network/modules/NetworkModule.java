package ee.help.helpee.network.modules;

import dagger.Module;

/**
 * Created by Jerry on 26/03/15.
 */
@Module(includes = {
        ApiModule.class,
        HostModule.class
})
public class NetworkModule {

}

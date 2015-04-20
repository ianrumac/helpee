package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.activities.LoginActivity;
import ee.help.helpee.dagger.LoginModule;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {LoginModule.class,
        NetworkModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}

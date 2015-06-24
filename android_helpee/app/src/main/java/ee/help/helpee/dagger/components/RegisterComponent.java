package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.activities.LoginActivity;
import ee.help.helpee.dagger.LoginModule;
import ee.help.helpee.dagger.RegisterModule;
import ee.help.helpee.fragments.RegisterFragment;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {RegisterModule.class,
        NetworkModule.class})
public interface RegisterComponent {

    void inject(RegisterFragment fragment);
}

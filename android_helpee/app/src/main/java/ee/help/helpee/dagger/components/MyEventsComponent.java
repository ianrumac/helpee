package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.activities.LoginActivity;
import ee.help.helpee.dagger.LoginModule;
import ee.help.helpee.dagger.MyEventsModule;
import ee.help.helpee.fragments.MyEventsFragment;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {MyEventsModule.class,
        NetworkModule.class})
public interface MyEventsComponent {

    void inject(MyEventsFragment fragment);
}

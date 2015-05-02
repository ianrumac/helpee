package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.dagger.MyEventsModule;
import ee.help.helpee.dagger.UserEventsModule;
import ee.help.helpee.fragments.CurrentUserEventsFragment;
import ee.help.helpee.fragments.MyEventsFragment;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {UserEventsModule.class,
        NetworkModule.class})
public interface UserEventsComponent {

    void inject(CurrentUserEventsFragment fragment);
}

package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.dagger.EventFeedModule;
import ee.help.helpee.fragments.EventFeedFragment;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {EventFeedModule.class,
        NetworkModule.class})
public interface EventFeedComponent {

    void inject(EventFeedFragment fragment);
}

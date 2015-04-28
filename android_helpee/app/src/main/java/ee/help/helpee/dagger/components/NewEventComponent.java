package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.dagger.NewEventModule;
import ee.help.helpee.activities.NewEventActivity;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {NewEventModule.class,
        NetworkModule.class})
public interface NewEventComponent {

    void inject(NewEventActivity fragment);
}

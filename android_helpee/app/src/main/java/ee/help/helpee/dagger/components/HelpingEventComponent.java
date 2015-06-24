package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.dagger.HelpingEventsModule;
import ee.help.helpee.fragments.HelpingEventsFragment;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {HelpingEventsModule.class,
        NetworkModule.class})
public interface HelpingEventComponent {

    void inject(HelpingEventsFragment fragment);
}

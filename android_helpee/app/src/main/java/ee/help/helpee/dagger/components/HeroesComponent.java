package ee.help.helpee.dagger.components;

import dagger.Component;
import ee.help.helpee.dagger.HeroesModule;
import ee.help.helpee.dagger.RegisterModule;
import ee.help.helpee.fragments.HeroesFragment;
import ee.help.helpee.fragments.RegisterFragment;
import ee.help.helpee.network.modules.NetworkModule;

/**
 * Created by ian on 19/04/15.
 */

@Component(modules = {HeroesModule.class,
        NetworkModule.class})
public interface HeroesComponent {

    void inject(HeroesFragment fragment);
}

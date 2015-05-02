package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.HeroesInteractor;
import ee.help.helpee.mvp.interactors.impl.HeroesInteractorImpl;
import ee.help.helpee.mvp.presenters.HeroesPresenter;
import ee.help.helpee.mvp.presenters.impl.HeroesPresenterImpl;
import ee.help.helpee.mvp.views.HeroesView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class HeroesModule {


    private HeroesView heroesView;

    public HeroesModule(HeroesView heroesView) {
        this.heroesView = heroesView;
    }

    @Provides
    public HeroesView provideHeroesView() {
        return heroesView;
    }

    @Provides
    public HeroesPresenter provideHeroesPresenter(HeroesPresenterImpl heroesPresenter) {
        return heroesPresenter;
    }

    ;

    @Provides
    public HeroesInteractor provideHeroesInteractor(ApiManager apiManager) {
        return new HeroesInteractorImpl(apiManager);
    }

}

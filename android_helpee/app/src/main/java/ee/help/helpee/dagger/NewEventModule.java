package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.NewEventInteractor;
import ee.help.helpee.mvp.interactors.impl.NewEventInteractorImpl;
import ee.help.helpee.mvp.presenters.NewEventPresenter;
import ee.help.helpee.mvp.presenters.impl.NewEventPresenterImpl;
import ee.help.helpee.mvp.views.NewEventView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class NewEventModule {


    private NewEventView loginView;

    public NewEventModule(NewEventView loginView) {
        this.loginView = loginView;
    }

    @Provides
    public NewEventView provideNewEventView() {
        return loginView;
    }

    @Provides
    public NewEventPresenter provideNewEventPresenter(NewEventPresenterImpl loginPresenter) {
        return loginPresenter;
    }

    @Provides
    public NewEventInteractor provideNewEventInteractor(ApiManager apiManager) {
        return new NewEventInteractorImpl(apiManager);
    }

}

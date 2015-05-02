package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.UserEventsInteractor;
import ee.help.helpee.mvp.interactors.impl.UserEventsInteractorImpl;
import ee.help.helpee.mvp.presenters.UserEventsPresenter;
import ee.help.helpee.mvp.presenters.impl.UserEventsPresenterImpl;
import ee.help.helpee.mvp.views.UserEventsView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class UserEventsModule {


    private UserEventsView userEventsView;

    public UserEventsModule(UserEventsView userEventsView) {
        this.userEventsView = userEventsView;
    }

    @Provides
    public UserEventsView provideUserEventsView() {
        return userEventsView;
    }

    @Provides
    public UserEventsPresenter provideUserEventsPresenter(UserEventsPresenterImpl userEventsPresenter) {
        return userEventsPresenter;
    }

    ;

    @Provides
    public UserEventsInteractor provideUserEventsInteractor(ApiManager apiManager) {
        return new UserEventsInteractorImpl(apiManager);
    }

}

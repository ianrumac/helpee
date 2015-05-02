package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.EventFeedInteractor;
import ee.help.helpee.mvp.interactors.impl.EventFeedInteractorImpl;
import ee.help.helpee.mvp.presenters.EventFeedPresenter;
import ee.help.helpee.mvp.presenters.impl.EventFeedPresenterImpl;
import ee.help.helpee.mvp.views.EventFeedView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class EventFeedModule {


    private EventFeedView feedView;

    public EventFeedModule(EventFeedView feedView) {
        this.feedView = feedView;
    }

    @Provides
    public EventFeedView provideEventFeedView() {
        return feedView;
    }

    @Provides
    public EventFeedPresenter provideEventFeedPresenter(EventFeedPresenterImpl eventFeedPresenter) {
        return eventFeedPresenter;
    }

    @Provides
    public EventFeedInteractor provideEventFeedInteractor(ApiManager apiManager) {
        return new EventFeedInteractorImpl(apiManager);
    }

}

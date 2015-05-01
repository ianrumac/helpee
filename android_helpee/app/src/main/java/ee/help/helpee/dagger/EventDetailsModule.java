package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.EventDetailsInteractor;
import ee.help.helpee.mvp.interactors.impl.EventDetailsInteractorImpl;
import ee.help.helpee.mvp.presenters.EventDetailsPresenter;
import ee.help.helpee.mvp.presenters.impl.EventDetailsPresenterImpl;
import ee.help.helpee.mvp.views.EventDetailsView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class EventDetailsModule {


    private EventDetailsView detailsView;

    public EventDetailsModule(EventDetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Provides
    public EventDetailsView provideEventDetailsView() {
        return detailsView;
    }

    @Provides
    public EventDetailsPresenter provideEventDetailsPresenter(EventDetailsPresenterImpl detailsPresenter) {
        return detailsPresenter;
    }

    @Provides
    public EventDetailsInteractor provideEventDetailsInteractor(ApiManager apiManager) {
        return new EventDetailsInteractorImpl(apiManager);
    }

}

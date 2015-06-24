package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.HelpingEventsInteractor;
import ee.help.helpee.mvp.interactors.impl.HelpingEventsInteractorImpl;
import ee.help.helpee.mvp.presenters.HelpingEventsPresenter;
import ee.help.helpee.mvp.presenters.impl.HelpingEventsPresenterImpl;
import ee.help.helpee.mvp.views.HelpingEventsView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class HelpingEventsModule {


    private HelpingEventsView helpingEventsView;

    public HelpingEventsModule(HelpingEventsView helpingEventsView) {
        this.helpingEventsView = helpingEventsView;
    }

    @Provides
    public HelpingEventsView provideHelpingEventsView() {
        return helpingEventsView;
    }

    @Provides
    public HelpingEventsPresenter provideHelpingEventsPresenter(HelpingEventsPresenterImpl helpingEventsPresenter) {
        return helpingEventsPresenter;
    }

    ;

    @Provides
    public HelpingEventsInteractor provideHelpingEventsInteractor(ApiManager apiManager) {
        return new HelpingEventsInteractorImpl(apiManager);
    }

}

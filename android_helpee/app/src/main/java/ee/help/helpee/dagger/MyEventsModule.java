package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.MyEventsInteractor;
import ee.help.helpee.mvp.interactors.impl.MyEventsInteractorImpl;
import ee.help.helpee.mvp.presenters.MyEventsPresenter;
import ee.help.helpee.mvp.presenters.impl.MyEventsPresenterImpl;
import ee.help.helpee.mvp.views.MyEventsView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class MyEventsModule {


    private MyEventsView myEventsView;

    public MyEventsModule(MyEventsView myEventsView) {
        this.myEventsView = myEventsView;
    }

    @Provides
    public MyEventsView provideMyEventsView() {
        return myEventsView;
    }

    @Provides
    public MyEventsPresenter provideMyEventsPresenter(MyEventsPresenterImpl myEventsPresenter) {
        return myEventsPresenter;
    }

    ;

    @Provides
    public MyEventsInteractor provideMyEventsInteractor(ApiManager apiManager) {
        return new MyEventsInteractorImpl(apiManager);
    }

}

package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.RegisterInteractor;
import ee.help.helpee.mvp.interactors.impl.RegisterInteractorImpl;
import ee.help.helpee.mvp.presenters.RegisterPresenter;
import ee.help.helpee.mvp.presenters.impl.RegisterPresenterImpl;
import ee.help.helpee.mvp.views.RegisterView;
import ee.help.helpee.mvp.views.RegisterView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class RegisterModule {


    private RegisterView registerView;

    public RegisterModule(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Provides
    public RegisterView provideRegisterView() {
        return registerView;
    }

    @Provides
    public RegisterPresenter provideRegisterPresenter(RegisterPresenterImpl registerPresenter) {
        return registerPresenter;
    }

    ;

    @Provides
    public RegisterInteractor provideRegisterInteractor(ApiManager apiManager) {
        return new RegisterInteractorImpl(apiManager);
    }

}

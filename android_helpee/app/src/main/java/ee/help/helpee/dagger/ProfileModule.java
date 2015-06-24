package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.LoginInteractor;
import ee.help.helpee.mvp.interactors.impl.LoginInteractorImpl;
import ee.help.helpee.mvp.presenters.LoginPresenter;
import ee.help.helpee.mvp.presenters.impl.LoginPresenterImpl;
import ee.help.helpee.mvp.views.LoginView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class ProfileModule {


    private LoginView loginView;

    public ProfileModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    public LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(LoginPresenterImpl loginPresenter) {
        return loginPresenter;
    }

    ;

    @Provides
    public LoginInteractor provideLoginInteractor(ApiManager apiManager) {
        return new LoginInteractorImpl(apiManager);
    }

}

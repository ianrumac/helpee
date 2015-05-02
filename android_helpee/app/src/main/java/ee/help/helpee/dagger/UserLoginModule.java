package ee.help.helpee.dagger;

import dagger.Module;
import dagger.Provides;
import ee.help.helpee.mvp.interactors.UserLoginInteractor;
import ee.help.helpee.mvp.interactors.impl.UserLoginInteractorImpl;
import ee.help.helpee.mvp.presenters.UserLoginPresenter;
import ee.help.helpee.mvp.presenters.impl.UserLoginPresenterImpl;
import ee.help.helpee.mvp.views.LoginView;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
@Module
public class UserLoginModule {


    private LoginView userLoginView;

    public UserLoginModule(LoginView userLoginView) {
        this.userLoginView = userLoginView;
    }

    @Provides
    public LoginView provideUserLoginView() {
        return userLoginView;
    }

    @Provides
    public UserLoginPresenter provideUserLoginPresenter(UserLoginPresenterImpl userLoginPresenter) {
        return userLoginPresenter;
    }



    @Provides
    public UserLoginInteractor provideUserLoginInteractor(ApiManager apiManager) {
        return new UserLoginInteractorImpl(apiManager);
    }

}

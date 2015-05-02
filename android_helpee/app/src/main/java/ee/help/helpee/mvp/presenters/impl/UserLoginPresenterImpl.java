package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.UserLoginInteractor;
import ee.help.helpee.mvp.presenters.UserLoginPresenter;
import ee.help.helpee.mvp.views.LoginView;

/**
 * Created by infinum on 28/04/15.
 */
public class UserLoginPresenterImpl implements UserLoginPresenter{

    UserLoginInteractor loginInteractor;

    LoginView loginView;

    @Inject
    public UserLoginPresenterImpl(UserLoginInteractor loginInteractor, LoginView loginView) {
        this.loginInteractor = loginInteractor;
        this.loginView = loginView;
    }

    @Override
    public void loginUser(String username, String password) {
        loginView.showProgress();
        loginInteractor.loginUser(username, password, new BaseListener<User>() {
            @Override
            public void onSuccess(User success) {
                loginView.hideProgress();
                loginView.userLoggedIn(success);
            }

            @Override
            public void onFail(ErrorType errorType) {
                loginView.hideProgress();
                loginView.showError(errorType);
            }
        });
    }
}

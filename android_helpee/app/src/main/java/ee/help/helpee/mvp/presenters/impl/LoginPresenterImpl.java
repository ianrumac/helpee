package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.LoginInteractor;
import ee.help.helpee.mvp.presenters.LoginPresenter;
import ee.help.helpee.mvp.views.LoginView;

/**
 * Created by ian on 19/04/15.
 */
public class LoginPresenterImpl implements LoginPresenter {


    LoginView loginView;

    LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void loginUserWithFacebookToken(String token) {
        loginView.showProgress();
        loginInteractor.receiveUserInfo(token, userListener);
    }

    @Override
    public void saveUser(User user) {
        loginInteractor.saveUserToPreferences(user);
    }

    BaseListener<User> userListener = new BaseListener<User>() {
        @Override
        public void onSuccess(User result) {
            loginView.hideProgress();
            loginView.userLoggedIn(result);

        }

        @Override
        public void onFail(ErrorType errorType) {
            loginView.hideProgress();
            loginView.showError(errorType);
        }
    };
}

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
    public void loginUserWithFacebookToken(String token,String deviceId) {
        loginView.showProgress();
        loginInteractor.receiveUserInfo(token, deviceId, userListener);
    }

    @Override
    public void saveUser(User user) {
        loginInteractor.saveUserToPreferences(user);
    }

    BaseListener<User> userListener = new BaseListener<User>() {
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
    };
}

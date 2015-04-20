package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.mvp.interactors.LoginInteractor;
import ee.help.helpee.mvp.presenters.LoginPresenter;
import ee.help.helpee.mvp.views.LoginView;

/**
 * Created by ian on 19/04/15.
 */
public class LoginPresenterImpl implements LoginPresenter{


    LoginView loginView;

    LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

}

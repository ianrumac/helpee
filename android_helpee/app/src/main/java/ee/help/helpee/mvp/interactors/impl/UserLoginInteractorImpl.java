package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.UserLoginInteractor;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 26/04/15.
 */
public class UserLoginInteractorImpl implements UserLoginInteractor {

    ApiManager apiManager;

    @Inject
    public UserLoginInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void loginUser(String username, String password, BaseListener<User> userBaseListener) {

    }
}

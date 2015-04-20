package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.mvp.interactors.LoginInteractor;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 19/04/15.
 */
public class LoginInteractorImpl implements LoginInteractor {

    ApiManager apiManager;

    @Inject
    public LoginInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }
}


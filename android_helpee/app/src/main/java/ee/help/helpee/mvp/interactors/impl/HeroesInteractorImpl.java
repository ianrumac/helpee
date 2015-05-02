package ee.help.helpee.mvp.interactors.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.HeroesInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by infinum on 02/05/15.
 */
public class HeroesInteractorImpl implements HeroesInteractor {

    ApiManager apiManager;

    @Inject
    public HeroesInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void getHeroes(String token, final BaseListener<List<User>> heroesListener) {
        apiManager.getApiService().getTopHelpees(BearerToken.authorize(token), new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                heroesListener.onSuccess(users);
            }

            @Override
            public void failure(RetrofitError error) {
                heroesListener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });
    }
}

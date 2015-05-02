package ee.help.helpee.mvp.interactors.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.MyEventsInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by infinum on 28/04/15.
 */
public class MyEventsInteractorImpl implements MyEventsInteractor {


    ApiManager apiManager;

    @Inject
    public MyEventsInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

}

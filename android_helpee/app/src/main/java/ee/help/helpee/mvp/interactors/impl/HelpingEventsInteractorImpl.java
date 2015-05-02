package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.mvp.interactors.HelpingEventsInteractor;
import ee.help.helpee.mvp.interactors.UserEventsInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by infinum on 02/05/15.
 */
public class HelpingEventsInteractorImpl implements HelpingEventsInteractor{

    ApiManager apiManager;

    @Inject
    public HelpingEventsInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }


    @Override
    public void cancelHelp(int eventId, String userId, String token, final SimpleBaseListener listener) {
        apiManager.getApiService().cancelHelp(eventId, userId, BearerToken.authorize(token), new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                listener.onSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });
    }
}

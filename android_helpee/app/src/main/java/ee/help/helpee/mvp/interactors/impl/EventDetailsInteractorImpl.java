package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.mvp.interactors.EventDetailsInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by infinum on 01/05/15.
 */
public class EventDetailsInteractorImpl implements EventDetailsInteractor {


    ApiManager apiManager;

    SimpleBaseListener listener;
    @Inject
    public EventDetailsInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void sendPoints(int eventId, int points, String userId, String token, SimpleBaseListener successListener) {

    }

    @Override
    public void sendHelp(int eventId, String userId, String token, final SimpleBaseListener successListener) {

        this.listener = successListener;
        apiManager.getApiService().joinEvent(eventId, userId, BearerToken.authorize(token), basicResponseCallback);
    }

    @Override
    public void cancelHelp(int eventId, String userId, String token, final SimpleBaseListener listener) {
        this.listener = listener;
        apiManager.getApiService().cancelHelp(eventId, userId, BearerToken.authorize(token),basicResponseCallback);
    }

    @Override
    public void completeEvent(int eventId, String token, final SimpleBaseListener listener) {
        this.listener = listener;
        apiManager.getApiService().completeEvent(eventId, BearerToken.authorize(token),  basicResponseCallback);
    }

    @Override
    public void failedEvent(int eventId, String token, SimpleBaseListener listener) {
        this.listener = listener;
            apiManager.getApiService().failedEvent(eventId, BearerToken.authorize(token), basicResponseCallback);
    }

    @Override
    public void cancelEvent(int eventId, String token, final SimpleBaseListener listener) {
        this.listener = listener;
        apiManager.getApiService().cancelEvent(eventId, BearerToken.authorize(token), basicResponseCallback);
    }


    Callback<Response> basicResponseCallback = new Callback<Response>() {
        @Override
        public void success(Response response, Response response2) {
            listener.onSuccess();
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onFail(ErrorType.CONNECTION_ERROR);
        }
    };
}

package ee.help.helpee.mvp.interactors.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.EventFeedInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ian on 21/04/15.
 */
public class EventFeedInteractorImpl implements EventFeedInteractor {



    ApiManager apiManager;

    @Inject
    public EventFeedInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void fetchEvents(String city, String userId, String token, final BaseListener<List<Event>> eventsListener) {


        apiManager.getApiService().getEventsByCity(city, userId, BearerToken.authorize(token), new Callback<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                eventsListener.onSuccess(events);
            }

            @Override
            public void failure(RetrofitError error) {
                eventsListener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });

    }

    @Override
    public void joinEvent(int eventId, String userId, String token, final SimpleBaseListener listener) {
            apiManager.getApiService().joinEvent(eventId, userId, BearerToken.authorize(token), new Callback<Response>() {
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

package ee.help.helpee.mvp.interactors.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.UserEventsInteractor;
import ee.help.helpee.network.ApiManager;
import ee.help.helpee.network.models.BearerToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by infinum on 02/05/15.
 */
public class UserEventsInteractorImpl implements UserEventsInteractor{

    ApiManager apiManager;

    @Inject
    public UserEventsInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }


    @Override
    public void cancelEvent(int eventId, String token, final SimpleBaseListener listener) {
        apiManager.getApiService().cancelEvent(eventId, BearerToken.authorize(token), new Callback<Response>() {
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

    @Override
    public void fetchUserEvents(String token, String userId, final BaseListener<List<Event>> userCreatedEventsListener) {

        apiManager.getApiService().getEventsByUser(userId, BearerToken.authorize(token), new Callback<List<Event>>() {
            @Override
            public void success(List<Event> eventList, Response response) {
                userCreatedEventsListener.onSuccess(eventList);
            }

            @Override
            public void failure(RetrofitError error) {

                userCreatedEventsListener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });

    }


}

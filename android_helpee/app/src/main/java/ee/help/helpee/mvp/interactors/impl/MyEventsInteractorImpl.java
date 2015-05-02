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

    @Override
    public void fetchHelpingEvents(String token, String userId, final BaseListener<List<Event>> userHelpingEventListener) {
        apiManager.getApiService().getEventsWhereUserHelps(userId, BearerToken.authorize(token), new Callback<List<Event>>() {
            @Override
            public void success(List<Event> eventList, Response response) {
                userHelpingEventListener.onSuccess(eventList);
            }

            @Override
            public void failure(RetrofitError error) {

                userHelpingEventListener.onFail(ErrorType.CONNECTION_ERROR);
            }
        });

    }
}

package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.network.ApiManager;

/**
 * Created by infinum on 28/04/15.
 */
public class MyEventsInteractorImpl implements ee.help.helpee.mvp.interactors.MyEventsInteractor {


    ApiManager apiManager;

    @Inject
    public MyEventsInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void fetchGoingEvents(String token, BaseListener<Event> eventBaseListener) {

    }

    @Override
    public void fetchHelpingEvents(String token, BaseListener<Event> eventBaseListener) {

    }
}

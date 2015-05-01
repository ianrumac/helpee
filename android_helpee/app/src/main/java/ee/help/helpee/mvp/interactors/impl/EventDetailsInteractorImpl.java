package ee.help.helpee.mvp.interactors.impl;

import javax.inject.Inject;

import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.mvp.interactors.EventDetailsInteractor;
import ee.help.helpee.network.ApiManager;

/**
 * Created by infinum on 01/05/15.
 */
public class EventDetailsInteractorImpl implements EventDetailsInteractor {


    ApiManager apiManager;

    @Inject
    public EventDetailsInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void sendPoints(int eventId, int points, String userId, String token, SimpleBaseListener successListener) {

    }

    @Override
    public void sendHelp(int eventId, String userId, String token, SimpleBaseListener successListener) {

    }
}

package ee.help.helpee.mvp.interactors.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.EventFeedInteractor;
import ee.help.helpee.network.ApiManager;

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
    public void fetchEvents(BaseListener<List<Event>> eventsListener) {

    }
}

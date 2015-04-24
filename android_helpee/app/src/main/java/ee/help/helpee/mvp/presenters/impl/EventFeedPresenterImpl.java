package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.mvp.interactors.EventFeedInteractor;
import ee.help.helpee.mvp.presenters.EventFeedPresenter;
import ee.help.helpee.mvp.views.EventFeedView;

/**
 * Created by ian on 24/04/15.
 */
public class EventFeedPresenterImpl implements EventFeedPresenter {

    EventFeedView feedView;

    EventFeedInteractor eventFeedInteractor;

    @Inject
    public EventFeedPresenterImpl(EventFeedView feedView, EventFeedInteractor eventFeedInteractor) {
        this.feedView = feedView;
        this.eventFeedInteractor = eventFeedInteractor;
    }

    @Override
    public void loadEventList() {

    }

    @Override
    public void eventJoined(int id) {

    }
}

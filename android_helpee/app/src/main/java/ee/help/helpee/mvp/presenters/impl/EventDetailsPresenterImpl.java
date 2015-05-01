package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.presenters.EventDetailsPresenter;
import ee.help.helpee.mvp.views.EventDetailsView;

/**
 * Created by infinum on 01/05/15.
 */
public class EventDetailsPresenterImpl implements EventDetailsPresenter {


    EventDetailsPresenter eventDetailsPresenter;

    EventDetailsView eventDetailsView;

    @Inject
    public EventDetailsPresenterImpl(EventDetailsPresenter eventDetailsPresenter, EventDetailsView eventDetailsView) {
        this.eventDetailsPresenter = eventDetailsPresenter;
        this.eventDetailsView = eventDetailsView;
    }

    @Override
    public void fetchEventData(int eventId, String userId, String token) {

    }

    @Override
    public void showEventData(Event event) {

    }

    @Override
    public void chipIn(int eventId, int points, String userId, String token) {

    }

    @Override
    public void sendHelp(int eventId, String userId, String token) {

    }
}

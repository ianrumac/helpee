package ee.help.helpee.mvp.presenters.impl;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;

import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.EventDetailsInteractor;
import ee.help.helpee.mvp.presenters.EventDetailsPresenter;
import ee.help.helpee.mvp.views.EventDetailsView;
import ee.help.helpee.utils.TimeUtils;

/**
 * Created by infinum on 01/05/15.
 */
public class EventDetailsPresenterImpl implements EventDetailsPresenter {


    EventDetailsInteractor eventDetailsInteractor;

    EventDetailsView eventDetailsView;

    @Inject
    public EventDetailsPresenterImpl(EventDetailsInteractor eventDetailsInteractor, EventDetailsView eventDetailsView) {
        this.eventDetailsInteractor = eventDetailsInteractor;
        this.eventDetailsView = eventDetailsView;
    }

    @Override
    public void fetchEventData(int eventId, String userId, String token) {

    }

    @Override
    public void showEventData(Event event) {

        eventDetailsView.showEventData(event);


        eventDetailsView.setEventDate(TimeUtils.parseDateTimeIntoShowableString(event.getEventDate()));
    }

    @Override
    public void chipIn(int eventId, int points, String userId, String token) {

    }

    @Override
    public void sendHelp(int eventId, String userId, String token) {

    }
}

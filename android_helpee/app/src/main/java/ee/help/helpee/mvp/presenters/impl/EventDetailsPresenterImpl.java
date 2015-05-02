package ee.help.helpee.mvp.presenters.impl;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
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
        eventDetailsInteractor.sendHelp(eventId, userId, token, new SimpleBaseListener() {
            @Override
            public void onSuccess() {
                eventDetailsView.hasHelped();
            }

            @Override
            public void onFail(ErrorType errorType) {
                eventDetailsView.showError(errorType);
            }
        });

    }

    @Override
    public void cancelHelp(final int eventId, String userId, String token) {
        eventDetailsView.showProgress();
        eventDetailsInteractor.cancelHelp(eventId, userId, token, new SimpleBaseListener() {
            @Override
            public void onSuccess() {
                eventDetailsView.hideProgress();
                eventDetailsView.cancelHelp();
            }

            @Override
            public void onFail(ErrorType errorType) {
                eventDetailsView.hideProgress();
                eventDetailsView.showError(errorType);
            }
        });
    }

    @Override
    public void completeEvent(boolean eventSuccess, int eventId, String userToken) {
        eventDetailsView.showProgress();

        if(eventSuccess)
            eventDetailsInteractor.completeEvent(eventId, userToken, new SimpleBaseListener() {
                @Override
                public void onSuccess() {
                    eventDetailsView.hideProgress();
                    eventDetailsView.completeEvent();
                }

                @Override
                public void onFail(ErrorType errorType) {
                    eventDetailsView.hideProgress();
                    eventDetailsView.showError(errorType);
                }
            });
        else
            eventDetailsInteractor.failedEvent(eventId, userToken, new SimpleBaseListener() {
                @Override
                public void onSuccess() {
                    eventDetailsView.hideProgress();
                    eventDetailsView.cancelEvent();
                }

                @Override
                public void onFail(ErrorType errorType) {
                    eventDetailsView.hideProgress();
                    eventDetailsView.showError(errorType);
                }
            });

    }

    @Override
    public void cancelEvent( int eventId, String token) {
        eventDetailsView.showProgress();
        eventDetailsInteractor.cancelEvent(eventId, token, new SimpleBaseListener() {
            @Override
            public void onSuccess() {
                eventDetailsView.hideProgress();
                eventDetailsView.cancelEvent();
                //TODO usereventsview remove event
            }

            @Override
            public void onFail(ErrorType errorType) {
                eventDetailsView.hideProgress();
                eventDetailsView.showError(errorType);
            }
        });
    }

}

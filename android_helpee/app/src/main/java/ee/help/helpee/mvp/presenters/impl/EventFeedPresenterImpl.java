package ee.help.helpee.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.User;
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
    public void loadEventList(String city, String userId, String token) {
        feedView.showProgress();
        eventFeedInteractor.fetchEvents(city, userId, token, eventListListenerImplementation);

    }

    @Override
    public void tryJoiningEvent(final int position, int id, String userId, String token) {

        feedView.showProgress();
        eventFeedInteractor.joinEvent(id, userId, token, new SimpleBaseListener() {
            @Override
            public void onSuccess() {
                feedView.hideProgress();
                feedView.eventJoined(position);
            }

            @Override
            public void onFail(ErrorType errorType) {
                feedView.hideProgress();
                feedView.showError(errorType);
            }
        });


    }

    BaseListener<List<Event>> eventListListenerImplementation = new BaseListener<List<Event>>() {
        @Override
        public void onSuccess(List<Event> success) {
                feedView.hideProgress();
                feedView.showEventList(success);

        }

        @Override
        public void onFail(ErrorType errorType) {
            feedView.hideProgress();
            feedView.showError(errorType);
        }
    };
}

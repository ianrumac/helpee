package ee.help.helpee.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.UserEventsInteractor;
import ee.help.helpee.mvp.presenters.UserEventsPresenter;
import ee.help.helpee.mvp.views.UserEventsView;

/**
 * Created by infinum on 02/05/15.
 */
public class UserEventsPresenterImpl implements UserEventsPresenter {

    UserEventsInteractor eventsInteractor;

    UserEventsView userEventsView;

    @Inject
    public UserEventsPresenterImpl(UserEventsInteractor eventsInteractor, UserEventsView userEventsView) {
        this.eventsInteractor = eventsInteractor;
        this.userEventsView = userEventsView;
    }

    @Override
    public void cancelEvent(final int position, final int eventId, String token) {
        userEventsView.showProgress();
        eventsInteractor.cancelEvent(eventId, token, new SimpleBaseListener() {
            @Override
            public void onSuccess() {
                userEventsView.hideProgress();
                userEventsView.removeEvent(position);
            }

            @Override
            public void onFail(ErrorType errorType) {
                userEventsView.hideProgress();
                userEventsView.showError(errorType);
            }
        });
    }


    @Override
    public void fetchUserEvents(String userId, String token) {
        eventsInteractor.fetchUserEvents(token, userId, userEventsListener);

    }


    BaseListener<List<Event>> userEventsListener = new BaseListener<List<Event>>() {
        @Override
        public void onSuccess(List<Event> success) {
            userEventsView.hideProgress();
            userEventsView.showEvents(success);
        }

        @Override
        public void onFail(ErrorType errorType) {
            userEventsView.hideProgress();
            userEventsView.showError(errorType);

        }
    };

}

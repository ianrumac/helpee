package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
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
                    //TODO usereventsview remove event
                }

                @Override
                public void onFail(ErrorType errorType) {
                    userEventsView.hideProgress();
                userEventsView.showError(errorType);
                }
            });
    }
}

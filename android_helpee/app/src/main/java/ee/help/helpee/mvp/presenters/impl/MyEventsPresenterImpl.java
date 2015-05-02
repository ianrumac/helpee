package ee.help.helpee.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.DoubleEventListContainer;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.MyEventsInteractor;
import ee.help.helpee.mvp.presenters.MyEventsPresenter;
import ee.help.helpee.mvp.views.MyEventsView;

/**
 * Created by infinum on 28/04/15.
 */
public class MyEventsPresenterImpl implements MyEventsPresenter {


    MyEventsView eventsView;

    MyEventsInteractor eventsInteractor;


    String token;
    String userId;
    DoubleEventListContainer doubleEventListContainer;
    @Inject
    public MyEventsPresenterImpl(MyEventsView eventsView, MyEventsInteractor eventsInteractor) {
        this.eventsView = eventsView;
        this.eventsInteractor = eventsInteractor;
    }

    @Override
    public void fetchUserEvents(String userId, String token) {
        this.userId = userId;
        this.token = token;
        doubleEventListContainer = new DoubleEventListContainer();
        eventsView.showProgress();
        eventsInteractor.fetchUserEvents(token, userId, userEventsListener);

    }

    BaseListener<List<Event>> userEventsListener = new BaseListener<List<Event>>() {
        @Override
        public void onSuccess(List<Event> success) {


            doubleEventListContainer.setUsersEvents(success);
            eventsInteractor.fetchHelpingEvents(token, userId, userHelpingEventsListener);
        }

        @Override
        public void onFail(ErrorType errorType) {
            eventsView.hideProgress();
            eventsView.showError(errorType);

        }
    };

    BaseListener<List<Event>> userHelpingEventsListener = new BaseListener<List<Event>>() {
        @Override
        public void onSuccess(List<Event> success) {

            doubleEventListContainer.setUserHelpingEvents(success);
            eventsView.hideProgress();
            eventsView.createFragmentsAndPager(doubleEventListContainer);

        }

        @Override
        public void onFail(ErrorType errorType) {
            eventsView.showError(errorType);

        }
    };

}

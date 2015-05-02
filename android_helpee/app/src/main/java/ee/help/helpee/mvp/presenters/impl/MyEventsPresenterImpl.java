package ee.help.helpee.mvp.presenters.impl;

import android.util.Log;

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
    @Inject
    public MyEventsPresenterImpl(MyEventsView eventsView, MyEventsInteractor eventsInteractor) {
        this.eventsView = eventsView;
        this.eventsInteractor = eventsInteractor;
    }


}

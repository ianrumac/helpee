package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.mvp.interactors.MyEventsInteractor;
import ee.help.helpee.mvp.presenters.MyEventsPresenter;
import ee.help.helpee.mvp.views.MyEventsView;

/**
 * Created by infinum on 28/04/15.
 */
public class MyEventsPresenterImpl implements MyEventsPresenter {


    MyEventsView eventsView;

    MyEventsInteractor eventsInteractor;

    @Inject
    public MyEventsPresenterImpl(MyEventsView eventsView, MyEventsInteractor eventsInteractor) {
        this.eventsView = eventsView;
        this.eventsInteractor = eventsInteractor;
    }


}

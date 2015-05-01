package ee.help.helpee.mvp.interactors.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.interactors.EventFeedInteractor;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 21/04/15.
 */
public class EventFeedInteractorImpl implements EventFeedInteractor {



    ApiManager apiManager;

    @Inject
    public EventFeedInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void fetchEvents(BaseListener<List<Event>> eventsListener) {

        List<Event> events = new ArrayList<>();
        Event eventToTest = new Event();
        eventToTest.setEventDate("01:20 - 22:30 16.4.2015");
        eventToTest.setEventTitle("Pomozimo ianu da popuni podatke");
        eventToTest.setLocation("Strojarska 22");

        eventToTest.setDescription(
                "Pomozimo ianu da popuni podatkePomozimo ianu da popuni podatkePomozimo ianu da popuni podatkePomozimo ianu da popuni podatkePomozimo ianu da popuni podatkePomozimo ianu da popuni podatke");
        eventToTest.setUserImageLink("http://www.gethorizontal.com/wp-content/themes/ness/library/images/thumbnail-1400x580.png");
        eventToTest.setPoints(20);
        eventToTest.setUserFullName("Ian Rumac");
        events.add(eventToTest);
        eventToTest.setDescription("AJMO JEN DVA JEN DVA JEN DVA JEN DVA JEN DVA");
        events.add(eventToTest);
        eventToTest.setEventTitle("123123123");
        eventToTest.setLocation("MOJA KUCA TGVOJA KUCA");
        events.add(eventToTest);

        events.add(eventToTest);

        events.add(eventToTest);

        eventsListener.onSuccess(events);
    }
}

package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;

/**
 * Created by infinum on 28/04/15.
 */
public interface MyEventsInteractor {


    void fetchGoingEvents(String token, BaseListener<Event> eventBaseListener);

    void fetchHelpingEvents(String token, BaseListener<Event> eventBaseListener);

}

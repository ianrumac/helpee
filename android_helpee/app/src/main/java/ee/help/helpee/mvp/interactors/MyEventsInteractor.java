package ee.help.helpee.mvp.interactors;

import java.util.List;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;

/**
 * Created by infinum on 28/04/15.
 */
public interface MyEventsInteractor {


    void fetchUserEvents(String token, String userId, BaseListener<List<Event>> eventBaseListener);

    void fetchHelpingEvents(String token, String userId, BaseListener<List<Event>> eventBaseListener);

}

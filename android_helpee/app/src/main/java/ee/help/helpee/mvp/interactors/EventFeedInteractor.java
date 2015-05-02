package ee.help.helpee.mvp.interactors;

import java.util.List;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;

/**
 * Created by ian on 12/04/15.
 */
public interface EventFeedInteractor {

    void fetchEvents(String city, String userId, String token, BaseListener<List<Event>> eventsListener);


    void joinEvent(int eventId, String userId, String token, SimpleBaseListener listener);
}

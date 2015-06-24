package ee.help.helpee.mvp.interactors;

import java.util.List;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.User;

/**
 * Created by infinum on 28/04/15.
 */
public interface UserEventsInteractor {


    void cancelEvent(int eventId, String token, SimpleBaseListener listener);

    void fetchUserEvents(String token, String userId, BaseListener<List<Event>> eventBaseListener);

}

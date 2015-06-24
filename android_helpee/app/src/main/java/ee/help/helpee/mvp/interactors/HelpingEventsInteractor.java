package ee.help.helpee.mvp.interactors;

import java.util.List;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;

/**
 * Created by infinum on 28/04/15.
 */
public interface HelpingEventsInteractor {


    void fetchHelpingEvents(String token, String userId, BaseListener<List<Event>> eventBaseListener);

    void cancelHelp(int eventId,String userId, String token, SimpleBaseListener listener);
}

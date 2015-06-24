package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.SimpleBaseListener;

/**
 * Created by infinum on 01/05/15.
 */
public interface EventDetailsInteractor {

    void sendPoints(int eventId, int points, String userId, String token, SimpleBaseListener successListener);

    void sendHelp(int eventId, String userId, String token, SimpleBaseListener successListener);

    void cancelEvent(int eventId, String token, SimpleBaseListener listener);

    void cancelHelp(int eventId, String userId, String token, SimpleBaseListener listener);

    void completeEvent(int eventId, String token, SimpleBaseListener listener);

    void failedEvent(int eventId, String token, SimpleBaseListener listener);

}

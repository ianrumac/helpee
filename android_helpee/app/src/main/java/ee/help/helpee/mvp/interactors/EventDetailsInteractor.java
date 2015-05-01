package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.SimpleBaseListener;

/**
 * Created by infinum on 01/05/15.
 */
public interface EventDetailsInteractor {

    void sendPoints(int eventId, int points, String userId, String token, SimpleBaseListener successListener);

    void sendHelp(int eventId, String userId, String token, SimpleBaseListener successListener);


}

package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;

/**
 * Created by infinum on 28/04/15.
 */
public interface UserEventsInteractor {


    void cancelEvent(int eventId, String token, SimpleBaseListener listener);
}

package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.SimpleBaseListener;

/**
 * Created by infinum on 28/04/15.
 */
public interface HelpingEventsInteractor {


    void cancelHelp(int eventId,String userId, String token, SimpleBaseListener listener);
}

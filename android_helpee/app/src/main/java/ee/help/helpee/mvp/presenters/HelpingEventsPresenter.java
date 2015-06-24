package ee.help.helpee.mvp.presenters;

/**
 * Created by infinum on 28/04/15.
 */
public interface HelpingEventsPresenter {

   void cancelHelp(int position, int eventId,String userId, String token);

   void fetchUserEvents(String userId, String token);
}

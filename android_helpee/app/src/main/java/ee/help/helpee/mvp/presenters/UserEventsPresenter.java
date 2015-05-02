package ee.help.helpee.mvp.presenters;

import ee.help.helpee.models.DoubleEventListContainer;

/**
 * Created by infinum on 28/04/15.
 */
public interface UserEventsPresenter {

   void cancelEvent(int position, int eventId, String token);

   void fetchUserEvents(String userId, String token);


}

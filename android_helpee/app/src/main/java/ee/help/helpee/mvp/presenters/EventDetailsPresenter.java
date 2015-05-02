package ee.help.helpee.mvp.presenters;

import ee.help.helpee.models.Event;

/**
 * Created by infinum on 01/05/15.
 */
public interface EventDetailsPresenter {


    void fetchEventData(int eventId, String userId, String token);

    void showEventData(Event event);

    void chipIn(int eventId, int points, String userId, String token);

    void sendHelp(int eventId, String userId, String token);

    void cancelHelp( int position, int eventId, String userId, String token);

    void completeEvent(boolean eventSuccess, int eventId, String userToken);

    void cancelEvent(int eventId, String userToken);
}

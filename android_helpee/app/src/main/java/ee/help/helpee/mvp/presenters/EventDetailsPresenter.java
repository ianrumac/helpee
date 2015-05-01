package ee.help.helpee.mvp.presenters;

/**
 * Created by infinum on 01/05/15.
 */
public interface EventDetailsPresenter {

    void showEventData();

    void chipIn(int eventId, int points, String userId, String token);

    void sendHelp(int eventId, String userId, String token);

}

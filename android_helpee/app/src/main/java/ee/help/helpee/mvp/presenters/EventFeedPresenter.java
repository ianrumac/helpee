package ee.help.helpee.mvp.presenters;

import ee.help.helpee.models.User;

/**
 * Created by ian on 12/04/15.
 */
public interface EventFeedPresenter {


    void loadEventList(String city, String userId, String token);

    void tryJoiningEvent(int position, int id, String userId, String token);



}

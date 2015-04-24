package ee.help.helpee.mvp.views;

import java.util.List;

import ee.help.helpee.models.Event;

/**
 * Created by ian on 12/04/15.
 */
public interface EventFeedView extends BaseView {


    void showEventList(List<Event> eventList);

    void eventJoined();


}

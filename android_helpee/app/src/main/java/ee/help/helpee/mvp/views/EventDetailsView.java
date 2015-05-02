package ee.help.helpee.mvp.views;

import ee.help.helpee.models.Event;

/**
 * Created by infinum on 01/05/15.
 */
public interface EventDetailsView {

    void showEventData(Event event);

    void hasChippedIn();

    void hasHelped();

    void setEventDate(String dateTime);
}

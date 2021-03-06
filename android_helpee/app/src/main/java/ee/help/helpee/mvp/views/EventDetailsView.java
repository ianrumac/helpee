package ee.help.helpee.mvp.views;

import ee.help.helpee.models.Event;

/**
 * Created by infinum on 01/05/15.
 */
public interface EventDetailsView extends BaseView{

    void showEventData(Event event);

    void hasChippedIn();

    void hasHelped();

    void setEventDate(String dateTime);

    void cancelHelp();

    void cancelEvent();

    void completeEvent();
}

package ee.help.helpee.mvp.views;

import java.util.List;

import ee.help.helpee.models.Event;

/**
 * Created by infinum on 02/05/15.
 */
public interface HelpingEventsView extends BaseView{

    void removeEvent(int eventId);

    void showEvents(List<Event> eventResultList);
}


package ee.help.helpee.mvp.interactors;

import java.util.List;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.Event;

/**
 * Created by ian on 12/04/15.
 */
public interface EventsInteractor {

    void fetchEvents(BaseListener<List<Event>> eventsListener);
}

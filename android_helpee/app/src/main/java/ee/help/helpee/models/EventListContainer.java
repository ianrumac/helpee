package ee.help.helpee.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infinum on 02/05/15.
 */
public class EventListContainer implements Serializable {

    List<Event> eventList;

    public EventListContainer(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}

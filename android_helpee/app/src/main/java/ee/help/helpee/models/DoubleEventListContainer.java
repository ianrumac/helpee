package ee.help.helpee.models;

import java.util.List;

/**
 * Created by infinum on 02/05/15.
 */
public class DoubleEventListContainer {


    List<Event> usersEvents;

    List<Event> userHelpingEvents;

    public List<Event> getUsersEvents() {
        return usersEvents;
    }

    public void setUsersEvents(List<Event> usersEvents) {
        this.usersEvents = usersEvents;
    }

    public List<Event> getUserHelpingEvents() {
        return userHelpingEvents;
    }

    public void setUserHelpingEvents(List<Event> userHelpingEvents) {
        this.userHelpingEvents = userHelpingEvents;
    }
}

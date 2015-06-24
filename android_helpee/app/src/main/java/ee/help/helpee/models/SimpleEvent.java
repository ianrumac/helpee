package ee.help.helpee.models;

/**
 * Created by infinum on 01/05/15.
 */
public class SimpleEvent {

    String eventname;

    String city;

    String description;

    double longitude;

    double latitude;

    String creatorid;

    int points;

    String eventdate;

    String address;

    public SimpleEvent(String eventname, String city, String description, double longitude, double latitude, String creatorid, int points, String eventdate, String address) {
        this.eventname = eventname;
        this.city = city;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.creatorid = creatorid;
        this.points = points;
        this.eventdate = eventdate;
        this.address = address;
    }
}

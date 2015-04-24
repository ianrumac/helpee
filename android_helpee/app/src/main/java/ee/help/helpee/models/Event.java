package ee.help.helpee.models;

/**
 * Created by ian on 12/04/15.
 */
public class Event {

    String eventTitle;

    String userFullName;

    String userImageLink;

    String eventDateAndTime;

    String description;

    int eventId;

    long eventStartTime;

    long eventEndTime;

    int points;

    String location;

    double latitude;

    double longitude;


    public String getEventTitle() {
        return eventTitle;
    }

    public String getUserImageLink() {
        //TODO placeholder if it is null
        return userImageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(String eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }

    public void setUserImageLink(String userImageLink) {
        this.userImageLink = userImageLink;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(long eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public long getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(long eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

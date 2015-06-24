package ee.help.helpee.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ian on 12/04/15.
 */
public class Event implements Serializable {

    @SerializedName("ID")
    int eventId;

    @SerializedName("EventName")
    String eventTitle;

    @SerializedName("City")
    String location;

    @SerializedName("Address")
    String address;

    @SerializedName("Description")
    String description;

    @SerializedName("Longitude")
    double longitude;

    @SerializedName("Latitude")
    double latitude;

    @SerializedName("EventDate")
    String eventDate;


    @SerializedName("Creator")
    User creator;

    @SerializedName("Helpees")
    List<User> helpeesList;

    @SerializedName("ChipIns")
    List<User> chipIns;

    @SerializedName("IsCompleted")
    boolean isCompleted;

    @SerializedName("NrOfHelpees")
    int numberOfHelpees;

    @SerializedName("Points")
    int points;


    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getHelpeesList() {
        return helpeesList;
    }

    public void setHelpeesList(List<User> helpeesList) {
        this.helpeesList = helpeesList;
    }

    public List<User> getChipIns() {
        return chipIns;
    }

    public void setChipIns(List<User> chipIns) {
        this.chipIns = chipIns;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getNumberOfHelpees() {
        return numberOfHelpees;
    }

    public void setNumberOfHelpees(int numberOfHelpees) {
        this.numberOfHelpees = numberOfHelpees;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Event() {
    }
}

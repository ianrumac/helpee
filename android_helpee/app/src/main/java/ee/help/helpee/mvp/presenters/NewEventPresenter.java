package ee.help.helpee.mvp.presenters;

import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import ee.help.helpee.models.User;

/**
 * Created by ian on 26/04/15.
 */
public interface NewEventPresenter {

    void createEvent(User user, LatLng location, String eventName, String eventDescription, int points, String timeOutput, String dateOutput, String city,String locationCity);

}

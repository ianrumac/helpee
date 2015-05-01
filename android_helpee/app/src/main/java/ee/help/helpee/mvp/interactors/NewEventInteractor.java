package ee.help.helpee.mvp.interactors;

import com.google.android.gms.maps.model.LatLng;

import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;

/**
 * Created by ian on 26/04/15.
 */
public interface NewEventInteractor {

    void postEvent(User user, LatLng location, String eventName,
            String eventDescription, int points, String date, String time, String city, SimpleBaseListener simpleListener);
}

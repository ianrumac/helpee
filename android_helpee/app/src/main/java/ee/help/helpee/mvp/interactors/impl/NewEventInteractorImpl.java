package ee.help.helpee.mvp.interactors.impl;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.NewEventInteractor;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 26/04/15.
 */
public class NewEventInteractorImpl implements NewEventInteractor {

    ApiManager apiManager;

    @Inject
    public NewEventInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void postEvent(User user, LatLng location, String eventName, String eventDescription, int points,
            SimpleBaseListener simpleListener) {

    }
}

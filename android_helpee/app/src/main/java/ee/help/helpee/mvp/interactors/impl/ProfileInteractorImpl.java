package ee.help.helpee.mvp.interactors.impl;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.NewEventInteractor;
import ee.help.helpee.network.ApiManager;

/**
 * Created by ian on 26/04/15.
 */
public class ProfileInteractorImpl implements ee.help.helpee.mvp.interactors.ProfileInteractor {

    ApiManager apiManager;

    @Inject
    public ProfileInteractorImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void fetchUserProfileData(String token, BaseListener<User> userBaseListener) {

    }

}

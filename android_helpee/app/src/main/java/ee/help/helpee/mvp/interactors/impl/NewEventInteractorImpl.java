package ee.help.helpee.mvp.interactors.impl;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.NewEventInteractor;
import ee.help.helpee.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    public void postEvent(User user, LatLng location, String eventName, String eventDescription, int points, String date, String time,
            String city, final SimpleBaseListener simpleListener) {


        Event postedEvent = new Event(eventName, user.getUserId(), date, time, eventDescription,
                location.latitude, location.longitude, city);

        apiManager.getApiService().postEvent(postedEvent, user.getToken(), new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                simpleListener.onSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                simpleListener.onFail(ErrorType.NO_DATA);
            }
        });
    }
}

package ee.help.helpee.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ee.help.helpee.R;
import ee.help.helpee.custom.Constants;

/**
 * Created by ian on 26/04/15.
 */
public class FetchAddressIntentService extends IntentService {

    private static final String TAG = "fetch-address-intent-service";

    protected ResultReceiver mReceiver;



    // Address found using the Geocoder.
    List<Address> addresses = null;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * TAG Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService() {
        super(TAG);
    }

    /**
     * Tries to get the location address using a Geocoder. If successful, sends an address to a
     * result receiver. If unsuccessful, sends an error message instead.
     * Note: We define a {@link android.os.ResultReceiver} in * MainActivity to process content
     * sent from this service.
     *
     * This service calls this method from the default worker thread with the intent that started
     * the service. When this method returns, the service automatically stops.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String errorMessage = "";

        mReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        // Get the location passed to this service through an extra.
        LatLng location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);


        try {
            // Using getFromLocation() returns an array of Addresses for the area immediately
            // surrounding the given latitude and longitude. The results are a best guess and are
            // not guaranteed to be accurate.
            addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
        }
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
            }
            deliverResultToReceiver(Constants.FAILURE_RESULT, null);
        } else {
            Address address = addresses.get(0);
            deliverResultToReceiver(Constants.SUCCESS_RESULT, address);
        }
    }


    /**
     * Sends a resultCode and message to the receiver.
     */
    private void deliverResultToReceiver(int resultCode, Address address) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.RESULT_DATA_KEY, address);
        mReceiver.send(resultCode, bundle);
    }


}
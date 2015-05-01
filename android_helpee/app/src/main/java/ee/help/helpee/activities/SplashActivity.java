package ee.help.helpee.activities;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.receivers.AddressResultReceiver;
import ee.help.helpee.services.FetchAddressIntentService;

import static ee.help.helpee.HelpeeApplication.*;

/**
 * Created by ian on 25/04/15.
 */
public class SplashActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    protected static final String TAG = "splash-activity";

    protected static final String ADDRESS_REQUESTED_KEY = "address-request-pending";

    protected static final String LOCATION_ADDRESS_KEY = "location-address";

    protected GoogleApiClient mGoogleApiClient;


    protected Location mLastLocation;

    protected String userCity;

    private AddressResultReceiver mResultReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mResultReceiver = new AddressResultReceiver(new Handler(), returnAddressListener );

        buildGoogleApiClient();

        super.onCreate(savedInstanceState);
    }

    BaseListener<Address> returnAddressListener = new BaseListener<Address>() {
        @Override
        public void onSuccess(Address success) {
            userCity = success.getLocality();
            setUserCity(userCity);
            onSplashFinished();
        }

        @Override
        public void onFail(ErrorType errorType) {
            showError(ErrorType.LOCATION_ERROR);
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        setLastKnownLocation(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
        if (mLastLocation != null) {
            startIntentService();
        }else{
            onConnected(bundle);
        }

    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    void onSplashFinished(){

        /*Check if user is logged in and start the corresponding activity*/
        if (getUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();

    }

}

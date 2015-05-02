package ee.help.helpee.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.receivers.AddressResultReceiver;
import ee.help.helpee.services.FetchAddressIntentService;

import static ee.help.helpee.HelpeeApplication.setLastKnownLocation;
import static ee.help.helpee.HelpeeApplication.setUserCity;

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
    @InjectView(R.id.spinning_anim)
    ImageView splashImageView;

    private AddressResultReceiver mResultReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mResultReceiver = new AddressResultReceiver(new Handler(), returnAddressListener);
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        if (apiAvailability.isGooglePlayServicesAvailable(this) != ConnectionResult.SUCCESS)
            apiAvailability.getErrorDialog(this, apiAvailability.isGooglePlayServicesAvailable(this), 0);
        setContentView(R.layout.layout_splash);
        ButterKnife.inject(this);
        splashImageView.setBackgroundResource(R.drawable.splash_anim);
        AnimationDrawable splashAnim = (AnimationDrawable) splashImageView.getBackground();
        splashAnim.start();

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
        } else {
            onConnected(bundle);
        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        GoogleApiAvailability.getInstance().getErrorDialog(this, connectionResult.getErrorCode(), 0, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });

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

    void onSplashFinished() {
     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {


        /*Check if user is logged in and start the corresponding activity*/
        if (getUser() != null) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
         }
     },3000);

    }

    /**
     * When API client check if user has services, and he decides to update, this is invoked.
     */



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0)
            if (resultCode == RESULT_OK)
                mGoogleApiClient.connect();
            else
                finish();
        super.onActivityResult(requestCode, resultCode, data);
    }
}

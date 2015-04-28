package ee.help.helpee.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.widgets.SnackBar;
import com.google.android.gms.maps.model.LatLng;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.R;
import ee.help.helpee.activities.BaseActivity;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.fragments.SelectLocationFragment;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.mvp.presenters.NewEventPresenter;
import ee.help.helpee.mvp.views.NewEventView;
import ee.help.helpee.receivers.AddressResultReceiver;
import ee.help.helpee.services.FetchAddressIntentService;

/**
 * Created by ian on 26/04/15.
 */
public class NewEventActivity extends BaseActivity implements NewEventView {


    @InjectView(R.id.event_title)
    MaterialEditText eventTitle;

    @InjectView(R.id.event_description)
    MaterialEditText eventDescription;

    @InjectView(R.id.create_event_btn)
    TextView createEventBtn;

    @Inject
    NewEventPresenter newEventPresenter;

    LatLng userSelectedLocation;
    int eventPoints;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.points_added)
    TextView pointsAdded;
    @InjectView(R.id.date)
    TextView date;
    @InjectView(R.id.date_output)
    TextView dateOutput;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.time_output)
    TextView timeOutput;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.location_output)
    TextView locationOutput;

    AddressResultReceiver mResultReceiver;
    boolean isDateSelected, isTimeSelected, isLocationSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_event);
        eventPoints = 1;
        mResultReceiver = new AddressResultReceiver(new Handler(), returnAddressListener );

        ButterKnife.inject(this);

    }

    @OnClick(R.id.add_points_btn)
    void addPoint() {
        if (eventPoints < getUser().getPoints())
            setEventPoints(++eventPoints);
    }

    @OnClick(R.id.sub_points_btn)
    void subPoint() {
        if (eventPoints > 1)
            setEventPoints(--eventPoints);
    }

    void setEventPoints(int futurePoints) {
        if (futurePoints > 1) {
            pointsAdded.setText(String.format(getString(R.string.int_points), futurePoints));
        } else {
            pointsAdded.setText(getString(R.string.single_point));
        }
    }

    @Override
    public void eventCreated() {
        new SnackBar(this, getString(R.string.event_created)).show();
        finish();
    }


    @OnClick(R.id.create_event_btn)
    void createNewEvent() {
        if (checkInputIsValid())
            newEventPresenter.createEvent(getUser(), userSelectedLocation, eventTitle.getText().toString(),
                    eventDescription.getText().toString(), eventPoints);
    }

    boolean checkInputIsValid() {

        if (eventTitle.getText().toString().length() < 3)
            return false;
        else if (eventDescription.getText().toString().length() < 3)
            return false;
        else return (isLocationSelected && isDateSelected && isTimeSelected);

    }

    @OnClick(R.id.location_output)
    void openLocationPicker(){
        SelectLocationFragment selectLocationFragment = new SelectLocationFragment();
        selectLocationFragment.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Black);
        selectLocationFragment.show(getFragmentManager(), SelectLocationFragment.TAG);
    }

    public void setLocation(LatLng latLng){
        userSelectedLocation = latLng;
        isLocationSelected = true;
        startIntentService();

    }
    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, userSelectedLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    BaseListener<Address> returnAddressListener = new BaseListener<Address>() {
        @Override
        public void onSuccess(Address success) {
            locationOutput.setText(success.getThoroughfare());
        }

        @Override
        public void onFail(ErrorType errorType) {
            showError(getString(R.string.failed_location));
        }
    };


}

package ee.help.helpee.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.gc.materialdesign.widgets.SnackBar;
import com.google.android.gms.maps.model.LatLng;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.dagger.NewEventModule;
import ee.help.helpee.dagger.components.DaggerNewEventComponent;
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
public class NewEventActivity extends BaseFragmentActivity implements NewEventView {

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";


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
    @InjectView(R.id.date_output)
    TextView dateOutput;
    @InjectView(R.id.time_output)
    TextView timeOutput;
    @InjectView(R.id.location)
    TextView location;
    @InjectView(R.id.location_output)
    TextView locationOutput;

    String locationCity;
    AddressResultReceiver mResultReceiver;
    boolean isDateSelected, isTimeSelected, isLocationSelected = false;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    StringBuilder timeBuilder;
    StringBuilder dateBuilder;

    String time;
    String date;

    SelectLocationFragment selectLocationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        DaggerNewEventComponent.builder().newEventModule(new NewEventModule(this)).build().inject(this);

        dateBuilder = new StringBuilder();
        timeBuilder = new StringBuilder();
        eventPoints = 1;
        mResultReceiver = new AddressResultReceiver(new Handler(), returnAddressListener);
        calendar = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
        timePickerDialog = TimePickerDialog.newInstance(onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);

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
        HelpeeApplication.changePoints(getUser().getPoints() - eventPoints);
        finish();
    }


    @OnClick(R.id.create_event_btn)
    void createNewEvent() {
        if (checkInputIsValid()) {
            newEventPresenter.createEvent(getUser(), userSelectedLocation, eventTitle.getText().toString(),
                    eventDescription.getText().toString(), eventPoints,
                    timeOutput.getText().toString(),
                    dateOutput.getText().toString(),
                    locationCity,
                    locationOutput.getText().toString());

        }
    }

    ;

    boolean checkInputIsValid() {

        if (eventTitle.getText().toString().length() < 3) {
            showError(getString(R.string.error_title));
            return false;
        } else if (eventDescription.getText().toString().length() < 3) {
            showError(getString(R.string.error_description));
            return false;

        } else if (timeOutput.getText().toString().equals(getString(R.string.select_time))) {
            showError(getString(R.string.error_time));
            return false;
        } else if (dateOutput.getText().toString().equals(getString(R.string.select_date))) {
            showError(getString(R.string.error_date));
            return false;
        } else if (!isLocationSelected) {
            showError(getString(R.string.select_location_error));
            return false;
        } else return true;


    }

    @OnClick(R.id.location_output)
    void openLocationPicker() {
        selectLocationFragment = new SelectLocationFragment();
        selectLocationFragment.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Black);
        selectLocationFragment.show(getFragmentManager(), SelectLocationFragment.TAG);
    }

    public void setLocation(LatLng latLng) {
        userSelectedLocation = latLng;
        isLocationSelected = true;

        /*Start service, because Geocoder is synchronous and we don't want to block the UI thread*/
        startIntentService();

    }

    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, userSelectedLocation);
        startService(intent);
    }

    BaseListener<Address> returnAddressListener = new BaseListener<Address>() {
        @Override
        public void onSuccess(Address success) {
            if(selectLocationFragment!=null){
                selectLocationFragment.setChosenLocation(success.getAddressLine(0));
            }
            locationOutput.setText(success.getAddressLine(0));
            locationCity = success.getLocality();
        }

        @Override
        public void onFail(ErrorType errorType) {
            showError(getString(R.string.failed_location));
        }
    };

    @OnClick(R.id.date_output)
    void chooseDate() {
        datePickerDialog.setVibrate(true);
        datePickerDialog.setYearRange(calendar.get(Calendar.YEAR), 2028);
        datePickerDialog.setCloseOnSingleTapDay(true);
        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
    }

    @OnClick(R.id.time_output)
    void chooseTime() {
        timePickerDialog.setVibrate(false);
        timePickerDialog.setCloseOnSingleTapMinute(true);
        timePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
            dateBuilder = new StringBuilder();
            if (month < calendar.get(Calendar.MONTH))
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            else if (day < calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);


            date = dateBuilder.append(timeToString(day)).append("-").append(timeToString(month+1)).append("-").append(year).toString();
            dateOutput.setText(date);
        }
    };

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minute) {
            timeBuilder = new StringBuilder();
            time = timeBuilder.append(timeToString(hourOfDay)).append(":").append(timeToString(minute)).toString();
            timeOutput.setText(time);
        }
    };


    String timeToString(int time) {
        if (time < 10) {
            return "0" + time;
        } else
            return String.valueOf(time);

    }


    @OnClick(R.id.ic_back)
    void backPressed(){
        super.onBackPressed();
    }
}

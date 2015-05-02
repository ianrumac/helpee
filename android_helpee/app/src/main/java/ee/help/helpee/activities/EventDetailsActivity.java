package ee.help.helpee.activities;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.dagger.EventDetailsModule;
import ee.help.helpee.dagger.components.DaggerEventDetailsComponent;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.presenters.EventDetailsPresenter;
import ee.help.helpee.mvp.views.EventDetailsView;

import static ee.help.helpee.HelpeeApplication.getInstance;

/**
 * Created by infinum on 29/04/15.
 */
public class EventDetailsActivity extends BaseActivity implements EventDetailsView {

    @InjectView(R.id.map_view)
    MapView mapView;
    @InjectView(R.id.needs_help_user)
    TextView needsHelpUser;
    @InjectView(R.id.helpee_user)
    TextView helpeeUser;
    @InjectView(R.id.event_street)
    TextView eventStreet;
    @InjectView(R.id.event_city)
    TextView eventCity;
    @InjectView(R.id.bottom_controls_container)
    LinearLayout bottomControlsContainer;
    @InjectView(R.id.chip_in_container)
    RelativeLayout chipInContainer;

    @InjectView(R.id.event_description)
    TextView eventDescription;

    @InjectView(R.id.event_time_date)
    TextView eventTimeAndDate;

    @InjectView(R.id.event_points)
    TextView eventPoints;

    @InjectView(R.id.dynamic_slider_layout)
    LinearLayout sliderLayout;

    @Inject
    EventDetailsPresenter detailsPresenter;


    GoogleMap googleMap;
    Event currentEvent;
    @InjectView(R.id.helped_overlay)
    FrameLayout helpedOverlay;

    @InjectView(R.id.btn_cant_help)
    TextView cantHelpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_details);

        ButterKnife.inject(this);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getInstance());
        initMap();

        DaggerEventDetailsComponent.builder().eventDetailsModule(new EventDetailsModule(this)).build().inject(this);
        setStateFromIntent();

        super.onCreate(savedInstanceState);
    }


    /**
     * Get current screen state from intent:
     * If EVENT_EXTRA was passed - normal opening of details from in-app link
     * If EVENT_FROM_ID was passed - we probably came from a push notification.
     */

    void setStateFromIntent() {
        Event currentEvent = (Event) getIntent().getSerializableExtra(Constants.EVENT_EXTRA);
        if (currentEvent == null) {
            detailsPresenter.fetchEventData(getIntent().getIntExtra(Constants.EVENT_FROM_ID, 0), getUser().getUserId(), getUser().getToken());
        } else {
            detailsPresenter.showEventData(currentEvent);
        }


        if (getIntent().getBooleanExtra(Constants.SHOULD_OPEN_CHIP_IN, false)) {
            setChipInOpened(0);
        }else if(getIntent().getBooleanExtra(Constants.FROM_HELPING, false)){
            bottomControlsContainer.setVisibility(View.GONE);
            cantHelpBtn.setVisibility(View.VISIBLE);
        }

    }


    public void initMap() {

        if (googleMap == null) {
            googleMap = mapView.getMap();
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            googleMap.setMyLocationEnabled(true);

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

    }

    /**
     * sets chip in overlay opened if user clicked
     * "Chip in" button on the list
     */

    void setChipInOpened(int duration) {
        chipInContainer.animate().translationY(-chipInContainer.getHeight()).setDuration(duration).start();
    }

    @OnClick(R.id.chip_in_button)
    void chipInSelected() {
        setChipInOpened(1000);

        List<ImageView> circlesList = new ArrayList<>();
        for (int i = 1; i <= getUser().getPoints(); i++) {
            ImageView circleView = new ImageView(this);
            circleView.setImageDrawable(getResources().getDrawable(R.drawable.slider_circle));
            circleView.setPadding(16, 16, 16, 16);
            circleView.setMaxWidth(sliderLayout.getWidth() / getUser().getPoints());
            circleView.setMaxHeight(sliderLayout.getWidth() / getUser().getPoints());

            circlesList.add(circleView);
        }

        for (ImageView imageView : circlesList) {
            sliderLayout.addView(imageView);
        }

        //TODO slide in chip in container
    }

    ;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    /**
     * Fills views with data except for date,
     * date is filled in setEventDate method
     */

    @Override
    public void showEventData(Event event) {
        currentEvent = event;
        eventCity.setText(event.getLocation());
        eventStreet.setText(event.getAddress());
        if (event.getHelpeesList().size() > 0)
            helpeeUser.setText(event.getHelpeesList().get(0).getFullName());
        needsHelpUser.setText(event.getCreator().getFullName());
        if (event.getPoints() > 1) {
            eventPoints.setText(String.format(getString(R.string.int_points), event.getPoints()));
        }

        /*Since it's just one marker we don't need to worry about bitmaps and memory, so we're good for now.*/
        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin))
                .position(new LatLng(event.getLatitude(), event.getLongitude())));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(event.getLatitude(), event.getLongitude()), 16));


    }

    @OnClick(R.id.help_btn)
    void helpUser() {
        if (getUser().getPoints() > 0) {
            detailsPresenter.sendHelp(currentEvent.getEventId(), getUser().getUserId(), getUser().getToken());
        } else {
            showDialog(getString(R.string.oh_no), getString(R.string.not_enough_points));
        }
    }


    @Override
    public void hasChippedIn() {


    }

    @Override
    public void hasHelped() {
        HelpeeApplication.changePoints(getUser().getPoints() - 1);
        helpedOverlay.setVisibility(View.VISIBLE);
        helpedOverlay.animate().alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                EventDetailsActivity.this.onBackPressed();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    @OnClick(R.id.btn_cant_help)
    void cancelHelp(){
        detailsPresenter.s
    }

    @OnClick(R.id.back_btn)
    void backPressed() {
        super.onBackPressed();
    }

    @Override
    public void setEventDate(String dateTime) {
        eventTimeAndDate.setText(dateTime);
    }
}

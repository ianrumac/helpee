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
import ee.help.helpee.utils.TimeUtils;

import static ee.help.helpee.HelpeeApplication.changePoints;
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

    @InjectView(R.id.helped_overlay)
    FrameLayout helpedOverlay;

    @InjectView(R.id.btn_cant_help)
    TextView cantHelpBtn;

    @InjectView(R.id.btn_cancel)
    TextView cancelBtn;

    @InjectView(R.id.bottom_controls_container)
    LinearLayout bottomControlsContainer;

    @InjectView(R.id.bottom_complete_container)
    LinearLayout bottomCompleteButtonsContainer;

    @InjectView(R.id.finished_overlay_text)
    TextView finishedOverlayText;

    GoogleMap googleMap;
    Event currentEvent;


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

        /**
         * This checks where the details came from and decides which controls to show based upon that.
         * FROM_HELPING = from screen where you are a helpee
         * OWNER_OF_EVENT = your event, still not finished
         * OWNER_OF_FINISHED_EVENT = your event, finished - also if event time has passed
         * !currentEvent.isCompleted = if current event isnt completed show controls, else everything remains hidden
         * **/


        if (getIntent().getBooleanExtra(Constants.SHOULD_OPEN_CHIP_IN, false)) {
            setChipInOpened(0);
        } else if (getIntent().getBooleanExtra(Constants.FROM_HELPING, false)) {
            cantHelpBtn.setVisibility(View.VISIBLE);
        } else if (getIntent().getBooleanExtra(Constants.OWNER_OF_EVENT, false) && !currentEvent.isCompleted() && !TimeUtils.hasEventPassed(currentEvent.getEventDate())) {
            cancelBtn.setVisibility(View.VISIBLE);
        } else if (getIntent().getBooleanExtra(Constants.OWNER_OF_FINISHED_EVENT, false) || TimeUtils.hasEventPassed(currentEvent.getEventDate())) {
            bottomCompleteButtonsContainer.setVisibility(View.VISIBLE);
        } else if (!currentEvent.isCompleted()) {
            bottomControlsContainer.setVisibility(View.VISIBLE);

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
        animateOverlay(getResources().getColor(R.color.md_amber_700));
    }


    @OnClick(R.id.btn_cant_help)
    void helpCancelPressed() {
        detailsPresenter.cancelHelp(currentEvent.getEventId(), getUser().getUserId(), getUser().getToken());
    }

    @OnClick(R.id.btn_cancel)
    void cancelEventPressed() {
        detailsPresenter.cancelEvent(currentEvent.getEventId(), getUser().getToken());
    }

    @OnClick(R.id.failed_btn)
    void failedEventPressed() {
        detailsPresenter.completeEvent(false, currentEvent.getEventId(), getUser().getToken());
    }

    @OnClick(R.id.completed_btn)
    void completedEventPressed() {
        detailsPresenter.completeEvent(true, currentEvent.getEventId(), getUser().getToken());
    }


    @OnClick(R.id.back_btn)
    void backPressed() {
        super.onBackPressed();
    }

    @Override
    public void setEventDate(String dateTime) {
        eventTimeAndDate.setText(dateTime);
    }

    @Override
    public void cancelHelp() {

        changePoints(getUser().getPoints() + 1);
        finishedOverlayText.setText(getString(R.string.helpee_sad));
        animateOverlay(getResources().getColor(R.color.md_red_500));

    }

    @Override
    public void cancelEvent() {
        changePoints(getUser().getPoints() + currentEvent.getPoints());
        finishedOverlayText.setText(getString(R.string.helpee_sad));
        animateOverlay(getResources().getColor(R.color.md_red_500));


    }

    @Override
    public void completeEvent() {
        finishedOverlayText.setText(getString(R.string.event_done));
        animateOverlay(getResources().getColor(R.color.md_green_500));


    }

    void animateOverlay(int backgroundColor) {
        helpedOverlay.setVisibility(View.VISIBLE);
        helpedOverlay.setBackgroundColor(backgroundColor);
        helpedOverlay.animate().alpha(1).setDuration(1200).setListener(new Animator.AnimatorListener() {
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
}

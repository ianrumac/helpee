package ee.help.helpee.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.dagger.EventDetailsModule;
import ee.help.helpee.dagger.components.DaggerEventDetailsComponent;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.presenters.EventDetailsPresenter;
import ee.help.helpee.mvp.views.EventDetailsView;

import static ee.help.helpee.HelpeeApplication.getInstance;
import static ee.help.helpee.HelpeeApplication.getLastKnownLocation;

/**
 * Created by infinum on 29/04/15.
 */
public class EventDetailsActivity extends BaseActivity implements EventDetailsView {

    @InjectView(R.id.map_view)
    MapView mapView;
    @InjectView(R.id.needs_help)
    TextView needsHelp;
    @InjectView(R.id.needs_help_user)
    TextView needsHelpUser;
    @InjectView(R.id.helpee)
    TextView helpee;
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


    @Inject
    EventDetailsPresenter detailsPresenter;


    GoogleMap googleMap;
    Event currentEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_details);

        ButterKnife.inject(this);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getInstance());
        DaggerEventDetailsComponent.builder().eventDetailsModule(new EventDetailsModule(this)).build().inject(this);
        if(getIntent().getBooleanExtra(Constants.SHOULD_OPEN_CHIP_IN, false)){
            openChipIn();
        }


        super.onCreate(savedInstanceState);
    }


    public void initMap() {

        if (googleMap == null) {
            googleMap = mapView.getMap();
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            googleMap.setMyLocationEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getLastKnownLocation(), 16));

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

    }

    void openChipIn(){

        //TODO slide in chip in container
    };

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


    @Override
    public void showEventData(Event event) {

    }

    @Override
    public void hasChippedIn() {

    }

    @Override
    public void hasHelped() {

    }
}

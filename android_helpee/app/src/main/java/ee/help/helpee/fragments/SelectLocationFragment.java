package ee.help.helpee.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.lang.Override;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.EventDetailsActivity;
import ee.help.helpee.activities.NewEventActivity;

import static ee.help.helpee.HelpeeApplication.*;

/**
 * Created by ian on 16/01/15.
 */
public class SelectLocationFragment extends DialogFragment {


    public static final String TAG = "select-location-fragment";
    @InjectView(R.id.map_view)
    MapView mMapView;

    @InjectView(R.id.location_chosen)
    TextView chosenLocation;

    GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.location_fragment, container, false);
        ButterKnife.inject(this, mainView);
        mMapView.onCreate(null);
        MapsInitializer.initialize(getInstance());
        initMap();


        return mainView;
    }


    public void initMap() {

        if (googleMap == null) {
            googleMap = mMapView.getMap();
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            googleMap.setMyLocationEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getLastKnownLocation(),16));

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
                chosenLocation.setText(getActivity().getString(R.string.loading));
                ((NewEventActivity) getActivity()).setLocation(latLng);
            }
        });

    }

    public void setChosenLocation(String address){
        chosenLocation.setText(address);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

    }

    @OnClick(R.id.confirm_location)
    void confirmLocation(){
        this.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }


}
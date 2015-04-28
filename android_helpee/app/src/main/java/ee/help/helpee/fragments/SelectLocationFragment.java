package ee.help.helpee.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import java.lang.Override;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.NewEventActivity;

/**
 * Created by ian on 16/01/15.
 */
public class SelectLocationFragment extends DialogFragment {


    public static final String TAG = "select-location-fragment";
    @InjectView(R.id.map_view)
    MapView mMapView;


    GoogleMap googleMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.location_fragment, container, false);
        ButterKnife.inject(this, mainView);
        mMapView.onCreate(null);
        MapsInitializer.initialize(HelpeeApplication.getInstance());
        initMap();
        return mainView;
    }


    public void initMap() {

        if (googleMap == null) {
            googleMap = mMapView.getMap();
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            googleMap.setMyLocationEnabled(true);

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                ((NewEventActivity) getActivity()).setLocation(latLng);
                SelectLocationFragment.this.dismiss();
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

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
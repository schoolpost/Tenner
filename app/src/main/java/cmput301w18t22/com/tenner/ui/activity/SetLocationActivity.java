package cmput301w18t22.com.tenner.ui.activity;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;

import cmput301w18t22.com.tenner.R;

public class SetLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private View mView;
    private LatLng position;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng currentloc;


    public void getcurrloc() throws SecurityException {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

                    currentloc = new LatLng(location.getLatitude(), location.getLongitude());
                    if (mapView != null) {
                        mapView.onCreate(null);
                        mapView.onResume();
                    }

                }
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_home);

        String pageTitle = "Set Task Location";

        TextView title = getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title);
        title.setText(pageTitle);

        setContentView(R.layout.fragment_map_view);
        mapView = (MapView) findViewById(R.id.map);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getcurrloc();

        if (mapView != null) {
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng yeg = new LatLng(53.5444, -113.4909);
        if (currentloc != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentloc, 15.5f));
        } else {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yeg, 15.5f));
        }

        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                position = mGoogleMap.getCameraPosition().target;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

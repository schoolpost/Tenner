package cmput301w18t22.com.tenner.ui.activity;


import android.content.Intent;
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


import cmput301w18t22.com.tenner.R;

public class SetLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private View mView;
    private LatLng position;


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

        Intent intent = getIntent();
        Double lat = intent.getDoubleExtra("lat", 53.5444);
        Double lng = intent.getDoubleExtra("lng", -113.4909);

        position = new LatLng(lat, lng);


        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        LatLng yeg = new LatLng(53.5444, -113.4909);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.5f));


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

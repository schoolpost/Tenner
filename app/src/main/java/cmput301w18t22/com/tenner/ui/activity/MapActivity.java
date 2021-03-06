package cmput301w18t22.com.tenner.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.server.ElasticServer;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private LatLng position;
    private Geocoder geo = new Geocoder(this, Locale.getDefault());
    private List<Address> addresses;
    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;
    GoogleApiClient mGoogleApiClient;

    public String getAddress(Double lat, Double lng) throws Exception {
        addresses = geo.getFromLocation(lat, lng, 5);
        return addresses.get(0).getAddressLine(0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_map);


        TextView title = getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title);


        TextView done = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Intent intent = new Intent();
                    intent.putExtra("location", getAddress(position.latitude, position.longitude));
                    intent.putExtra("lat", Double.valueOf(position.latitude).floatValue());
                    intent.putExtra("lng", Double.valueOf(position.longitude).floatValue());
                    setResult(20, intent);

                } catch (Exception e) {

                }

                finish();
            }
        });

        if (getIntent().getStringExtra("maptype").equals("viewmap")) {
            setContentView(R.layout.fragment_map_view);
            title.setText("Maps");
        } else if (getIntent().getStringExtra("maptype").equals("setmap")) {
            setContentView(R.layout.fragment_set_map);
            title.setText("Set Task Location");
        }

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

        //https://developers.google.com/places/android-api/start
        mGeoDataClient = Places.getGeoDataClient(this, null);

        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        GoogleApiClient.OnConnectionFailedListener connectionFailure = new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        };

        // TODO: Start using the Places API.
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, connectionFailure)
                .build();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MapsInitializer.initialize(getApplicationContext());

        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //https://developers.google.com/maps/documentation/android-api/location
        FloatingActionButton locationButton = (FloatingActionButton) findViewById(R.id.myLocationButton);
        if (getIntent().getStringExtra("maptype").equals("viewmap")) {


            locationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mGoogleMap.getMyLocation() != null) {
                        CameraUpdate center =
                                CameraUpdateFactory.newLatLng(new LatLng(mGoogleMap.getMyLocation().getLatitude(),
                                        mGoogleMap.getMyLocation().getLongitude()));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);

                        mGoogleMap.moveCamera(center);
                        mGoogleMap.animateCamera(zoom);
                    }
                }
            });


            CameraUpdate center =
                    CameraUpdateFactory.newLatLng(new LatLng(position.latitude,
                            position.longitude));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);

            mGoogleMap.moveCamera(center);
            mGoogleMap.animateCamera(zoom);


            try {
                mGoogleMap.setMyLocationEnabled(true);
            } catch (SecurityException e) {

            }

            getTasks(position.latitude, position.longitude);

            //https://developers.google.com/places/android-api/autocomplete
            PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                    getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

            //https://stackoverflow.com/questions/11692162/android-change-background-color-of-fragment?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            autocompleteFragment.getView().setBackgroundColor(Color.WHITE);

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    // TODO: Get info about the selected place.
                    Double latitude = place.getLatLng().latitude;
                    Double longitude = place.getLatLng().longitude;

                    CameraUpdate center =
                            CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);

                    mGoogleMap.moveCamera(center);
                    mGoogleMap.animateCamera(zoom);

                    getTasks(latitude, longitude);
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.i("Error", "An error occurred: " + status);
                }
            });

        }


        if (getIntent().getStringExtra("maptype").equals("setmap")) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.5f));

            mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                @Override
                public void onCameraMove() {
                    position = mGoogleMap.getCameraPosition().target;
                }
            });
        }


    }

    private void setPins(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            LatLng point = new LatLng(taskList.get(i).getLocation().getLatitude(),
                    taskList.get(i).getLocation().getLongitude());
            //https://stackoverflow.com/questions/14226453/google-maps-api-v2-how-to-make-markers-clickable?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(point);
            markerOptions.title("Title : " + taskList.get(i).getTitle());
            markerOptions.snippet("Requester : " + taskList.get(i).getRequester().getFirstName()
                    + taskList.get(i).getRequester().getLastName());
            Marker marker = mGoogleMap.addMarker(markerOptions);

            mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    LatLng position = (LatLng) marker.getTag();
                    return false;
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {

    }

    private void getTasks(double latitude, double longitude) {
        RequestParams params = new RequestParams();

        try {
            params.put("lat", latitude);
            params.put("long", longitude);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("getMapTasks", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Gson gson = new GsonBuilder().create();
                    ArrayList<Task> taskList = new ArrayList<Task>();

                    for (int i = 0; i < response.length(); i++) {
                        taskList.add(gson.fromJson(response.get(i).toString(), Task.class));
                    }

                    setPins(taskList);

                } catch (Exception e) {

                }
            }
        });
    }
}

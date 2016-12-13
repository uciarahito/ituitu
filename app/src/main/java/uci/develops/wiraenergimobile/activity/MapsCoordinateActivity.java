package uci.develops.wiraenergimobile.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;

public class MapsCoordinateActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    private Button buttonSubmitLocation, buttonFind;
    EditText editTextSearchLocation;
    TextView textViewAlamat, textViewLatitude, textViewLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_coordinate);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initializeComponent();

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextSearchLocation.getText().toString().equals("")) {
                    textViewAlamat.setText("" + editTextSearchLocation.getText().toString());
                    LatLng inputLocation = null;
                    inputLocation = getLocationFromAddress(editTextSearchLocation.getText().toString());
                    if (inputLocation != null) {
                        textViewLatitude.setText("Lat: " + inputLocation.latitude);
                        textViewLongitude.setText("Long: " + inputLocation.longitude);

                        String address = "";
                        address = getAddressFromLocation(inputLocation);
                        textViewAlamat.setText("" + address);
                        marker.setPosition(inputLocation);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(inputLocation, 16));
                    }
                }
            }
        });
    }

    private void initializeComponent() {
        buttonSubmitLocation = (Button) findViewById(R.id.button_submit_location);
        buttonFind = (Button) findViewById(R.id.buttonFind);
        editTextSearchLocation = (EditText) findViewById(R.id.editTextLocation);
        textViewAlamat = (TextView) findViewById(R.id.textViewAlamat);
        textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
    }

    private LatLng getLocationFromAddress(String _address) {
        Double _latitude = 0.0, _longitude = 0.0;
        if (Geocoder.isPresent()) {
            try {
                String location = _address;
                Geocoder gc = new Geocoder(this);
                List<Address> addresses = gc.getFromLocationName(location, 5); // get the found Address Objects

                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for (Address a : addresses) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                        _latitude = a.getLatitude();
                        _longitude = a.getLongitude();
                    }
                }
            } catch (IOException e) {
                // handle the exception
            }
        }
        return new LatLng(_latitude, _longitude);
    }

    private String getAddressFromLocation(LatLng location) {
        String address = "";
        List<Address> geocodeMatches = null;
        try {
            geocodeMatches = new Geocoder(MapsCoordinateActivity.this).getFromLocation(location.latitude, location.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!geocodeMatches.isEmpty()) {
            address = geocodeMatches.get(0).getAddressLine(0);
        }
        return address;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        final LatLng sydney = new LatLng(-34, 151);
        marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marker.setPosition(latLng);
                new SharedPreferenceManager().setPreferences(MapsCoordinateActivity.this, "latitude_shipping_address", "" + latLng.latitude);
                new SharedPreferenceManager().setPreferences(MapsCoordinateActivity.this, "longitude_shipping_address", "" + latLng.longitude);
                textViewLatitude.setText("Lat: " + latLng.latitude);
                textViewLongitude.setText("Long: " + latLng.longitude);
                String address = "";
                address = getAddressFromLocation(latLng);
                textViewAlamat.setText("" + address);

                if (marker != null) {
                    marker.remove();
                }

                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(address));
            }
        });
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        // Retrieve the data from the marker.
//        Integer clickCount = (Integer) marker.getTag();
//
//        // Check if a click count was set, then display the click count.
//        if (clickCount != null) {
//            clickCount = clickCount + 1;
//            marker.setTag(clickCount);
//            Toast.makeText(this, marker.getTitle() + " has been clicked " + clickCount + " times.", Toast.LENGTH_SHORT).show();
//        }
//
//        // Return false to indicate that we have not consumed the event and that we wish
//        // for the default behavior to occur (which is for the camera to move such that the
//        // marker is centered and for the marker's info window to open, if it has one).
//        return false;
//    }
}

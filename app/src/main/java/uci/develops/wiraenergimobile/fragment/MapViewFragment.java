package uci.develops.wiraenergimobile.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;

/**
 * Created by ArahitoPC on 12/6/2016.
 */
public class MapViewFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    Marker marker;
    TextView editTextDetailLocation;
    ImageView imageViewSearchAddress;
    Button buttonSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        editTextDetailLocation = (EditText)rootView.findViewById(R.id.editTextDetailLocation);
        imageViewSearchAddress = (ImageView)rootView.findViewById(R.id.imageViewSearchAddress);
        buttonSave = (Button)rootView.findViewById(R.id.buttonSave);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                marker = googleMap.addMarker(new MarkerOptions().position(sydney).title("Pilih lokasi"));
                marker.showInfoWindow();

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {
                        marker.setPosition(googleMap.getCameraPosition().target);
                        editTextDetailLocation.setText(""+getAddressFromLocation(googleMap.getCameraPosition().target));
                    }
                });

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        marker.setPosition(latLng);
                        editTextDetailLocation.setText(""+getAddressFromLocation(latLng));
                    }
                });
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonSave();
            }
        });

        imageViewSearchAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImageViewSearchAddress();
            }
        });

        return rootView;
    }

    private List<Address> geocodeMatches = null;
    private String getAddressFromLocation(LatLng location){
        String address="";
        try {
            geocodeMatches = new Geocoder(getContext()).getFromLocation(location.latitude, location.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!geocodeMatches.isEmpty()) {
            address = geocodeMatches.get(0).getAddressLine(0);
        }
        return address;
    }

    private LatLng getLocationFromAddress(String _address){
        Double _latitude=0.0, _longitude=0.0;
        if(Geocoder.isPresent()){
            try {
                String location = _address;
                Geocoder gc = new Geocoder(getContext());
                List<Address> addresses= gc.getFromLocationName(location, 5); // get the found Address Objects

                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for(Address a : addresses){
                    if(a.hasLatitude() && a.hasLongitude()){
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

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    void onClickButtonSave(){
        String detailLocation="";
        detailLocation = editTextDetailLocation.getText().toString();
        if(!detailLocation.equals("")){
            LatLng location = null;
            location = getLocationFromAddress(detailLocation);
            if(location != null){
                marker.setPosition(location);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }

    void onClickImageViewSearchAddress(){
        String detailLocation="";
        detailLocation = editTextDetailLocation.getText().toString();
        if(!detailLocation.equals("")){
            LatLng location = null;
            location = getLocationFromAddress(detailLocation);
            if(location != null){
                marker.setPosition(location);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }
}


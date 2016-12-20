package uci.develops.wiraenergimobile.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import uci.develops.wiraenergimobile.helper.PlacesAutoCompleteAdapter;

/**
 * Created by ArahitoPC on 12/6/2016.
 */
public class MapViewFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    Marker marker;
    AutoCompleteTextView editTextDetailLocation;
    Button buttonSave;

    private PlacesAutoCompleteAdapter mAdapter;

    HandlerThread mHandlerThread;
    Handler mThreadHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        editTextDetailLocation = (AutoCompleteTextView)rootView.findViewById(R.id.editTextDetailLocation);
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
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                marker = googleMap.addMarker(new MarkerOptions().position(sydney).title("Pilih lokasi"));
                marker.showInfoWindow();

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        marker.setPosition(latLng);
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

        setupAutoCompleteDetailLocation();

        return rootView;
    }

    String value = "";
    void setupAutoCompleteDetailLocation(){
        mAdapter = new PlacesAutoCompleteAdapter(getContext(), R.layout.item_autocomplete_location);
        editTextDetailLocation.setAdapter(mAdapter);

        editTextDetailLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                value = s.toString();
                //value = "";
                //value = s.toString()+" "+editTextOrigin.getText().toString();

                if (mThreadHandler == null) {
                    // Initialize and start the HandlerThread
                    // which is basically a Thread with a Looper
                    // attached (hence a MessageQueue)
                    mHandlerThread = new HandlerThread("TAG", android.os.Process.THREAD_PRIORITY_BACKGROUND);
                    mHandlerThread.start();

                    // Initialize the Handler
                    mThreadHandler = new Handler(mHandlerThread.getLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 1) {
                                ArrayList<String> results = mAdapter.resultList;

                                if (results != null && results.size() > 0) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        public void run() {
                                            mAdapter.notifyDataSetChanged();
                                        }

                                    });
                                }
                                else {
                                    getActivity().runOnUiThread(new Runnable() {
                                        public void run() {
                                            mAdapter.notifyDataSetChanged();
                                        }

                                    });
                                }
                            }
                        }
                    };
                }

                // Remove all callbacks and messages
                mThreadHandler.removeCallbacksAndMessages(null);

                // Now add a new one
                mThreadHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // Background thread

                        mAdapter.resultList = mAdapter.mPlaceAPI.autocomplete(value);

                        // Footer
                        /*if (mAdapter.resultList.size() > 0)
                            mAdapter.resultList.add("footer");*/

                        // Post to Main Thread
                        mThreadHandler.sendEmptyMessage(1);
                    }
                }, 500);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //doAfterTextChanged();
            }
        });

        editTextDetailLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                LatLng location = getLocationFromAddress(description);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                marker.setPosition(location);
                //Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
        });
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

                FragmentFormCustomerShippingTo.addressDialog = detailLocation;

                FragmentFormCustomerShippingTo.latitudeDialog = ""+location.latitude;
                FragmentFormCustomerShippingTo.longitudeDialog = ""+location.longitude;

                Intent pushNotification = new Intent("pushNotification");
                pushNotification.putExtra("type", "dismiss_dialog_maps");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(pushNotification);
            }
        }
    }
}


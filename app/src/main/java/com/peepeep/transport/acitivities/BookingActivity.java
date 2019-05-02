package com.peepeep.transport.acitivities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.peepeep.transport.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity implements OnMapReadyCallback,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private Location mLastLocation = null;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private EditText timeEditText;
    private EditText dateEditText;
    private LatLng mSourceLatLng, mDestLatLng;
    boolean isToSelected = false, isFromSelected= false;
    private EditText toEditText ;
    private EditText fromEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyB98Gd6FtUQ-q01QAzAQg0lE4A0IKSygII");
        toEditText = (EditText) findViewById(R.id.tolocation_edittext);
        fromEditText = (EditText) findViewById(R.id.fromlocation_edittext);

        final GestureDetector gestureDetector = new GestureDetector(BookingActivity.this, new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                // Create a new Places client instance.
                PlacesClient placesClient = Places.createClient(BookingActivity.this);
                // Set the fields to specify which types of place data to return.
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ADDRESS );
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(BookingActivity.this);
                startActivityForResult(intent, 1);
                return true;
            }
        });

        toEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                isToSelected = true;
                isFromSelected= false;
                return gestureDetector.onTouchEvent(event);
            }
        });


        fromEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                isFromSelected = true;
                isToSelected = false;
                return gestureDetector.onTouchEvent(event);
            }
        });


        ImageView dateImageView = (ImageView) findViewById(R.id.date_picker);
        dateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        ImageView timeImageView = (ImageView) findViewById(R.id.time_picker);
        timeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        timeEditText = (EditText) findViewById(R.id.time_editText);
        dateEditText = (EditText) findViewById(R.id.date_editText);
        Calendar newDate = Calendar.getInstance();
        timeEditText.setText(convertDateToStringHmma(newDate.getTime()));
        dateEditText.setText(convertDateToStringMMddyyyy(newDate.getTime()));

        Button drawButton = (Button) findViewById(R.id.drawline);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSourceLatLng != null && mDestLatLng != null) {
                    drapLin(mSourceLatLng.latitude, mSourceLatLng.longitude, mDestLatLng.latitude, mDestLatLng.longitude);
                } else {
                    Toast.makeText(BookingActivity.this, "please select position", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                Log.i("centerLat", cameraPosition.target.latitude + "");
                Log.i("centerLong", cameraPosition.target.longitude + "");
            }
        });


        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                double lat = mMap.getCameraPosition().target.latitude;
                double lng = mMap.getCameraPosition().target.longitude;
                Log.i("centerLat", lat + "");
                Log.i("centerLong", lng + "");

                if(isFromSelected) {
                    mDestLatLng = new LatLng(lat,lng);
                }
                if(isToSelected) {
                    mSourceLatLng = new LatLng(lat,lng);
                }


            }
        });
        this.mMap.getUiSettings().setMyLocationButtonEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        11);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        //move map camera
        if (mLastLocation == null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(8.0f));
        }

        mLastLocation = location;
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if(isFromSelected) {
                    mDestLatLng = place.getLatLng();
                    fromEditText.setText(place.getAddress());
                }
                if(isToSelected) {
                    mSourceLatLng = place.getLatLng();
                    toEditText.setText(place.getAddress());
                }
                Log.i("ksbkz", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(",jhxgfb", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    public void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        dateEditText.setText(convertDateToStringMMddyyyy(newDate.getTime()));

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(c.getTime().getTime());
        datePickerDialog.show();
    }

    public void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                                c.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                        timeEditText.setText(convertDateToStringHmma(newDate.getTime()));
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    public static String convertDateToStringHmma(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static String convertDateToStringMMddyyyy(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }

    private void drapLin(double sourcelat, double sourcelog, double destlat, double destlog) {
        LatLng barcelona = new LatLng(sourcelat, sourcelog);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Marker in Barcelona"));

        LatLng madrid = new LatLng(destlat, destlog);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid"));

//        LatLng zaragoza = new LatLng(41.648823,-0.889085);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyB98Gd6FtUQ-q01QAzAQg0lE4A0IKSygII")
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, sourcelat + "," + sourcelog, destlat + "," + destlog);
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("TAG", ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zaragoza, 6));
    }
}
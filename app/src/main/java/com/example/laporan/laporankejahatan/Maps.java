package com.example.laporan.laporankejahatan;

import android.renderscript.Double2;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Laporan");

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user= firebaseAuth.getCurrentUser();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long c = dataSnapshot.getChildrenCount()-1;
                String lat = dataSnapshot.child(user.getUid()).child("Statistik").child(Long.toString(c)).child("latitude").getValue().toString();
                String lo = dataSnapshot.child(user.getUid()).child("Statistik").child(Long.toString(c)).child("longitude").getValue().toString();

                Double a = Double.parseDouble(lat);
                Double b = Double.parseDouble(lo);
                Log.d("", "Value is: " + c + " " + a + " "+ b  );



                LatLng indo = new LatLng(a, b);
                mMap.addMarker(new MarkerOptions().position(indo).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(indo));
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        // Add a marker in Sydney and move the camera

    }
}

package com.example.laporan.laporankejahatan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Lokasi extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference  databaseReference;
    private FirebaseAuth firebaseAuth;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reee = database.getReference("Laporan");


    private EditText editTextLong, editTextLat;


    static final int REQUEST_LOCATION= 1;
    LocationManager locationManager;
    private Button buttonSend;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();

        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextLat = (EditText)findViewById(R.id.etLocationLat);
        editTextLong =  (EditText)findViewById(R.id.etLocationLong);
        buttonSend = (Button) findViewById(R.id.buttonSend);

    }



    void getLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location!=null){
                double latti = location.getLatitude();
                double longi = location.getLongitude();

                ((EditText)findViewById(R.id.etLocationLat)).setText(Double.toString(latti));
                ((EditText)findViewById(R.id.etLocationLong)).setText(Double.toString(longi));
            }
            else{
                ((EditText)findViewById(R.id.etLocationLat)).setText("Unable to find correct location");
                ((EditText)findViewById(R.id.etLocationLong)).setText("Unable to find correct location");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    private void saveLocation(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        reee.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String latitude = editTextLat.getText().toString().trim();
                String longitude = editTextLong.getText().toString().trim();
                DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date tgl= new Date();
                String tanggal = dateFormat.format(tgl);
                SaveLoc loc = new SaveLoc(latitude,longitude,tanggal);

                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user= firebaseAuth.getCurrentUser();
                // Write a message to the database

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Laporan");
                DatabaseReference myRef2 = myRef.child(user.getUid());
                DatabaseReference myRef3 = myRef2.child("Statistik");

                DatabaseReference myRef4 = myRef3.child(Long.toString(dataSnapshot.child(user.getUid()).child("Statistik").getChildrenCount()));
                myRef4.setValue(loc);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        Toast.makeText(this, "mengirim lokasi", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View view) {
        if (view==buttonSend){
            saveLocation();
            finish();
            startActivity(new Intent(this, Maps.class));
        }

        //startActivity(new Intent(getApplicationContext(), Tampil.class));
    }
}

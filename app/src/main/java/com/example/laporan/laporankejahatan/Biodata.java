package com.example.laporan.laporankejahatan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Biodata extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextKTP;
    private EditText editTextNama;
    private EditText editTextAlamat;
    private EditText editTextNoTelp;
    private EditText editTextUsia;
    private Button buttonSave;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextKTP = (EditText) findViewById(R.id.editTextKTP);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextUsia = (EditText) findViewById(R.id.editTextUsia);
        editTextNoTelp = (EditText) findViewById(R.id.editTextNoTelp);

        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        if(view == buttonSave){
            SimpanData();
            finish();
            startActivity(new Intent(this, navigasi.class));
        }
    }

    private void SimpanData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reee = database.getReference("Laporan");

        String ktp = editTextKTP.getText().toString().trim();
        String nama = editTextNama.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();
        String usia = editTextUsia.getText().toString().trim();
        String telp = editTextNoTelp.getText().toString().trim();

        if(TextUtils.isEmpty(ktp)){
            Toast.makeText(this, "Please enter your KTP", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(nama)){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(alamat)){
            Toast.makeText(this, "Please enter your alamat", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(usia)){
            Toast.makeText(this, "Please enter your usia", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(telp)){
            Toast.makeText(this, "Please enter your telepon", Toast.LENGTH_SHORT).show();
            return;
        }

        SaveUser dat = new SaveUser(ktp,nama,alamat,telp,usia);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();

        DatabaseReference myRef = database.getReference("Laporan");
        DatabaseReference myRef2 = myRef.child(user.getUid());
        DatabaseReference myRef3 = myRef2.child("Biodata");
        myRef3.setValue(dat);

    }
}

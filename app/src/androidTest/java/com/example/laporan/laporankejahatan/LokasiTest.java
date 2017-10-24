package com.example.laporan.laporankejahatan;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by HP on 18/10/2017.
 */
public class LokasiTest {

    @Rule
    public ActivityTestRule<Lokasi> lokasiActivityTestRule = new ActivityTestRule<Lokasi>(Lokasi.class);
    private Lokasi loka = null;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reee = database.getReference("Laporan");

    long  b = 0;

    @Before
    public void setUp() throws Exception {
        Intents.init();
        loka = lokasiActivityTestRule.getActivity();
        FirebaseAuth.getInstance().signInWithEmailAndPassword("magrid@gmail.com", "123456");
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
        loka = null;
        FirebaseAuth.getInstance().signOut();
    }

    public void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLokasi() {
        pause(2000);
        reee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseAuth firebaseAuth;
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                b = dataSnapshot.child(user.getUid()).child("Statistik").getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        onView(withId(R.id.buttonSend)).perform(click());
        assertEquals(16, b);
    }
}

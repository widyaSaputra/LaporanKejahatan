package com.example.laporan.laporankejahatan;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    public void testLokasi(){
        onView(withId(R.id.buttonSend)).perform(click());
        pause(2000);
        //intended(hasComponent(Maps.class.getName()));
    }

}
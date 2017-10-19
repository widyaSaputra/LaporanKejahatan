package com.example.laporan.laporankejahatan;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by HP on 17/10/2017.
 */
public class BiodataTest {

    @Rule
    public ActivityTestRule<Biodata> biodataTest = new ActivityTestRule<Biodata>(Biodata.class);
    private Biodata biodata=null;

    @Before
    public void setUp() throws Exception {
        Intents.init();
        biodata = biodataTest.getActivity();
        FirebaseAuth.getInstance().signInWithEmailAndPassword("magrid@gmail.com", "123456");
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
        biodata = null;
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
    public void testLogin()throws Exception{
//        biodataTest.launchActivity(null);
        onView(withId(R.id.editTextKTP)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextNama)).perform(typeText("joko"), closeSoftKeyboard());
        onView(withId(R.id.editTextAlamat)).perform(typeText("jakarta"), closeSoftKeyboard());
        onView(withId(R.id.editTextNoTelp)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.editTextUsia)).perform(typeText("12"), closeSoftKeyboard());

        onView(withId(R.id.buttonSave)).perform(click());
        pause(2000);
        intended(hasComponent(navigasi.class.getName()));
    }
    @Test
    public void testBioWithoutKTP(){
//        biodataTest.launchActivity(null);
        onView(withId(R.id.editTextKTP)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextNama)).perform(typeText("joko"), closeSoftKeyboard());
        onView(withId(R.id.editTextAlamat)).perform(typeText("jakarta"), closeSoftKeyboard());
        onView(withId(R.id.editTextNoTelp)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.editTextUsia)).perform(typeText("12"), closeSoftKeyboard());

        onView(withId(R.id.buttonSave)).perform(click());
        pause(500);
        onView(withText("Please enter your KTP"))
                .inRoot(withDecorView(not(biodataTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
@Test
    public void testBioWithoutNama(){
//    biodataTest.launchActivity(null);
        onView(withId(R.id.editTextKTP)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextNama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextAlamat)).perform(typeText("jakarta"), closeSoftKeyboard());
        onView(withId(R.id.editTextNoTelp)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.editTextUsia)).perform(typeText("12"), closeSoftKeyboard());

        onView(withId(R.id.buttonSave)).perform(click());
        pause(1000);
        onView(withText("Please enter your name"))
                .inRoot(withDecorView(not(biodataTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
    @Test
    public void testBioWithoutAlamat(){
//        biodataTest.launchActivity(null);
        onView(withId(R.id.editTextKTP)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextNama)).perform(typeText("joko"), closeSoftKeyboard());
        onView(withId(R.id.editTextAlamat)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextNoTelp)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.editTextUsia)).perform(typeText("27"), closeSoftKeyboard());

        onView(withId(R.id.buttonSave)).perform(click());
        pause(500);
        onView(withText("Please enter your alamat"))
                .inRoot(withDecorView(not(biodataTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
    @Test
    public void testBioWithoutUsia(){
//        biodataTest.launchActivity(null);
        onView(withId(R.id.editTextKTP)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextNama)).perform(typeText("joko"), closeSoftKeyboard());
        onView(withId(R.id.editTextAlamat)).perform(typeText("jakarta"), closeSoftKeyboard());
        onView(withId(R.id.editTextNoTelp)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.editTextUsia)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.buttonSave)).perform(click());
        pause(1000);
        onView(withText("Please enter your usia"))
                .inRoot(withDecorView(not(biodataTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
    @Test
    public void testBioWithoutTelepon(){
//        biodataTest.launchActivity(null);
        onView(withId(R.id.editTextKTP)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.editTextNama)).perform(typeText("joko"), closeSoftKeyboard());
        onView(withId(R.id.editTextAlamat)).perform(typeText("jakarta"), closeSoftKeyboard());
        onView(withId(R.id.editTextNoTelp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextUsia)).perform(typeText("12"), closeSoftKeyboard());

        onView(withId(R.id.buttonSave)).perform(click());
        pause(1000);
        onView(withText("Please enter your telepon"))
                .inRoot(withDecorView(not(biodataTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void onClick() throws Exception {

    }

}
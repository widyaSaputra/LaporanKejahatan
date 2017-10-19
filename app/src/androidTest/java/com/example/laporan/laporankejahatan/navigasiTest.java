package com.example.laporan.laporankejahatan;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
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
public class navigasiTest {

//    @Rule
//    public ActivityTestRule<navigasi> naviTest = new ActivityTestRule<navigasi>(navigasi.class);
//    private navigasi navi = null;
//
//    @Before
//    public void setUp() throws Exception {
//        Intents.init();
//        navi = naviTest.getActivity();
//
//        FirebaseAuth.getInstance().signOut();
//        AuthCredential aut = EmailAuthProvider.getCredential("magrid@gmail.com","123456");
//        FirebaseAuth.getInstance().signInWithCredential(aut);
//        FirebaseAuth.getInstance().signInWithEmailAndPassword("magrid@gmail.com", "123456");
//
//    }

//    @After
//    public void tearDown() throws Exception {
//        Intents.release();
//        navi = null;
//    }
//
//    @Test
//    public void testNavigasiTambah(){
//        onView(withId(R.menu.navigasi)).perform(click());
//        pause(1000);
//        //intended(hasComponent(MainActivity.class.getName()));
//    }
//
//    public void pause(long time) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
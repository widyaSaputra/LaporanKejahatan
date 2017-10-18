package com.example.laporan.laporankejahatan;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
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
public class LoginTest {
    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<Login>(Login.class);
    private Login login=null;

    @Before
    public void setUp() throws Exception {

        Intents.init();
        login = loginActivityTestRule.getActivity();
        //FirebaseAuth.getInstance().signInWithEmailAndPassword("magrid@gmail.com", "123456");


    }



    @Test
    public void testLoginWithoutEmail() throws Exception{
        FirebaseAuth.getInstance().signOut();

        onView(withId(R.id.editTextEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.buttonSignin)).perform(click());
        pause(1000);
        onView(withText("Please enter your mail"))
                .inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginWithoutpassword() throws Exception{
        //FirebaseAuth.getInstance().signOut();
        onView(withId(R.id.editTextPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("asdfghjk@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.buttonSignin)).perform(click());
        pause(1000);
        onView(withText("Please enter your password"))
                .inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSignUp() throws Exception{
        FirebaseAuth.getInstance().signOut();

        onView(withId(R.id.textViewSignup)).perform(click());
        pause(1000);
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void testLogin()throws Exception{
        FirebaseAuth.getInstance().signOut();

        onView(withId(R.id.editTextEmail)).perform(typeText("magrid@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.buttonSignin)).perform(click());
        pause(3000);
        //onView(withId(R.)).perform(click());
        intended(hasComponent(navigasi.class.getName()));
        FirebaseAuth.getInstance().signOut();
    }

    @Test
    public void testLoginWithNavigasi()throws Exception{
        FirebaseAuth.getInstance().signOut();

        onView(withId(R.id.editTextEmail)).perform(typeText("magrid@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.buttonSignin)).perform(click());
        pause(3000);
        intended(hasComponent(navigasi.class.getName()));
        FirebaseAuth.getInstance().signOut();
    }

    @Test
    public void testLoginFailed() throws Exception{
        onView(withId(R.id.editTextEmail)).perform(typeText("jfghffg@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("234876"), closeSoftKeyboard());
        onView(withId(R.id.buttonSignin)).perform(click());
        pause(2000);
        onView(withText("Couldnt login. please try again"))
                .inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }



    public void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @After
    public void tearDown() throws Exception {
        Intents.release();
        login = null;
        FirebaseAuth.getInstance().signOut();
    }

}
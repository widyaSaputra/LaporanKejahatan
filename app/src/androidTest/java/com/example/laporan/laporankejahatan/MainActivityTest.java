package com.example.laporan.laporankejahatan;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by HP on 14/10/2017.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
    private EditText editTextEmail;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Biodata.class.getName(),null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunch(){

    }

    @Test
    public void testRegister(){

        View view = mActivity.findViewById(R.id.editTextEmail);
        assertNotNull(view);

        View aa = mActivity.findViewById(R.id.editTextPassword);
        assertNotNull(aa);

        View text = mActivity.findViewById(R.id.textViewSignin);
        assertNotNull(text);

        assertNotNull(mActivity.findViewById(R.id.buttonRegister));

//        onView(withId(R.id.buttonRegister)).perform(click());

    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}
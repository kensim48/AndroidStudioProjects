package com.example.kensi.finals01;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.kensi.finals01", appContext.getPackageName());
    }

    @Test
    public void test1(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText("900"));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText("1000"));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText("1"));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText("0"));
        onView(withId(R.id.buttonCalculateYield))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("0.11111111111111116")));
    }

    @Test
    public void test2(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText(""));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText(""));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText(""));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText(""));
        onView(withId(R.id.buttonCalculateYield))
                .perform(click());


        onView(withId(R.id.textViewResult))
                .check(matches(withText("result here")));
    }

    @Test
    public void test3(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText("a"));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText("b"));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText("c"));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText("d"));


        onView(withId(R.id.buttonCalculateYield))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("result here")));
    }

    @Test
    public void test4(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText("12"));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText("24"));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText("16"));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText("d"));


        onView(withId(R.id.buttonCalculateYield))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("result here")));
    }

    @Test
    public void test5(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText("12"));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText(" "));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText("16"));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText("18"));


        onView(withId(R.id.buttonCalculateYield))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("result here")));
    }

    @Test
    public void test6(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText("a"));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText(" "));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText("16"));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText("18"));


        onView(withId(R.id.buttonCalculateYield))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("result here")));
    }

    @Test
    public void test7(){
        onView(withId(R.id.editTextSellingPrice))
                .perform(replaceText("c"));
        onView(withId(R.id.editTextFaceValue))
                .perform(replaceText("15"));
        onView(withId(R.id.editTextDuration))
                .perform(replaceText(""));
        onView(withId(R.id.editTextAnnualInterest))
                .perform(replaceText(""));


        onView(withId(R.id.buttonCalculateYield))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("result here")));
    }
}

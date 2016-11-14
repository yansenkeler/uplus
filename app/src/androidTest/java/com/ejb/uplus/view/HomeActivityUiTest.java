package com.ejb.uplus.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ejb.uplus.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by John on 11/3/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityUiTest implements IdlingResource
{
    @Rule
    public ActivityTestRule<LoginActivity> homeActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void sample()
    {
        Espresso.onView(ViewMatchers.withId(R.id.mobile_input)).perform(ViewActions.typeText("qianyx"));
        Espresso.onView(ViewMatchers.withId(R.id.password_input)).perform(ViewActions.typeText("1234567"));
        Espresso.onView(ViewMatchers.withText("注册")).perform(ViewActions.click());
//        Espresso.onView(ViewMatchers.withText("登录")).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();

    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public boolean isIdleNow()
    {
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback)
    {

    }
}
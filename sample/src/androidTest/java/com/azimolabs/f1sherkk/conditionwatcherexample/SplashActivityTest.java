package com.azimolabs.f1sherkk.conditionwatcherexample;

import android.content.Intent;

import com.azimolabs.f1sherkk.conditionwatcherexample.Screens.Screen;
import com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources.BtnStartAnimationIdlingResource;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.SplashActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition;

public class SplashActivityTest {
    @Rule
    public ActivityTestRule<SplashActivity> activityRule =
            new ActivityTestRule<>(SplashActivity.class, false, true);


    @Test
    public void testStartButton() {
        BtnStartAnimationIdlingResource btnStartAnimationIdlingResource = new BtnStartAnimationIdlingResource();
        IdlingRegistry.getInstance().register(btnStartAnimationIdlingResource);
        onView(withId(R.id.btnStart)).perform(click());
        IdlingRegistry.getInstance().unregister(btnStartAnimationIdlingResource);
    }
}

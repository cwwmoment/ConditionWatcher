package com.azimolabs.f1sherkk.conditionwatcherexample.Screens;

import android.app.Activity;
import android.content.Intent;

import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.ListActivity;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.SplashActivity;

import org.junit.Rule;

import androidx.test.rule.ActivityTestRule;

public class Screen {
    private ActivityTestRule<? extends Activity> currentRule;

    public Screen(ActivityTestRule<? extends Activity> activityRule, Intent intent) {
        currentRule = activityRule;
        activityRule.launchActivity(intent);
    }

    public void finish() {
        currentRule.finishActivity();
    }

}

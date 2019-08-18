package com.azimolabs.f1sherkk.conditionwatcherexample;

import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.azimolabs.conditionwatcher.ConditionWatcher;
import com.azimolabs.f1sherkk.conditionwatcherexample.data.Server;
import com.azimolabs.f1sherkk.conditionwatcherexample.instruction.BtnStartAnimationInstruction;
import com.azimolabs.f1sherkk.conditionwatcherexample.instruction.LoadingDialogInstruction;
import com.azimolabs.f1sherkk.conditionwatcherexample.instruction.ServerListLoadingInstruction;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.SplashActivity;
import com.azimolabs.f1sherkk.conditionwatcherexample.utils.DataProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class ConditionWatcherExampleTests {

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class, false, false);

    @Before
    public void setUp() {
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void shouldDisplayServerDetails_conditionWatcher() throws Exception {
        List<Server> servers = DataProvider.generateServerList();
        Server thirdServer = servers.get(2);

        // SplashActivity
        ConditionWatcher.waitForCondition(new BtnStartAnimationInstruction());
        onView(withId(R.id.btnStart)).perform(click());

        // ListActivity
        ConditionWatcher.waitForCondition(new ServerListLoadingInstruction());
        onData(anything()).inAdapterView(withId(R.id.lvList)).atPosition(2).perform(click());

        // DetailsActivity
        ConditionWatcher.waitForCondition(new LoadingDialogInstruction());
        onView(withText(thirdServer.getName())).check(matches(isDisplayed()));
        onView(withText(thirdServer.getAddress())).check(matches(isDisplayed()));
        onView(withText(thirdServer.getPort())).check(matches(isDisplayed()));
    }
}
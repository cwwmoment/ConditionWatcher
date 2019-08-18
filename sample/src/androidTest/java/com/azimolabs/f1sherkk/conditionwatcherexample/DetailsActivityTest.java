package com.azimolabs.f1sherkk.conditionwatcherexample;

import android.content.Intent;

import com.azimolabs.f1sherkk.conditionwatcherexample.Screens.Screen;
import com.azimolabs.f1sherkk.conditionwatcherexample.data.Server;
import com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources.LoadingDialogIdlingResource;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.DetailsActivity;
import com.azimolabs.f1sherkk.conditionwatcherexample.utils.DataProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;

public class DetailsActivityTest {
    @Rule
    public ActivityTestRule<DetailsActivity> activityRule =
            new ActivityTestRule<>(DetailsActivity.class, false, false);

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.putExtra(DetailsActivity.KEY_SERVER, DataProvider.generateServerList().get(2));
        activityRule.launchActivity(intent);
    }

    @Test
    public void testSelectThirdServerButton() {
        LoadingDialogIdlingResource loadingDialogIdlingResource = new LoadingDialogIdlingResource();
        IdlingRegistry.getInstance().register(loadingDialogIdlingResource);
        List<Server> servers = DataProvider.generateServerList();
        Server thirdServer = servers.get(2);
        onView(withText(thirdServer.getName())).check(matches(isDisplayed()));
        onView(withText(thirdServer.getAddress())).check(matches(isDisplayed()));
        onView(withText(thirdServer.getPort())).check(matches(isDisplayed()));
        IdlingRegistry.getInstance().unregister(loadingDialogIdlingResource);

    }

}

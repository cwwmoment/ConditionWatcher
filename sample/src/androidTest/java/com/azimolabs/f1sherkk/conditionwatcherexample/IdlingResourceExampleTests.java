package com.azimolabs.f1sherkk.conditionwatcherexample;

import android.content.Intent;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.azimolabs.f1sherkk.conditionwatcherexample.data.Server;
import com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources.BtnStartAnimationIdlingResource;
import com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources.LoadingDialogIdlingResource;
import com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources.ServerListLoadingIdlingResource;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.SplashActivity;
import com.azimolabs.f1sherkk.conditionwatcherexample.utils.DataProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static org.hamcrest.Matchers.anything;

public class IdlingResourceExampleTests {

    @Rule
    public ActivityTestRule<SplashActivity> activityRule =
            new ActivityTestRule<>(SplashActivity.class, false, false);

    @Before
    public void setUp() {
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void shouldDisplayServerDetails_idlingResource() throws Exception {
        List<Server> servers = DataProvider.generateServerList();
        Server thirdServer = servers.get(2);

        IdlingPolicies.setMasterPolicyTimeout(
                1000 * 30, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(
                1000 * 30, TimeUnit.MILLISECONDS);

        BtnStartAnimationIdlingResource btnStartAnimationIdlingResource = new BtnStartAnimationIdlingResource();
        ServerListLoadingIdlingResource serverListLoadingIdlingResource = new ServerListLoadingIdlingResource();
        LoadingDialogIdlingResource loadingDialogIdlingResource = new LoadingDialogIdlingResource();

        // SplashActivity
        IdlingRegistry.getInstance().register(btnStartAnimationIdlingResource);
        onView(withId(R.id.btnStart)).perform(click());
        IdlingRegistry.getInstance().unregister(btnStartAnimationIdlingResource);

        // ListActivity
        IdlingRegistry.getInstance().register(serverListLoadingIdlingResource);
        onData(anything()).inAdapterView(withId(R.id.lvList)).atPosition(2).perform(click());
        IdlingRegistry.getInstance().unregister(serverListLoadingIdlingResource);

        // DetailsActivity
        IdlingRegistry.getInstance().register(loadingDialogIdlingResource);
        onView(withText(thirdServer.getName())).check(matches(isDisplayed()));
        onView(withText(thirdServer.getAddress())).check(matches(isDisplayed()));
        onView(withText(thirdServer.getPort())).check(matches(isDisplayed()));
        IdlingRegistry.getInstance().unregister(serverListLoadingIdlingResource);
    }

    @Test
    public void test() throws Exception {
        BtnStartAnimationIdlingResource btnStartAnimationIdlingResource = new BtnStartAnimationIdlingResource();

        // SplashActivity
        IdlingRegistry.getInstance().register(btnStartAnimationIdlingResource);
        onView(withId(R.id.btnStart)).perform(click());
        IdlingRegistry.getInstance().unregister(btnStartAnimationIdlingResource);

        // ListActivity
//        onData(anything()).inAdapterView(withId(R.id.lvList)).atPosition(2).perform(click());
        ServerListLoadingIdlingResource serverListLoadingIdlingResource = new ServerListLoadingIdlingResource();
        IdlingRegistry.getInstance().register(serverListLoadingIdlingResource);
        clickListItem(R.id.lvList, 2);
        IdlingRegistry.getInstance().unregister(serverListLoadingIdlingResource);

    }
}
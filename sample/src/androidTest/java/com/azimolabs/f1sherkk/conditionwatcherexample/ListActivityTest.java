package com.azimolabs.f1sherkk.conditionwatcherexample;

import android.content.Intent;

import com.azimolabs.f1sherkk.conditionwatcherexample.Screens.ListScreen;
import com.azimolabs.f1sherkk.conditionwatcherexample.Screens.Screen;
import com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources.ServerListLoadingIdlingResource;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.activity.ListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickBack;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static com.schibsted.spain.barista.interaction.BaristaSwipeRefreshInteractions.refresh;

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {
    @Rule
    public ActivityTestRule<ListActivity> rule = new ActivityTestRule<>(ListActivity.class, false, false);

    private ServerListLoadingIdlingResource serverListLoadingIdlingResource;
    private ListScreen listScreen;

    @Before
    public void setUp() {
        listScreen = new ListScreen(rule, new Intent());
        serverListLoadingIdlingResource = new ServerListLoadingIdlingResource();
        IdlingRegistry.getInstance().register(serverListLoadingIdlingResource);
    }

    @After
    public void finish() {
        IdlingRegistry.getInstance().unregister(serverListLoadingIdlingResource);
        listScreen.finish();
    }


    @Test
    public void testList() {
        listScreen.refreshPage();
        clickListItem(R.id.lvList, 2);
        clickBack();
    }

//    @Test
//    public void testRefresh() {
//        assertListItemCount(R.id.lvList, 5);
//        listScreen.refreshPage();
//        assertListItemCount(R.id.lvList, 5);
//    }

}

package com.azimolabs.f1sherkk.conditionwatcherexample.Screens;

import android.app.Activity;
import android.content.Intent;

import com.azimolabs.f1sherkk.conditionwatcherexample.R;

import androidx.test.rule.ActivityTestRule;

import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount;
import static com.schibsted.spain.barista.interaction.BaristaSwipeRefreshInteractions.refresh;

public class ListScreen extends Screen{

    public ListScreen(ActivityTestRule<? extends Activity> activityRule, Intent intent) {
        super(activityRule, intent);
    }

    public void refreshPage() {
        assertListItemCount(R.id.lvList, 5);
        refresh(R.id.srItemList);
        assertListItemCount(R.id.lvList, 5);
    }
}

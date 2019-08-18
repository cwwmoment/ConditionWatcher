package com.azimolabs.f1sherkk.conditionwatcherexample.idlingResources;

import android.app.Activity;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingResource;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.azimolabs.f1sherkk.conditionwatcherexample.R;
import com.azimolabs.f1sherkk.conditionwatcherexample.utils.TestApplication;

/**
 * Created by F1sherKK on 14/04/16.
 */
public class ServerListLoadingIdlingResource implements IdlingResource {
    private ResourceCallback resourceCallback;
    private boolean isIdle;

    @Override
    public String getName() {
        return ServerListLoadingIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        if (isIdle) return true;

        Activity activity = getCurrentActivity();
        if (activity == null) return false;

        SwipeRefreshLayout srItemList = (SwipeRefreshLayout) activity.findViewById(R.id.srItemList);
        isIdle = (srItemList != null && !srItemList.isRefreshing());
        if (isIdle) {
            resourceCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    public Activity getCurrentActivity() {
        return ((TestApplication) ApplicationProvider.getApplicationContext()).getCurrentActivity();
    }

    @Override
    public void registerIdleTransitionCallback(
            ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}

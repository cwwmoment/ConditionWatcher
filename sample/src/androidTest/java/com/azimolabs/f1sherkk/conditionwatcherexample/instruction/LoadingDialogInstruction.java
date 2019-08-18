package com.azimolabs.f1sherkk.conditionwatcherexample.instruction;

import android.app.Activity;
import android.app.DialogFragment;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import com.azimolabs.conditionwatcher.Instruction;
import com.azimolabs.f1sherkk.conditionwatcherexample.ui.dialog.LoadingDialog;
import com.azimolabs.f1sherkk.conditionwatcherexample.utils.TestApplication;

/**
 * Created by F1sherKK on 15/04/16.
 */
public class LoadingDialogInstruction extends Instruction {
    @Override
    public String getDescription() {
        return "Loading dialog shouldn't be in view hierarchy";
    }

    @Override
    public boolean checkCondition() {
        Activity activity = ((TestApplication)
                ApplicationProvider.getApplicationContext()).getCurrentActivity();
        if (activity == null) return false;

        DialogFragment f = (DialogFragment) activity.getFragmentManager().findFragmentByTag(LoadingDialog.TAG);
        return f == null;
    }
}

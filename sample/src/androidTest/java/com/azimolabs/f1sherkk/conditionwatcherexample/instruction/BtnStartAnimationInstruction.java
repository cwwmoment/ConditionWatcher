package com.azimolabs.f1sherkk.conditionwatcherexample.instruction;

import android.app.Activity;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import android.widget.Button;

import com.azimolabs.conditionwatcher.Instruction;
import com.azimolabs.f1sherkk.conditionwatcherexample.R;
import com.azimolabs.f1sherkk.conditionwatcherexample.utils.TestApplication;

/**
 * Created by F1sherKK on 15/04/16.
 */
public class BtnStartAnimationInstruction extends Instruction {

    @Override
    public String getDescription() {
        return "BtnStart should be moved to center of activity";
    }

    @Override
    public boolean checkCondition() {
        Activity activity = ((TestApplication)
                ApplicationProvider.getApplicationContext()).getCurrentActivity();
        if (activity == null) return false;

        Button btnStart = (Button) activity.findViewById(R.id.btnStart);
        return btnStart != null && btnStart.getTranslationY() == 0;
    }
}

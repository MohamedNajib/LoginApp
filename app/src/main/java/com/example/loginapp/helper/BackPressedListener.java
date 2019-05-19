package com.example.loginapp.helper;

import android.support.v4.app.FragmentActivity;

public class BackPressedListener implements OnBackPressedListener{

    private final FragmentActivity activity;

    public BackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBackPressed() {
        if (!activity.getSupportFragmentManager().popBackStackImmediate()) {
            activity.supportFinishAfterTransition();
        }
    }
}

package com.example.loginapp.ui.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.loginapp.helper.HelperMethod;
import com.example.loginapp.R;
import com.example.loginapp.helper.OnBackPressedListener;
import com.example.loginapp.ui.fragments.ForgotPasswordSecondStepFragment;
import com.example.loginapp.ui.fragments.LoginFragment;
import com.example.loginapp.ui.fragments.ProfileFragment;

public class UserCycleActivity extends AppCompatActivity {

    protected OnBackPressedListener onBackPressedListener;
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        HelperMethod.replaceFragments(new LoginFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
        //HelperMethod.replaceFragments(new ProfileFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
        //HelperMethod.replaceFragments(new ForgotPasswordSecondStepFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBackPressed();

        } else {
            super.onBackPressed();
        }
    }
}

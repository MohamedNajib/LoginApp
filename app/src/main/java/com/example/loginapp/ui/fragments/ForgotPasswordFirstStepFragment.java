package com.example.loginapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.loginapp.R;
import com.example.loginapp.helper.BackPressedListener;
import com.example.loginapp.helper.HelperMethod;
import com.example.loginapp.helper.UserInputValidation;
import com.example.loginapp.ui.activities.UserCycleActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFirstStepFragment extends Fragment {


    @BindView(R.id.ForgotP_S1EditTextEmail)
    EditText ForgotPS1EditTextEmail;
    Unbinder unbinder;

    public ForgotPasswordFirstStepFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Configure Back Pressed Listener Button */
        OnBackPressedListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password_first_stip, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Extract And Validation User Input
     */
    private void extractAndValidationInput() {
        String email = ForgotPS1EditTextEmail.getText().toString().trim();

        if (!UserInputValidation.isValidMail(email)) {
            ForgotPS1EditTextEmail.setError("Please Enter Correct Email..");

        } else {
            forgotPasswordFirstStepApiCall(email);
        }
    }

    private void forgotPasswordFirstStepApiCall(String email) {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ForgotP_S1Button)
    public void onViewClicked() {
        extractAndValidationInput();
    }

    /**
     * Configure Back Pressed Listener Button
     */
    public void OnBackPressedListener() {
        ((UserCycleActivity) Objects.requireNonNull(getActivity()))
                .setOnBackPressedListener(new BackPressedListener(getActivity()) {
                    @Override
                    public void doBackPressed() {

                        HelperMethod.replaceFragments(new LoginFragment(),
                                getActivity().getSupportFragmentManager(), R.id.FragmentContainer);
                    }
                });
    }
}

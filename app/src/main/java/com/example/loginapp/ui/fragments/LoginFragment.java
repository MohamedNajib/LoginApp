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
public class LoginFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.LoginEditTextEmail)
    EditText LoginEditTextEmail;
    @BindView(R.id.EditTextPassword)
    EditText EditTextPassword;

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Extract And Validation User Login Input
     */
    private void extractAndValidationInput() {
        String email = LoginEditTextEmail.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim();

        if (!UserInputValidation.isValidMail(email)) {
            LoginEditTextEmail.setError("Please Enter Correct Email..");

        } else if (!UserInputValidation.isValidPassword(password)) {
            EditTextPassword.setError("Please Enter Strong Password..");

        } else {
            loginUserApiCall(email, password);
        }
    }

    /**
     * Login User Account Use Api Call
     */
    private void loginUserApiCall(String email, String password) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




    @OnClick({R.id.LoginBtn_SignUp, R.id.LoginTextViewForgotPassword, R.id.LoginButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LoginBtn_SignUp:
                HelperMethod.replaceFragments(new RegisterFragment(),
                        getActivity().getSupportFragmentManager(), R.id.FragmentContainer);
                break;

            case R.id.LoginTextViewForgotPassword:
                HelperMethod.replaceFragments(new ForgotPasswordFirstStepFragment(),
                        getActivity().getSupportFragmentManager(), R.id.FragmentContainer);
                break;

            case R.id.LoginButton:
                extractAndValidationInput();
                break;
        }
    }

    /**
     * Configure Back Pressed Listener Button
     */
    public void OnBackPressedListener() {
        ((UserCycleActivity) Objects.requireNonNull(getActivity()))
                .setOnBackPressedListener(new BackPressedListener(getActivity()) {
                    @Override
                    public void doBackPressed() {

                        getActivity().moveTaskToBack(true);
                    }
                });
    }


}

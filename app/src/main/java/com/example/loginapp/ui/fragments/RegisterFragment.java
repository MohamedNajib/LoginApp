package com.example.loginapp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.loginapp.helper.HelperMethod;
import com.example.loginapp.R;
import com.example.loginapp.helper.UserInputValidation;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.RegisterEditTextEmail)
    EditText RegisterEditTextEmail;
    @BindView(R.id.RegisterEditTextUserName)
    EditText RegisterEditTextUserName;
    @BindView(R.id.RegisterEditTextPassword)
    EditText RegisterEditTextPassword;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    /**
     * Extract And Validation User Input
     */
    private void extractAndValidationInput() {
        String email = RegisterEditTextEmail.getText().toString().trim();
        String userName = RegisterEditTextUserName.getText().toString().trim();
        String password = RegisterEditTextPassword.getText().toString().trim();


        if (!UserInputValidation.isValidMail(email)) {
            RegisterEditTextEmail.setError("Please Enter Correct Email..");

        } else if (!UserInputValidation.isValidName(userName)) {
            RegisterEditTextUserName.setError("Please Enter Correct Name..");

        } else if (!UserInputValidation.isValidPassword(password)) {
            RegisterEditTextPassword.setError("Please Enter Strong Password..");

        } else {
            createUserAccountApiCall(email, userName, password);
        }
    }

    /**
     * Create User Account Use Api Call
     */
    private void createUserAccountApiCall(String email, String userName, String password) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Register_Btn_SignIn)
    public void onViewClicked() {

    }

    @OnClick({R.id.Register_Btn_SignUp, R.id.RegisterButtonSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Register_Btn_SignUp:
                HelperMethod.replaceFragments(new LoginFragment(), getActivity().getSupportFragmentManager(), R.id.FragmentContainer);
                break;

            case R.id.RegisterButtonSignUp:
                extractAndValidationInput();
                break;
        }
    }
}

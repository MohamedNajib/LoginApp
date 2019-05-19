package com.example.loginapp.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.loginapp.R;
import com.example.loginapp.helper.BackPressedListener;
import com.example.loginapp.helper.HelperMethod;
import com.example.loginapp.helper.UserInputValidation;
import com.example.loginapp.ui.activities.UserCycleActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.LoginFacebookButton)
    LoginButton LoginFacebookButton;

    private CallbackManager mCallbackManager;

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

        mCallbackManager = CallbackManager.Factory.create();
        LoginFacebookButton.setPermissions("email", "public_profile");

        LoginFacebookButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode,resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken == null){
                Toast.makeText(getContext(), "User Log Out", Toast.LENGTH_SHORT).show();

            }else {
                getUserProfile(currentAccessToken);
            }
        }
    };

    private String email;
    private void getUserProfile(AccessToken accessToken){
        final GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    String First_name = object.getString("First_name");
                    String last_name = object.getString("last_name");
                    email = object.getString("email");
                    String id = object.getString("id");

                    String imagUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    Toast.makeText(getContext(),email , Toast.LENGTH_SHORT).show();

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "First_name,last_name,email,id");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
        Toast.makeText(getContext(),email , Toast.LENGTH_SHORT).show();

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


    @OnClick(R.id.LoginFacebookButton)
    public void onViewClicked() {
    }
}

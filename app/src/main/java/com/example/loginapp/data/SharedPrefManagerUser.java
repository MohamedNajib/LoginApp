package com.example.loginapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.loginapp.Constants.SharedKeys.SHARED_PREF_NAME;
import static com.example.loginapp.Constants.SharedKeys.USER_API_TOKEN;

public class SharedPrefManagerUser {

    private static SharedPrefManagerUser mInstance;
    private Context mContext;

    private SharedPrefManagerUser(Context context) {
        this.mContext = context;
    }

    public static synchronized SharedPrefManagerUser getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerUser(context);
        }
        return mInstance;
    }

    public void setUserApiToken(String apiToken) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_API_TOKEN, apiToken);
        editor.apply();
    }

    public String getUserApiToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_API_TOKEN, null);
    }

    public void clare() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

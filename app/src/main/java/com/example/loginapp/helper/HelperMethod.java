package com.example.loginapp.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HelperMethod {

    /**
     * Helper Method
     * to Replace Fragments
     *
     * @param fragment
     * @param supportFragmentManager
     * @param id
     */
    public static void replaceFragments(Fragment fragment, FragmentManager supportFragmentManager, int id) {

        supportFragmentManager.beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Helper Method to Show Toast Message
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Convert File To Multipart
     *
     * @param pathImageFile
     * @param Key
     */
    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {
        File file = new File(pathImageFile);
        RequestBody reqFileSelect = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part imageBody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileSelect);
        return imageBody;
    }

    /**
     * Convert Input To RequestBody
     *
     * @param part
     */
    public static RequestBody convertToRequestBody(String part) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), part);
    }
}

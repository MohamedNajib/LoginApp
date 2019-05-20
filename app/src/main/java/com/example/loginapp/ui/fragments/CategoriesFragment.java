package com.example.loginapp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    Unbinder unbinder;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.frameLayoutCarWash, R.id.frameLayoutHouseCleaning, R.id.FrameLayoutLaundry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frameLayoutCarWash:
                break;

            case R.id.frameLayoutHouseCleaning:
                break;

            case R.id.FrameLayoutLaundry:
                break;
        }
    }
}

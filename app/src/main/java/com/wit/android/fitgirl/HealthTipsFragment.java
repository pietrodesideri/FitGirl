package com.wit.android.fitgirl;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthTipsFragment extends Fragment {

    View view;
    Button firstButton;


    public HealthTipsFragment() {
        // Required empty public constructor
    }

    public static HealthTipsFragment create() {
        HealthTipsFragment fragment = new HealthTipsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_tips, container, false);


    }

}

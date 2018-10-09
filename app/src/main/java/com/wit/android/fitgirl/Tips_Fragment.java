package com.wit.android.fitgirl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tips_Fragment extends Fragment {


    public Tips_Fragment() {
        // Required empty public constructor
    }

    public static Tips_Fragment create() {
        Tips_Fragment fragment = new Tips_Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips_, container, false);
    }

}

package com.gdg.miagegi.can2015.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdg.miagegi.can2015.MainActivity;
import com.gdg.miagegi.can2015.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FluxFragment extends Fragment{

    public FluxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flux, container, false);
    }
}

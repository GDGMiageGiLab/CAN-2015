package com.miagegi.gdg.can2015;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentHistorique extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static FragmentHistorique newInstance(int sectionNumber) {
        FragmentHistorique fragment = new FragmentHistorique();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_historique, container, false);
        return rootView;
    }
}

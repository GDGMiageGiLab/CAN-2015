package com.miagegi.gdg.can2015;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HP PROBOOK on 09/01/2015.
 */
public class FragmentApropos extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static FragmentApropos newInstance(int sectionNumber) {
        FragmentApropos fragment = new FragmentApropos();
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
        View rootView = inflater.inflate(R.layout.fragment_apropos, container, false);
        return rootView;
    }
}

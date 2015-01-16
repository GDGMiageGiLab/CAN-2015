package com.gdg.miagegi.can2015.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.gdg.miagegi.can2015.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoriqueFragment extends Fragment {
    public HistoriqueFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historique, container, false);
        WebView webview = (WebView) view.findViewById(R.id.webviewhistorique);
        webview.loadUrl("file:///android_res/raw/histoire.html");
        return view;
    }
}

package com.gdg.miagegi.can2015.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.gdg.miagegi.can2015.MainActivity;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.activity.SocialActivity;
import com.gdg.miagegi.can2015.adapter.SocialViewPagerTabAdapter;
import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Social;
import com.gdg.miagegi.can2015.service.SocialFetchService;
import com.gdg.miagegi.can2015.utils.BusProvider;
import com.squareup.otto.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class FluxFragment extends Fragment{

    private SocialViewPagerTabAdapter mAdapter;
    private Context mContext;
    private ViewPager mViewPager;
    private String registrationId;
    private AQuery aQ;
    private Context context;
    protected ActionBar mActionBar;
    protected ProgressBar mProgressBar;

    final private static String LOG_TAG = SocialActivity.class.getName();

    public FluxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_social, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("#BEST14");
        mProgressBar = (ProgressBar) getActivity().findViewById(R.id.progress);
        context = getActivity().getApplicationContext();
        aQ = new AQuery(getActivity());
        mContext = getActivity();
        mActionBar.setHomeButtonEnabled(false);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.pager);
        //showPager();

        Social socialCheck = Social.findFirst(getActivity());

        if (socialCheck != null) {
            showPager();
        } else {
            getActivity().startService(new Intent(getActivity(), SocialFetchService.class));
        }
        return v;
    }

    @Subscribe
    public void onNetworkOperationEvent(NetworkOperationEvent event) {

        Log.i(LOG_TAG, "I received an event : " + event.getClass().getName() + " : " + event.getMessage());
        if (event.hasStarted()) {
            showProgressBarWithMessage(event.getMessage());
        } else if (event.hasFinishedOne()) {
            hideProgressBar();
        } else if (event.hasFinishedAll()) {
            hideProgressBar();
            showPager();
	            /*Log.i("SHOWPAGER()",Integer.toString(event.mPosition));
	            BusProvider.getInstance().post(new VoteCompletedEvent(event.mPosition));*/

        } else if (event.hasFailed()) {
            hideProgressBar();
            Toast.makeText(mContext, event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        BusProvider.getInstance().register(this);
    }

    public void showPager() {
        int oldCurrentPosition = 0;
        if (mAdapter != null) {
            oldCurrentPosition = mViewPager.getCurrentItem();
        }
        mAdapter = new SocialViewPagerTabAdapter(getFragmentManager(), mContext);
        if (mViewPager == null) {
            mViewPager = (ViewPager) getActivity().findViewById(R.id.pager);
        }
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(oldCurrentPosition);
	        /*Log.i("SHOWPAGER","FINISHED");*/
    }

    public void hideProgressBar() {
        toggleProgressBarVisibility(false, null);
    }

    public void toggleProgressBarVisibility(boolean makeVisible, String message) {

        TextView progressMessageTV = (TextView) getActivity().findViewById(R.id.progress_message);
        if (!makeVisible) {
            message = "";
        }
        if (message != null) {
            progressMessageTV.setText(message);
        }

        mProgressBar.setVisibility(makeVisible ? View.VISIBLE : View.GONE);
        progressMessageTV.setVisibility(makeVisible && message != null ? View.VISIBLE : View.GONE);
    }

    public void showProgressBar() {
        toggleProgressBarVisibility(true, null);
    }

    public void showProgressBarWithMessage(String message) {
        toggleProgressBarVisibility(true, message);
    }

    public void toggleProgressBarVisibility(boolean makeVisible) {
        toggleProgressBarVisibility(makeVisible, null);
    }
}

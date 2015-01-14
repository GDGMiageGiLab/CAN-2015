package com.gdg.miagegi.can2015.activity;

import com.androidquery.AQuery;
import com.squareup.otto.Subscribe;


import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.R.layout;
import com.gdg.miagegi.can2015.adapter.SocialViewPagerTabAdapter;
import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Social;
import com.gdg.miagegi.can2015.service.SocialFetchService;
import com.gdg.miagegi.can2015.utils.BusProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerTabStrip;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SocialActivity extends BaseActivity {

	
	private SocialViewPagerTabAdapter mAdapter;
    private Context mContext;
    private ViewPager mViewPager;
    private String registrationId;
    private AQuery aQ;
    private Context context;
    
    final private static String LOG_TAG = SocialActivity.class.getName();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social);
		getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("#BEST14");
		mProgressBar = (ProgressBar) findViewById(R.id.progress);
		context = getApplicationContext();
        aQ = new AQuery(this);
        mContext = this;
        mActionBar.setHomeButtonEnabled(false);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //showPager();
        
        Social socialCheck = Social.findFirst(this);

        if (socialCheck != null) {
            showPager();
        } else {
            startService(new Intent(this, SocialFetchService.class));
        }
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
	    protected void onPause() {
	        super.onPause();
	        BusProvider.getInstance().unregister(this);
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        
	        BusProvider.getInstance().register(this);
	    }

	    public void showPager() {
	        int oldCurrentPosition = 0;
	        if (mAdapter != null) {
	            oldCurrentPosition = mViewPager.getCurrentItem();
	        }
	        mAdapter = new SocialViewPagerTabAdapter(getSupportFragmentManager(), mContext);
	        if (mViewPager == null) {
	            mViewPager = (ViewPager) findViewById(R.id.pager);
	        }
	        mViewPager.setAdapter(mAdapter);

	        mViewPager.setCurrentItem(oldCurrentPosition);
	        /*Log.i("SHOWPAGER","FINISHED");*/
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        int id = item.getItemId();
	        if (id == android.R.id.home) {
	            finish();
	        
	        } 
	        return super.onOptionsItemSelected(item);
	    }
}

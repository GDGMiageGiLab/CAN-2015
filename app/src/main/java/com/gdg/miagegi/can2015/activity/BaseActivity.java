package com.gdg.miagegi.can2015.activity;

import com.gdg.miagegi.can2015.R;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class BaseActivity extends ActionBarActivity {

    protected ActionBar mActionBar;
    protected ProgressBar mProgressBar;

    public void hideProgressBar() {
        toggleProgressBarVisibility(false, null);
    }
    
    @Override
    public void onStart() {
      super.onStart();
       // The rest of your onStart() code.
      //EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
      super.onStop();
      // The rest of your onStop() code.
      //EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.app_name);
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

    public void toggleProgressBarVisibility(boolean makeVisible, String message) {
        
        TextView progressMessageTV = (TextView) findViewById(R.id.progress_message);
        if (!makeVisible) {
            message = "";
        }
        if (message != null) {
            progressMessageTV.setText(message);
        }
        
        mProgressBar.setVisibility(makeVisible ? View.VISIBLE : View.GONE);
        progressMessageTV.setVisibility(makeVisible && message != null ? View.VISIBLE : View.GONE);
    }

}

package com.gdg.miagegi.can2015.activity;

import com.androidquery.AQuery;



import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.R.layout;
import com.gdg.miagegi.can2015.model.Social;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SocialDetailsActivity extends BaseActivity {
	
	private TextView tvUsername;
    private ImageView ivThumbnail;
    private ProgressBar progress;
    private TextView tvContent;
    private AQuery aQ;
    private Social mSocial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_details);
		aQ = new AQuery(this);
		getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("#CAN2015");
		ivThumbnail = (ImageView) findViewById(R.id.ivThumbnail);
		progress = (ProgressBar) findViewById(R.id.progress);
		tvContent = (TextView) findViewById(R.id.tvContent);
		tvUsername = (TextView) findViewById(R.id.tvUsername);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			mSocial = Social.findById(this, bundle.getInt("socialId"));
			if(mSocial != null){
				aQ.id(ivThumbnail).progress(progress).image(mSocial.profilePic.trim(), true, true, 0, R.drawable.ic_launcher);
	            tvContent.setText(mSocial.content); 
	            tvUsername.setText(mSocial.author);
			}
	}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.social_details, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        
        } else if (id == R.id.action_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            intent.putExtra(Intent.EXTRA_SUBJECT, "CAN 2015");
            intent.putExtra(Intent.EXTRA_TEXT, mSocial.link);
            startActivity(Intent.createChooser(intent, "Partager"));
        }
        return super.onOptionsItemSelected(item);
    }
}

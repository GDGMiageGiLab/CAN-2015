package com.gdg.miagegi.can2015.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.adapter.FeedListAdapter;
import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Feed;
import com.gdg.miagegi.can2015.service.DataFetchService;
import com.gdg.miagegi.can2015.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FeedListActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;
    private FeedListAdapter mAdapter = null;
    private ListView mListView;

    List<Feed> feeds = new ArrayList<Feed>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);
        mListView = (ListView) findViewById(R.id.lvFeed);
        feeds = Feed.findAll(this);
        mAdapter = new FeedListAdapter(this, feeds);
        mListView.setAdapter(mAdapter);
        //Toast.makeText(mContext, ""+feeds.size(), 3000).show();
        if(feeds.size() < 1){
            startService(new Intent(this, DataFetchService.class));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onNetworkOperationEvent(NetworkOperationEvent event) {

        // Log.i(LOG_TAG, "I received an event : " + event.getClass().getName() + " : " + event.getMessage());
        if (event.hasStarted()) {


                Toast.makeText(FeedListActivity.this, "Actualisation en cours", Toast.LENGTH_LONG).show();

        } else if (event.hasFinishedOne()) {
            //  hideProgressBar();
        } else if (event.hasFinishedAll()) {

            feeds.clear();
            feeds.addAll(Feed.findAll(FeedListActivity.this));
            mAdapter.notifyDataSetChanged();

        } else if (event.hasFailed()) {
            // hideProgressBar();
            Toast.makeText(FeedListActivity.this, event.getMessage(), Toast.LENGTH_LONG).show();
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
}

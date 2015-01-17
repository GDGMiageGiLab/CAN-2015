package com.gdg.miagegi.can2015.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gdg.miagegi.can2015.FeedDetailsActivity;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.adapter.FeedListAdapter;
import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Feed;
import com.gdg.miagegi.can2015.service.DataFetchService;
import com.gdg.miagegi.can2015.utils.BusProvider;
import com.gdg.miagegi.can2015.utils.Constants;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 17/01/15.
 */
public class FeedListFragment extends Fragment {
    private ProgressDialog progressDialog;
    private FeedListAdapter mAdapter = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Feed> feeds = new ArrayList<Feed>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_feed_list, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        feeds = Feed.findAll(getActivity());

        mAdapter = new FeedListAdapter(getActivity(), feeds);
        mAdapter.setItemClickListener(new FeedListAdapter.onItemClickListener() {

            @Override
            public void onItemClicked(View view, int position) {
                 Intent intent = new Intent(getActivity(), FeedDetailsActivity.class);
                intent.putExtra(Constants.EXTRA_FEED, feeds.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        //Toast.makeText(mContext, ""+feeds.size(), 3000).show();
        if (feeds.size() < 1) {
            getActivity().startService(new Intent(getActivity(), DataFetchService.class));
        }
        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_feed_list, menu);
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

            Toast.makeText(getActivity(), "Actualisation en cours", Toast.LENGTH_LONG).show();

        } else if (event.hasFinishedOne()) {
            //  hideProgressBar();
        } else if (event.hasFinishedAll()) {

            feeds.clear();
            feeds.addAll(Feed.findAll(getActivity()));
            mAdapter.notifyDataSetChanged();

        } else if (event.hasFailed()) {
            // hideProgressBar();
            Toast.makeText(getActivity(), event.getMessage(), Toast.LENGTH_LONG).show();
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

}

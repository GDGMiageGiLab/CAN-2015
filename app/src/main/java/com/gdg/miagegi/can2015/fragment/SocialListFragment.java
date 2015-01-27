package com.gdg.miagegi.can2015.fragment;

import java.util.ArrayList;
import java.util.List;

import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.activity.SocialDetailsActivity;
import com.gdg.miagegi.can2015.adapter.SocialListAdapter;
import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Feed;
import com.gdg.miagegi.can2015.model.Social;
import com.gdg.miagegi.can2015.service.DataFetchService;
import com.gdg.miagegi.can2015.service.SocialFetchService;
import com.gdg.miagegi.can2015.utils.BusProvider;

import com.squareup.otto.Subscribe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SocialListFragment extends Fragment{
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		BusProvider.getInstance().unregister(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 BusProvider.getInstance().register(this);
	}

	private SocialListAdapter mAdapter = null;
    private Context mContext;
    private int mCurrentPosition;
    private ListView mListView;
   

    private List<Social> mSocials = new ArrayList<Social>();

    public SocialListFragment() {
        super();
    }

    public static String ARGS_POSITION = "position";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        if (getArguments() != null) {
            mCurrentPosition = getArguments().getInt(ARGS_POSITION);
        } else {
            mCurrentPosition = 0;
        }
        super.onCreate(savedInstanceState);
    }
    
   

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_social, container, false);

        if (mCurrentPosition == 0) {
            mSocials.clear();
            mSocials.addAll(Social.findAllByType(mContext, "Twitter"));
            
        }

        if(mAdapter == null){
           mAdapter = new SocialListAdapter(mContext, mSocials);
        }else{
           mAdapter.notifyDataSetChanged();
        }
        
        if (mListView == null) {
           mListView = new ListView(mContext);
        }

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				getActivity().startActivity(new Intent(getActivity(), SocialDetailsActivity.class)
				.putExtra("socialId", mAdapter.getItem(position).id));
				
			}
		});
        
        
        ((ViewGroup) rootView).removeAllViews();
        ((ViewGroup) rootView).addView(mListView);
        if (mSocials.size() < 1) {
            getActivity().startService(new Intent(getActivity(), SocialFetchService.class));
        }
        return rootView;
    }

    public static SocialListFragment newInstance(int defaultPosition) {
        SocialListFragment f = new SocialListFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_POSITION, defaultPosition);
        f.setArguments(args);
        return (f);
    }

    @Subscribe
    public void onNetworkOperationEvent(NetworkOperationEvent event) {

        // Log.i(LOG_TAG, "I received an event : " + event.getClass().getName() + " : " + event.getMessage());
        if (event.hasStarted()) {

            Toast.makeText(getActivity(), "Actualisation en cours", Toast.LENGTH_LONG).show();

        } else if (event.hasFinishedOne()) {
            //  hideProgressBar();
        } else if (event.hasFinishedAll()) {

            mSocials.clear();
            mSocials.addAll(Social.findAllByType(mContext, "Twitter"));
            mAdapter.notifyDataSetChanged();

        } else if (event.hasFailed()) {
            // hideProgressBar();
            Toast.makeText(getActivity(), event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}

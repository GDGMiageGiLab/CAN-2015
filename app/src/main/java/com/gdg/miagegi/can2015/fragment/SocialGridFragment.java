package com.gdg.miagegi.can2015.fragment;

import java.util.ArrayList;
import java.util.List;

import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.activity.SocialDetailsActivity;
import com.gdg.miagegi.can2015.activity.SocialGridDetailsActivity;
import com.gdg.miagegi.can2015.adapter.SocialGridAdapter;
import com.gdg.miagegi.can2015.adapter.SocialListAdapter;
import com.gdg.miagegi.can2015.model.Social;
import com.gdg.miagegi.can2015.utils.BusProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class SocialGridFragment extends Fragment{
	
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

	private SocialGridAdapter mAdapter = null;
    private Context mContext;
    
    private GridView mGridView;
   

    private List<Social> mSocials = new ArrayList<Social>();

    public SocialGridFragment() {
        super();
    }

    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity();
       
        super.onCreate(savedInstanceState);
    }
    
   

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid_social, container, false);
       
            mSocials.clear();
            mSocials.addAll(Social.findAllByType(mContext, "Instagram"));
            
        
        if(mAdapter == null){
        mAdapter = new SocialGridAdapter(mContext, mSocials);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        
        mGridView = (GridView) rootView.findViewById(R.id.gridSocial);
        mGridView.setAdapter(mAdapter);
        
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				getActivity().startActivity(new Intent(getActivity(), SocialGridDetailsActivity.class)
				.putExtra("socialId", mAdapter.getItem(position).id));
				
			}
		});
        
        ((ViewGroup) rootView).removeAllViews();
        ((ViewGroup) rootView).addView(mGridView);
        return rootView;
    }

   

}

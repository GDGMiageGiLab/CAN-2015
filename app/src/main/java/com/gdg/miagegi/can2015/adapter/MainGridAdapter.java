package com.gdg.miagegi.can2015.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.model.MainItem;

public class MainGridAdapter extends BaseAdapter{
	
	private static class ViewHolder {
       
        public ImageView imgMain;
        public TextView textMain;
        
        
        public ViewHolder() {
        }
    }
		
	
	    private List<MainItem> mMainItems = new ArrayList<MainItem>();

	    private Context mContext;

	    private LayoutInflater mInflater;
	    
	    public MainGridAdapter(Context context, List<MainItem> mainItems) {
	        mContext = context;
	        mMainItems = mainItems;
	        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	       
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMainItems.size();
		}

		@Override
		public MainItem getItem(int position) {
			// TODO Auto-generated method stub
			return mMainItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
	        final MainItem object = mMainItems.get(position);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.main_grid_item, null);
	            holder = new ViewHolder();
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.textMain = (TextView) convertView.findViewById(R.id.textMain);
	        
	        holder.imgMain = (ImageView) convertView.findViewById(R.id.imgMain);
	       
	       
	        if (object != null) {
	        	holder.textMain.setText(object.title);
	        	holder.imgMain.setImageResource(object.icon);
	        	
	        }
	        return convertView;

		}


}

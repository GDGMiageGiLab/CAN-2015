package com.gdg.miagegi.can2015.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.model.Social;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;

public class SocialListAdapter extends BaseAdapter{
	
	private static class ViewHolder {
        public TextView tvUsername;
        public TextView tvScreenname;
        public ImageView ivThumbnail;
        public ProgressBar progress;
        public TextView tvContent;
        
        public ViewHolder() {
        }
    }
		private final AQuery aQ;
	
	    private List<Social> mSocialListItem = new ArrayList<Social>();

	    private Context mContext;

	    private LayoutInflater mInflater;
	    
	    public SocialListAdapter(Context context, List<Social> socialListItem) {
	        mContext = context;
	        mSocialListItem = socialListItem;
	        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        aQ = new AQuery(this.mContext);
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mSocialListItem.size();
		}

		@Override
		public Social getItem(int position) {
			// TODO Auto-generated method stub
			return mSocialListItem.get(position);
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
	        final Social object = mSocialListItem.get(position);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.social_list_item, null);
	            holder = new ViewHolder();
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
	        holder.tvScreenname = (TextView) convertView.findViewById(R.id.tvScreenname);
	        holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
	        holder.ivThumbnail = (ImageView) convertView.findViewById(R.id.ivThumbnail);
	        holder.progress = (ProgressBar) convertView.findViewById(R.id.progress);
	       
	        if (object != null) {
	        	holder.tvUsername.setText(object.author);
	        	holder.tvContent.setText(object.content);
	        	holder.tvScreenname.setText("@"+object.screenName);
	           if (object.profilePic != null) {
	            	
	            	aQ.id(holder.ivThumbnail).progress(holder.progress).image(object.profilePic.trim(), true, true, 0, R.drawable.ic_launcher);
	               
	            }
	          
	           
	           
	        }
	        return convertView;

		}


}

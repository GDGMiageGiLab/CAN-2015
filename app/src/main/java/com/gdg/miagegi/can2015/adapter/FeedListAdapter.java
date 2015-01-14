package com.gdg.miagegi.can2015.adapter;

import java.util.ArrayList;
import java.util.List;





import com.androidquery.AQuery;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.model.Feed;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FeedListAdapter extends BaseAdapter{
	private static class ViewHolder {
        public TextView tvTitle;
        public ImageView ivThumbnail;
        public ProgressBar progress;
        public TextView tvDescription;
        
        public ViewHolder() {
        }
    }
		private final AQuery aQ;
	
	    private List<Feed> mMainListItem = new ArrayList<Feed>();

	    private Context mContext;

	    private LayoutInflater mInflater;
	    
	    public FeedListAdapter(Context context, List<Feed> mainListItem) {
	        mContext = context;
	        mMainListItem = mainListItem;
	        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        aQ = new AQuery(this.mContext);
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMainListItem.size();
		}

		@Override
		public Feed getItem(int position) {
			// TODO Auto-generated method stub
			return mMainListItem.get(position);
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
	        final Feed object = mMainListItem.get(position);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.feed_list_item, parent, false);
	            holder = new ViewHolder();
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
	        holder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
	        holder.ivThumbnail = (ImageView) convertView.findViewById(R.id.ivThumbnail);
	        holder.progress = (ProgressBar) convertView.findViewById(R.id.progress);
	       
	        if (object != null) {
	        	holder.tvTitle.setText(object.title);
	        	holder.tvDescription.setText(Html.fromHtml(object.description));
	           if (object.photoUrl != null) {
	            	if(object.photoUrl.equals("")){
                        holder.ivThumbnail.setImageResource(R.drawable.ic_launcher);
                    }else {
                        aQ.id(holder.ivThumbnail).progress(holder.progress).image(object.photoUrl.trim(), true, true, 0, R.drawable.ic_launcher);
                    }
	            }
	          
	           
	           
	        }
	        return convertView;

		}

}

package com.gdg.miagegi.can2015.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.androidquery.AQuery;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.model.Feed;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.MyViewHolder>{

    private AQuery listAq;
    private AQuery aQ;
    private List<Feed> mMainListItem = new ArrayList<Feed>();
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener listener;

    public FeedListAdapter(Context context, List<Feed> mainListItem) {
        mContext = context;
        mMainListItem = mainListItem;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listAq = new AQuery(this.mContext);
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.feed_list_item, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        view.setTag(holder);
        aQ = listAq.recycle(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Feed object = mMainListItem.get(position);

        if (object != null) {
            holder.tvTitle.setText(object.title);
            Date date = object.pubDate;
            DateTime dt = new DateTime(date);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("E e MMMM yyyy HH:mm:ss");
            String str = fmt.print(dt);
            holder.tvDate.setText(str);
            if (object.photoUrl != null) {

                String imageUrl = object.photoUrl;
                aQ.id(holder.ivThumbnail).progress(holder.progress).image(imageUrl, true, true, 0,0, null, 0,1.0f);

            }



        }

        Typeface Roboto_Regular = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Regular.ttf");

        holder.tvTitle.setTypeface(Roboto_Regular);

    }

    @Override
    public int getItemCount() {
        return mMainListItem.size();
    }

    public void setItemClickListener(onItemClickListener itemClickListener){
        listener = itemClickListener;
    }
    class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle;
        public ImageView ivThumbnail;
        public ProgressBar progress;
        public TextView tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);

            tvTitle.setOnClickListener(this);
            tvDate.setOnClickListener(this);
            ivThumbnail.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(listener != null){
                listener.onItemClicked(v, getPosition());
            }

        }


    }

    public  interface onItemClickListener{
        public void onItemClicked(View view, int position);
    }

}

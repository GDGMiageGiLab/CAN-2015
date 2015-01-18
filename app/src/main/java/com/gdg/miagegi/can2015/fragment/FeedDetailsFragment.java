package com.gdg.miagegi.can2015.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.model.Feed;
import com.gdg.miagegi.can2015.utils.Constants;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by macbookpro on 17/01/15.
 */
public class FeedDetailsFragment extends Fragment{
    Feed feed;

    private TextView textTitle, textDate, textDescription;
    private ImageView imageView;
    private AQuery aQ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed_details, container, false);

        feed = (Feed)getActivity().getIntent().getSerializableExtra(Constants.EXTRA_FEED);

        aQ  = new AQuery(getActivity());
        Typeface Roboto_Regular = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Regular.ttf");
        imageView = (ImageView)v.findViewById(R.id.imageView);


        String imageUrl =feed.photoUrl;
        aQ.id(R.id.imageView).image(imageUrl, true, true,0,R.drawable.logocan2015, null, AQuery.FADE_IN_NETWORK, 1.0f);

        textTitle = (TextView)v.findViewById(R.id.textTitre);
        textTitle.setTypeface(Roboto_Regular);
        textDate = (TextView)v.findViewById(R.id.textDate);
        textDate.setTypeface(Roboto_Regular);
        textDescription = (TextView)v.findViewById(R.id.textDescription);
        textDescription.setTypeface(Roboto_Regular);

        if (feed != null){
            textTitle.setText(feed.title);
            Date date = feed.pubDate;
            DateTime dt = new DateTime(date);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("E e MMMM yyyy HH:mm:ss");
            String str = fmt.print(dt);
            textDate.setText(str);
            textDescription.setText(Html.fromHtml(feed.description));
        }
        return v;
    }
}

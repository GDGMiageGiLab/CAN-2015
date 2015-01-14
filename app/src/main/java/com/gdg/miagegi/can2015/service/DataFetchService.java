package com.gdg.miagegi.can2015.service;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.w3c.dom.DOMException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;


import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.gdg.miagegi.can2015.MainApplication;
import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.activity.FeedListActivity;
import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Feed;
import com.gdg.miagegi.can2015.utils.BusProvider;
import com.gdg.miagegi.can2015.utils.Constants;
import com.gdg.miagegi.can2015.utils.Log;
import com.gdg.miagegi.can2015.utils.Reversed;
import com.squareup.otto.Bus;

public class DataFetchService extends Service {

    final private static String LOG_TAG = DataFetchService.class.getName();
    private AQuery mAQ;
    private Context mContext;
    private Bus mBus;

   
    private int mNewArticleCount;


    

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification buildForJellyBean(NotificationCompat.Builder builder) {
        builder.setPriority(Notification.PRIORITY_HIGH);
        return builder.build();
    }

    


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(LOG_TAG, "I have been created.");
        BusProvider.getInstance().register(this);
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        Log.i(LOG_TAG, "I have been destroyed.");
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOG_TAG, "I have started.");

        mContext = this;
        mAQ = new AQuery(mContext);
        mBus = BusProvider.getInstance();
       
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if ((networkInfo == null) || (networkInfo.getState() != NetworkInfo.State.CONNECTED)) {
            //return;
        }else{

        		
        mBus.post(new NetworkOperationEvent(NetworkOperationEvent.HAS_STARTED, getString(R.string.message_bootstrapping_data)));


        	fetchFeedXML("http://afriquefoot.rfi.fr/can-2015/rss/");


        }

        return super.onStartCommand(intent, flags, startId);
    }
    
   	

		
				    	 	
			
		

		
		 @SuppressWarnings("deprecation")
		    public void doneFetchingAll() {
		        Log.d(LOG_TAG, "Done Fetching all");

		        if (mNewArticleCount > 0) {
		            String message = "";
		            if (mNewArticleCount == 1) {
		                message = mNewArticleCount + " nouvel article.";
		            } else {
		                message = mNewArticleCount + " nouveaux articles.";
		            }

		            Intent notificationIntent = new Intent(DataFetchService.this, FeedListActivity.class);
		            
		            notificationIntent.putExtra("position", 0).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		            PendingIntent contentIntent = PendingIntent.getActivity(DataFetchService.this, 0,
		                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		            Notification notification = null;
		            NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(
		                    MainApplication.getContext()) //
		                    .setContentIntent(contentIntent) //
		                    .setSmallIcon(R.drawable.ic_launcher) //
		                    .setLargeIcon(
		                            BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)) //
		                    .setTicker(message) //
		                    .setWhen(System.currentTimeMillis()) //
		                    .setAutoCancel(true) //
		                    .setContentTitle("CAN 2015") //
		                    .setContentText(message) //
		                    .setLights(0xffffffff, 300, 1000);

		            
		           
		                notifBuilder.setVibrate(new long[] {
		                        0, 1000
		                });
		            
		              
		           
		            if (Build.VERSION.SDK_INT > 15) {
		                notification = buildForJellyBean(notifBuilder);
		            } else {
		                notification = notifBuilder.getNotification();
		            }
		            notification.flags |= Notification.FLAG_AUTO_CANCEL;

		            if (Constants.NOTIF_MGR != null) {
		                Constants.NOTIF_MGR.notify(0, notification);
		            }

		        } else if (Constants.NOTIF_MGR != null) {
		            Constants.NOTIF_MGR.cancel(0);
		        }
		    }
		 
		 public void fetchFeedXML(final String url) {

		        mAQ.ajax(url, XmlDom.class, 30 * 60 * 1000, new AjaxCallback<XmlDom>() {
		            @Override
		            public void callback(String url, XmlDom xml, AjaxStatus status) {

		                if ((status.getCode() == 200) && (xml != null)) {
		                    List<Feed> articles = new ArrayList<Feed>();

		                    List<XmlDom> entries = xml.tags("item");

		                    //
		                    for (XmlDom entry : Reversed.reversed(entries)) {
                                Date date = new Date();
                                try {
                                    if ((entry.text("pubDate") != null)) {
                                        if (!entry.text("pubDate").equals("")) {
                                            date = Constants.getDateFormat().parse(entry.text("pubDate"));
                                        }
                                    }
                                } catch (ParseException e) {
                                    Log.e(LOG_TAG, e.getMessage(), e);
                                } catch (DOMException e) {
                                    Log.e(LOG_TAG, e.getMessage(), e);
                                } catch (java.text.ParseException e) {
                                    Log.e(LOG_TAG, e.getMessage(), e);
                                }
                                String link = entry.text("link");
                                Log.e("DESRIPTION ---->", entry.text("description"));
                                Feed article = Feed.findByLink(mContext,
                                        link);
                                if (article == null) {
                                    mNewArticleCount++;
                                    String description = entry.text("description");
                                    String imageUrl = "";

                                    if (entry.text("thumbnail") != null) {

                                        Document document = Jsoup.parse(entry.text("thumbnail"));

                                        if (document != null) {


                                            imageUrl = document.attr("url");


                                        }

                                    }
                                    article = new Feed();
                                    article.content = description;
                                    article.description = description;
                                    article.link = entry.text("link");
                                    article.pubDate = date;
                                    article.photoUrl = imageUrl.trim();
                                    article.title = entry.text("title");

                                    articles.add(article);
                                    Log.e("FEED >>>>>>", description + "\n" + entry
                                            .text("link") + "\n" + entry.text("title"));

                                }
                            }
		                    Feed.saveAll(mContext, articles);
                            doneFetchingAll();
                            //}

		                }else{
                            mBus.post(new NetworkOperationEvent(NetworkOperationEvent.HAS_FAILED));
                        }

		                	//if(!MainApplication.getPrefs().getString(Constants.NAME, "").equals("")){



		            }
		        });

		    }
		
		

}

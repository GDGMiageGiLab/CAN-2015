package com.gdg.miagegi.can2015.service;

import static com.gdg.miagegi.can2015.utils.Reversed.reversed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gdg.miagegi.can2015.event.NetworkOperationEvent;
import com.gdg.miagegi.can2015.model.Social;
import com.gdg.miagegi.can2015.utils.Authenticated;
import com.gdg.miagegi.can2015.utils.BusProvider;
import com.gdg.miagegi.can2015.R;


import java.util.List;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.DOMException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Base64;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.google.gson.Gson;
import com.squareup.otto.Bus;

public class SocialFetchService extends Service{
	
	
	 final private static String LOG_TAG = SocialFetchService.class.getName();
	    private AQuery mAQ;
	    private Context mContext;
	    private Bus mBus;
	    private String[] mTypes;
	    
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
	        mTypes = mContext.getResources().getStringArray(R.array.social_list_tabs);
	        mBus.post(new NetworkOperationEvent(NetworkOperationEvent.HAS_STARTED, getString(R.string.message_bootstrapping_data)));
	        downloadTweets();
	        

	        return super.onStartCommand(intent, flags, startId);
	    }
	    
	   
	    
	    
	    private void fetchInstagramPosts(){
	    	
	    	mAQ.ajax("http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=20&q=http://instagram.com/tags/can2015/feed/recent.rss", JSONObject.class, 30 * 60 * 1000, new AjaxCallback<JSONObject>() {
	            @Override
	            public void callback(String url, JSONObject data, AjaxStatus status) {
	               
	                if ((status.getCode() == 200) && (data != null)) {


	                    List<Social> instas = new ArrayList<Social>();
	                    try{
	                    JSONArray array = data.getJSONObject("responseData").getJSONObject("feed").getJSONArray("entries");
						for (int i = 0; i < array.length(); i++){
							JSONObject _social = array.getJSONObject(i);
							 String link = _social.getString("link");
							 Social social = Social.findByLink(mContext,
		                                link );
		                        if (social == null) {
		                        	social = new Social();
		                        	social.link = link;
		                            social.content = _social.getString("title"); 
		                            StringBuilder sb = new StringBuilder(link);
		                            sb.setCharAt(link.length() - 5, 's');
		                            social.imageUrl = sb.toString();
		                            Log.e("ZZZZZZ", sb.toString());
		                            social.type = mTypes[1];
		                            instas.add(social);
		                        }
							
							
						}}catch(Exception e){
							e.printStackTrace();
						}
	                   
	                       
	                    Social.saveAll(mContext, instas);
	                    mBus.post(new NetworkOperationEvent(NetworkOperationEvent.HAS_FINISHED_ALL));
	                }
	               
	                    
	                

	            }
	        });
	    //end
	    }
	    
	    
	    public void downloadTweets() {
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

			if (networkInfo != null && networkInfo.isConnected()) {
				new DownloadTwitterTask().execute();
			} else {
				Log.v(LOG_TAG, "No network connection available.");
			}
		}

		// Uses an AsyncTask to download a Twitter user's timeline
		private class DownloadTwitterTask extends AsyncTask<String, Void, String> {
			final static String CONSUMER_KEY = "gWIvgFdpssviel0PonMvxCtQk";
			final static String CONSUMER_SECRET = "wOSdbmIOEpHEDU5kMdqSALHsC15TxPhitYPVM0vhNIfY7oalAo";
			final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
			final static String TwitterStreamURL = "https://api.twitter.com/1.1/search/tweets.json?q=%23can2015";

			@Override
			protected String doInBackground(String... params) {
				String result = null;

					result = getTwitterStream();
				
				return result;
			}

			// onPostExecute convert the JSON results into a Twitter object (which is an Array list of tweets
			@Override
			protected void onPostExecute(String result) {
				Log.e("QQQQQQQQQQQ", result);
				List<Social> twits = jsonToTwitter(result);
				Social.saveAll(mContext, twits);
				mBus.post(new NetworkOperationEvent(NetworkOperationEvent.HAS_FINISHED_ONE));
				fetchInstagramPosts();
				
			}

			// converts a string of JSON data into a Twitter object
			private List<Social> jsonToTwitter(String result) {
				List<Social> list = new ArrayList<Social>();
				try{
					
					
					JSONObject res = new JSONObject(result);
					JSONArray array = res.getJSONArray("statuses");
					for(int i = 0; i < array.length(); i++){
						JSONObject t = array.getJSONObject(i);
						
						JSONObject u = t.getJSONObject("user");
						
						String link = new StringBuilder("https://twitter.com/").append(u.getString("screen_name"))
								.append("/status/").append(t.getString("id_str")).toString();
						Social tweet = Social.findByLink(mContext, link);
						if(tweet == null){
							tweet = new Social();
					    tweet.content = t.getString("text");
						tweet.author = u.getString("name");
						tweet.screenName = u.getString("screen_name");
						tweet.profilePic = u.getString("profile_image_url");
						tweet.link = link;
						tweet.type = mTypes[0];
						}
						
						list.add(tweet);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
						
					
				return list;
			}

			// convert a JSON authentication object into an Authenticated object
			private Authenticated jsonToAuthenticated(String rawAuthorization) {
				Authenticated auth = null;
				if (rawAuthorization != null && rawAuthorization.length() > 0) {
					try {
						Gson gson = new Gson();
						auth = gson.fromJson(rawAuthorization, Authenticated.class);
					} catch (IllegalStateException ex) {
						// just eat the exception
					}
				}
				return auth;
			}

			private String getResponseBody(HttpRequestBase request) {
				StringBuilder sb = new StringBuilder();
				try {

					DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
					HttpResponse response = httpClient.execute(request);
					int statusCode = response.getStatusLine().getStatusCode();
					String reason = response.getStatusLine().getReasonPhrase();

					if (statusCode == 200) {

						HttpEntity entity = response.getEntity();
						InputStream inputStream = entity.getContent();

						BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
						String line = null;
						while ((line = bReader.readLine()) != null) {
							sb.append(line);
						}
					} else {
						sb.append(reason);
					}
				} catch (UnsupportedEncodingException ex) {
				} catch (ClientProtocolException ex1) {
				} catch (IOException ex2) {
				}
				return sb.toString();
			}

			private String getTwitterStream() {
				String results = null;

				// Step 1: Encode consumer key and secret
				try {
					// URL encode the consumer key and secret
					String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
					String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");

					// Concatenate the encoded consumer key, a colon character, and the
					// encoded consumer secret
					String combined = urlApiKey + ":" + urlApiSecret;

					// Base64 encode the string
					String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

					// Step 2: Obtain a bearer token
					HttpPost httpPost = new HttpPost(TwitterTokenURL);
					httpPost.setHeader("Authorization", "Basic " + base64Encoded);
					httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
					httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
					String rawAuthorization = getResponseBody(httpPost);
					Authenticated auth = jsonToAuthenticated(rawAuthorization);

					// Applications should verify that the value associated with the
					// token_type key of the returned object is bearer
					if (auth != null && auth.token_type.equals("bearer")) {

						// Step 3: Authenticate API requests with bearer token
						HttpGet httpGet = new HttpGet(TwitterStreamURL);

						// construct a normal HTTPS request and include an Authorization
						// header with the value of Bearer <>
						httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
						httpGet.setHeader("Content-Type", "application/json");
						// update the results with the body of the response
						results = getResponseBody(httpGet);
					}
				} catch (UnsupportedEncodingException ex) {
				} catch (IllegalStateException ex1) {
				}
				return results;
			}
		}


}

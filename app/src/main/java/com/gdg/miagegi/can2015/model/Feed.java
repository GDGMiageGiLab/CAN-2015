package com.gdg.miagegi.can2015.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.PreparedQuery;

public class Feed
    
{
    @DatabaseField(generatedId = true)
    public int id;
	@DatabaseField
    public String description;
	@DatabaseField
    public String link;
	@DatabaseField
    public String photoUrl;
	@DatabaseField
    public Date pubDate;
	@DatabaseField
    public String title;

	@DatabaseField
	public String content;

    public Feed()
    {
    	
    }
    
    public static List<Feed> findLatest(Context context, long n) {
        List<Feed> articles = new ArrayList<Feed>();
        try {
            final Dao<Feed, Integer> dao = Model.getHelper(context).getDao(Feed.class);
            articles = dao.queryBuilder().groupBy("node_id").orderBy("pubDate", false).limit(n).query();
        } catch (final SQLException e) {
            Log.e( "Database Exception", e.getMessage());
        }
        return articles;
    }

   


    public static Feed findByLink(Context context, String link) {
        Feed object = null;
        try {
            final Dao<Feed, Integer> dao = Model.getHelper(context).getDao(Feed.class);
            final PreparedQuery<Feed> pQ = dao.queryBuilder().where().eq("link", link).prepare();
            object = dao.queryForFirst(pQ);
        } catch (final SQLException e) {
            Log.e( "Database Exception", e.getMessage());
        }
        return object;
    }
    
    private static Dao<Feed, Integer> getDao(Context context) throws SQLException {
        return Model.getHelper(context).getDao(Feed.class);
    }

    private static void logException(Exception e) {
        Model.logException(Feed.class, e);
    }
    
    public static void saveAll(Context context, List<Feed> feeds){
        for (Feed feed : feeds) {
            try {
                getDao(context).createOrUpdate(feed);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   
        }
        
    }

    public static List<Feed> findAll(Context context) {
        List<Feed> feeds = new ArrayList<Feed>();
        try { 
        	 final Dao<Feed, Integer> dao = Model.getHelper(context).getDao(Feed.class);
             feeds = dao.queryBuilder().orderBy("pubDate", false).query();
         } catch (final SQLException e) {
                   logException(e);
        }
        return feeds;
    }
    
    
    public static List<Feed> findByCategory(Context context, String category){
       List<Feed> objects = null;
        try { 
             objects = getDao(context).queryBuilder().orderBy("pubDate", false).where().eq("category", category).query();
        } catch (SQLException e) {
            logException(e);
        }
        
        return objects;
    }
    
    public static void save(Context context, Feed feed){
        
        try {
            getDao(context).createOrUpdate(feed);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    
    
}

   
}

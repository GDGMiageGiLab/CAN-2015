package com.gdg.miagegi.can2015.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.PreparedQuery;

public class Social {
	
	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField
	public String author;
	
	@DatabaseField
	public String screenName;
	
	@DatabaseField
	public String profilePic;
	
	@DatabaseField
	public String type;
	
	@DatabaseField
	public String content;
	
	@DatabaseField
	public String link;
	
	@DatabaseField
	public String imageUrl;
	
	private static DatabaseHelper databaseHelper = null;
	
	 public static DatabaseHelper getHelper(Context context) {
	        if (databaseHelper == null) {
	            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
	        }
	        return databaseHelper;
	    }

	    @SuppressWarnings("rawtypes")
	    public static void logException(Class clazz, Exception e) {
	        Log.e(clazz.getName(), "Database Exception", e);
	    }
	
	private static Dao<Social, Integer> getDao(Context context) throws SQLException {
      return getHelper(context).getDao(Social.class);
  }
	
	public static Social findByLink(Context context, String link) {
        Social object = null;
        try {
            final Dao<Social, Integer> dao = Model.getHelper(context).getDao(Social.class);
            final PreparedQuery<Social> pQ = dao.queryBuilder().where().eq("link", link).prepare();
            object = dao.queryForFirst(pQ);
        } catch (final SQLException e) {
            Log.e( "Database Exception", e.getMessage());
        }
        return object;
    }
	
	public static Social findById(Context context, int id) {
        Social object = null;
        try {
            final Dao<Social, Integer> dao = Model.getHelper(context).getDao(Social.class);
            final PreparedQuery<Social> pQ = dao.queryBuilder().where().eq("id", id).prepare();
            object = dao.queryForFirst(pQ);
        } catch (final SQLException e) {
            Log.e( "Database Exception", e.getMessage());
        }
        return object;
    }

  private static void logException(Exception e) {
      Model.logException(Social.class, e);
  }
  
  public static Social findFirst(Context context) {
      Social object = null;
      try {
          final PreparedQuery<Social> pQ = getDao(context).queryBuilder().prepare();
          object = getDao(context).queryForFirst(pQ);
      } catch (SQLException e) {
          logException(e);
      }
      return object;
  }
  
  public static void saveAll(Context context, List<Social> socials){
      for (Social social : socials) {
          try {
              getDao(context).createOrUpdate(social);
              
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }   
      }
      
  }

  public static List<Social> findAllByType(Context context, String type) {
      List<Social> socials = new ArrayList<Social>();
      try { 
      	 final Dao<Social, Integer> dao = Model.getHelper(context).getDao(Social.class);
           socials = dao.queryBuilder().where().eq("type", type).query();
       } catch (final SQLException e) {
                 logException(e);
      }
      return socials;
  }

}

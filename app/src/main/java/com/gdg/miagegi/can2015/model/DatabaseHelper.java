package com.gdg.miagegi.can2015.model;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "can.db";
    private static final int DATABASE_VERSION = 1;
    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    public Class<?> classes[] = {Social.class};

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(LOG_TAG, "DB Creation");
            for (int i = 0; i < classes.length; i++) {
                Log.i(LOG_TAG, "\t CREATE IF NOT EXISTS ====> " + classes[i].getName());
                TableUtils.createTableIfNotExists(connectionSource, classes[i]);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(LOG_TAG, "DB Update");

            for (int i = 0; i < classes.length; i++) {
                Log.i(LOG_TAG, "\t DROP TABLE ====> " + classes[i].getName());
                TableUtils.dropTable(connectionSource, classes[i], true);
            }

            this.onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

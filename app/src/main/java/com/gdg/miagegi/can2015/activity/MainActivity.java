
/**
 * Kouakou Aristide Guy
 */

/**
 *Kouakou Aristide Guy
 *
 */
package com.gdg.miagegi.can2015.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.R.id;
import com.gdg.miagegi.can2015.R.layout;
import com.gdg.miagegi.can2015.R.menu;
import com.gdg.miagegi.can2015.adapter.MainGridAdapter;
import com.gdg.miagegi.can2015.model.MainItem;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.os.Build;

import com.gdg.miagegi.can2015.R;


public class MainActivity extends ActionBarActivity{


    private GridView gridMain;
    private MainGridAdapter mAdapter;
    private List<MainItem> mMainItems = new ArrayList<MainItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainItems.add(new MainItem("Actualite", R.drawable.ic_launcher));//Presentation
        mMainItems.add(new MainItem("Classement", R.drawable.ic_launcher));
        mMainItems.add(new MainItem("Equipes", R.drawable.ic_launcher));
        mMainItems.add(new MainItem("Stades", R.drawable.ic_launcher));
        mMainItems.add(new MainItem("#Can2015", R.drawable.ic_launcher));
        mMainItems.add(new MainItem("Retro. CAN", R.drawable.ic_launcher));

        gridMain = (GridView) findViewById(R.id.gridMain);
        mAdapter = new MainGridAdapter(this, mMainItems);
        gridMain.setAdapter(mAdapter);

        gridMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0){
                    startActivity(new Intent(MainActivity.this, FeedListActivity.class));
                }else if(position == 1){
                   // startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                }else if(position == 2){
                   // startActivity(new Intent(MainActivity.this, TeamActivity.class));
                }else if(position == 3){
                   // startActivity(new Intent(MainActivity.this, ContactActivity.class));
                }else if(position == 4){
                     startActivity(new Intent(MainActivity.this, SocialActivity.class));
                }else if(position == 5){
                    // startActivity(new Intent(MainActivity.this, ContactActivity.class));
                }

            }
        });


    }


}

package com.gdg.miagegi.can2015;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ListView;

import com.gdg.miagegi.can2015.fragment.AproposFragment;
import com.gdg.miagegi.can2015.fragment.FeedDetailsFragment;
import com.gdg.miagegi.can2015.fragment.FeedListFragment;
import com.gdg.miagegi.can2015.fragment.HistoriqueFragment;
import com.gdg.miagegi.can2015.fragment.ProgrammeFragment;
import com.gdg.miagegi.can2015.fragment.StadesFragment;

import java.util.ArrayList;

/**
 * Created by macbookpro on 17/01/15.
 */
public class FeedDetailsActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        navMenuTitles = getResources().getStringArray(R.array.menuitem);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment feedDetailsFragmentfragment = new FeedDetailsFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, feedDetailsFragmentfragment)
                .commit();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch(position) {
            case 0:
                Fragment homefragment = new HomeFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, homefragment)
                        .commit();
                break;
            case 1:
                Fragment historiquefragment = new HistoriqueFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, historiquefragment)
                        .commit();
                break;
            case 2:
                Fragment programmesfragment = new ProgrammeFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, programmesfragment)
                        .commit();
                break;
            case 3:
                Fragment stadesfragment = new StadesFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, stadesfragment)
                        .commit();
                break;
            case 4:
                Fragment feedListFragment = new FeedListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, feedListFragment)
                        .commit();
                break;
            case 5:
                Fragment aproposfragment = new AproposFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, aproposfragment)
                        .commit();
                break;
        }
    }
}

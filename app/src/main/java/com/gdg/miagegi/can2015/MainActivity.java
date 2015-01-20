
/**
 * Kouakou Aristide Guy
 */

/**
 *Kouakou Aristide Guy
 *
 */
package com.gdg.miagegi.can2015;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.gdg.miagegi.can2015.fragment.AproposFragment;
import com.gdg.miagegi.can2015.fragment.FeedListFragment;
import com.gdg.miagegi.can2015.fragment.FluxFragment;
import com.gdg.miagegi.can2015.fragment.HistoriqueFragment;
import com.gdg.miagegi.can2015.fragment.NewsFragment;
import com.gdg.miagegi.can2015.fragment.ProgrammeFragment;
import com.gdg.miagegi.can2015.fragment.SocialListFragment;
import com.gdg.miagegi.can2015.fragment.StadesFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

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
        // enable transitions
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
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
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
                Fragment newsfragment = new SocialListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, newsfragment)
                        .commit();
                break;

            case 5:
                Fragment feedListFragment = new FeedListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, feedListFragment)
                        .commit();
                break;

            case 6:
                Fragment aproposfragment = new AproposFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, aproposfragment)
                        .commit();
                break;


        }
    }

    public void onSectionAttached(int number) {
        switch (number) {

            case 1:
                mTitle = navMenuTitles[0];
                break;
            case 2:
                mTitle = navMenuTitles[1];
                break;
            case 3:
                mTitle = navMenuTitles[2];
                break;
            case 4:
                mTitle = navMenuTitles[3];
                break;
            case 5:
                mTitle = navMenuTitles[4];
                break;
            case 6:
                mTitle = navMenuTitles[5];
                break;
            case 7:
                mTitle = navMenuTitles[6];
                break;
            case 8:
                mTitle = navMenuTitles[7];
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}

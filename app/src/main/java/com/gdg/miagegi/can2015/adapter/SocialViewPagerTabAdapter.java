package com.gdg.miagegi.can2015.adapter;



import com.gdg.miagegi.can2015.R;
import com.gdg.miagegi.can2015.fragment.SocialGridFragment;
import com.gdg.miagegi.can2015.fragment.SocialListFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SocialViewPagerTabAdapter extends FragmentStatePagerAdapter{
	
	private String[] mTabs;

    public SocialViewPagerTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTabs = context.getResources().getStringArray(R.array.social_list_tabs);
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public Fragment getItem(int position) {
    	if(position == 1){
    	return new SocialGridFragment();	
    	}else{
        return SocialListFragment.newInstance(position);
    	}
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

}

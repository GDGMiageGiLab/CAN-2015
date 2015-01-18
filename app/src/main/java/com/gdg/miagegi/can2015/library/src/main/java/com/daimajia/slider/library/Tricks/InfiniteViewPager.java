package com.gdg.miagegi.can2015.library.src.main.java.com.daimajia.slider.library.Tricks;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.daimajia.slider.library.Tricks.*;

/**
 * A {@link android.support.v4.view.ViewPager} that allows pseudo-infinite paging with a wrap-around effect. Should be used with an {@link
 * com.daimajia.slider.library.Tricks.InfinitePagerAdapter}.
 */
public class InfiniteViewPager extends com.daimajia.slider.library.Tricks.ViewPagerEx {

    public InfiniteViewPager(Context context) {
        super(context);
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

}
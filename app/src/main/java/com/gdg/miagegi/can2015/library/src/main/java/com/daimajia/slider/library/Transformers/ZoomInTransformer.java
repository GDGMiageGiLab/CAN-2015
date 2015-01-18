package com.gdg.miagegi.can2015.library.src.main.java.com.daimajia.slider.library.Transformers;

import android.view.View;

import com.daimajia.slider.library.Transformers.*;
import com.nineoldandroids.view.ViewHelper;

public class ZoomInTransformer extends com.daimajia.slider.library.Transformers.BaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		final float scale = position < 0 ? position + 1f : Math.abs(1f - position);
		ViewHelper.setScaleX(view,scale);
        ViewHelper.setScaleY(view,scale);
        ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view,view.getHeight() * 0.5f);
        ViewHelper.setAlpha(view,position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
	}

}

package com.gdg.miagegi.can2015.library.src.main.java.com.daimajia.slider.library.Transformers;

import android.view.View;

import com.daimajia.slider.library.Transformers.*;
import com.nineoldandroids.view.ViewHelper;

public class CubeInTransformer extends com.daimajia.slider.library.Transformers.BaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		// Rotate the fragment on the left or right edge
        ViewHelper.setPivotX(view,position > 0 ? 0 : view.getWidth());
        ViewHelper.setPivotY(view,0);
        ViewHelper.setRotation(view,-90f * position);
	}

	@Override
	public boolean isPagingEnabled() {
		return true;
	}

}

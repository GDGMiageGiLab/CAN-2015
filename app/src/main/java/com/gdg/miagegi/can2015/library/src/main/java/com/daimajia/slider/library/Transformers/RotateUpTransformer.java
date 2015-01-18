package com.gdg.miagegi.can2015.library.src.main.java.com.daimajia.slider.library.Transformers;

import android.view.View;

import com.daimajia.slider.library.Transformers.*;
import com.nineoldandroids.view.ViewHelper;

public class RotateUpTransformer extends com.daimajia.slider.library.Transformers.BaseTransformer {

	private static final float ROT_MOD = -15f;

	@Override
	protected void onTransform(View view, float position) {
		final float width = view.getWidth();
		final float rotation = ROT_MOD * position;

		ViewHelper.setPivotX(view,width * 0.5f);
        ViewHelper.setPivotY(view,0f);
        ViewHelper.setTranslationX(view,0f);
        ViewHelper.setRotation(view,rotation);
	}
	
	@Override
	protected boolean isPagingEnabled() {
		return true;
	}

}

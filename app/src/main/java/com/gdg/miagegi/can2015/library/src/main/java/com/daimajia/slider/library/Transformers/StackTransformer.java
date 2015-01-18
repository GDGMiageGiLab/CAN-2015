package com.gdg.miagegi.can2015.library.src.main.java.com.daimajia.slider.library.Transformers;

import android.view.View;

import com.daimajia.slider.library.Transformers.*;
import com.nineoldandroids.view.ViewHelper;

public class StackTransformer extends com.daimajia.slider.library.Transformers.BaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		ViewHelper.setTranslationX(view,position < 0 ? 0f : -view.getWidth() * position);
	}

}

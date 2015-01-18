package com.gdg.miagegi.can2015.library.src.main.java.com.daimajia.slider.library.Transformers;

/**
 * Created by daimajia on 14-5-29.
 */
import android.view.View;

import com.daimajia.slider.library.Transformers.*;
import com.nineoldandroids.view.ViewHelper;

public class AccordionTransformer extends com.daimajia.slider.library.Transformers.BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        ViewHelper.setPivotX(view,position < 0 ? 0 : view.getWidth());
        ViewHelper.setScaleX(view,position < 0 ? 1f + position : 1f - position);
    }

}
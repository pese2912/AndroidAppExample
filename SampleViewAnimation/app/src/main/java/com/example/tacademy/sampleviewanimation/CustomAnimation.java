package com.example.tacademy.sampleviewanimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Tacademy on 2016-04-22.
 */
public class CustomAnimation extends Animation {
    int sizeX , sizeY;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);

        sizeX = parentWidth- width;
        sizeY = parentHeight - height;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float x = interpolatedTime * sizeX;
        float y = interpolatedTime * sizeY;

        t.getMatrix().setScale(1-interpolatedTime, 1-interpolatedTime);
        t.getMatrix().postTranslate(x,y);

    }
}

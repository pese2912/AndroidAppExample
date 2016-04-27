package com.example.tacademy.samplecustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tacademy on 2016-04-27.
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
        init();
    }
    public CustomView(Context context, AttributeSet attrs){
        super(context,attrs);
        init();

    }
    public void setBitmap(Bitmap bitmap){
        mBitmap = bitmap;
        requestLayout(); // 크기 다시 설정
        invalidate(); // 화면 다시 그리기
    }
    Paint mPaint;
    Bitmap mBitmap;
    int xBitmap, yBitmap;

    private void init(){
        mPaint = new Paint();

        mBitmap = BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_dialog_email);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getPaddingLeft() + getPaddingRight();
        int height = getPaddingBottom() + getPaddingTop();
        if(mBitmap != null){    //크기 결정
            width += mBitmap.getWidth();
            height+=mBitmap.getHeight();
        }

     ////   int mode = MeasureSpec.getMode(widthMeasureSpec);
      //  int size = MeasureSpec.getSize(widthMeasureSpec);

       // switch (mode)
       // {
       //     case MeasureSpec.AT_MOST:
        //        width = (width < size) ? width : size;
        //        break;
       // }

        width = resolveSize(width,widthMeasureSpec);
        height  = resolveSize(height,heightMeasureSpec);

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) { //레이아웃 배치
        super.onLayout(changed, left, top, right, bottom);

        int width, height;
        width = (right - left) - (getPaddingLeft() + getPaddingRight()); //뷰의 width, height 구하기
        height = (bottom - top) - (getPaddingTop() + getPaddingBottom());

        if(mBitmap!=null){
            width -=mBitmap.getWidth();
            height-= mBitmap.getHeight();
        }
        xBitmap= getPaddingLeft() + width/2;
        yBitmap = getPaddingTop() + height/2;



    }

    protected void onDraw(Canvas canvas){ //그리기

        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        if(mBitmap != null){
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0); //채도 0 흑백
            ColorMatrixColorFilter cf = new ColorMatrixColorFilter(cm);
            mPaint.setColorFilter(cf);
            canvas.drawBitmap(mBitmap, xBitmap, yBitmap, mPaint);
        }


    }
}

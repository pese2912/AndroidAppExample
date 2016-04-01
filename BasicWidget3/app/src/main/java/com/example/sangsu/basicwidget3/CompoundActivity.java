package com.example.sangsu.basicwidget3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CompoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound);
    }

    public interface OnImageClickListener {
        public void onImageClick(View v, ImageTextData d);
    }
    public class ImageTextView extends FrameLayout {
        ImageTextData mData;
        ImageView iconView;
        TextView nameView;


        public ImageTextView(Context context) {
            super(context);
            init();
        }

        public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public ImageTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public void setImageText(ImageTextData data) {
            mData = data;
            iconView.setImageResource(data.imageId);
            nameView.setText(data.name);
        }


        OnImageClickListener mImageClickListener;
        public void setOnImageClickListener(OnImageClickListener listener) {
            mImageClickListener = listener;
        }

        private void init() {
            LayoutInflater.from(getContext()).inflate(R.layout.activity_compound,this);
            iconView = (ImageView)findViewById(R.id.image_icon);
            nameView = (TextView)findViewById(R.id.text_name);

            iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mImageClickListener != null) {
                        mImageClickListener.onImageClick(ImageTextView.this, mData);
                    }
                }
            });
        }

        public ImageTextData getImageText() {
            return mData;
        }
        public Drawable getImage() {
            return iconView.getDrawable();
        }
        public String getText() {
            return nameView.getText().toString();
        }



    }
    public class ImageTextData {
        public int imageId;
        public String name;
    }


}

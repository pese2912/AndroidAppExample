package com.example.tacademy.samplemultitypelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-04-20.
 */
public class SendView extends FrameLayout {


    public SendView(Context context){
        super(context);
        init();
    }
    TextView messageView;
    ImageView iconView;
    public void init()
    {
        inflate(getContext(), R.layout.view_send,this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        messageView =(TextView)findViewById(R.id.text_message);
    }

    public void setData(Send send){
        messageView.setText(send.message);
        iconView.setImageDrawable(send.icon);
    }

}

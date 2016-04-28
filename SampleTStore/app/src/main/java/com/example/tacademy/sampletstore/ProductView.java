package com.example.tacademy.sampletstore;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Tacademy on 2016-04-28.
 */

public class ProductView extends FrameLayout {

    public ProductView(Context context) {
        super(context);
        initData();

    }
    ImageView iconView;
    TextView nameView, rankView, descriptionView;

    private void initData(){
        inflate(getContext(),R.layout.view_product,this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        nameView = (TextView)findViewById(R.id.text_name);
        rankView  = (TextView)findViewById(R.id.textView);
        descriptionView = (TextView)findViewById(R.id.text_description);


    }

    Product product;
    ImageRequest mRequest;

    public void setProduct(Product product){
        this.product = product;
        nameView.setText(product.name);
        rankView.setText("score : "+ product.score);
        descriptionView.setText(product.description);


        //Glide.with(getContext()).load(product.thumbnailUrl).into(iconView); //오픈소스 사용

        if(mRequest != null){//이미지 재사용
            mRequest.cancel();
            mRequest = null;
        }
        if(!TextUtils.isEmpty(product.thumbnailUrl)) {


             mRequest= new ImageRequest(product.thumbnailUrl);

            NetworkManager.getInstance().getNetworkData(mRequest, new NetworkManager.OnrResultListener<Bitmap>() {
                @Override
                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {

                    if(mRequest == request) {//이미지 재사용
                        iconView.setImageBitmap(result);
                    }
                }

                @Override
                public void onFail(NetworkRequest<Bitmap> request, int code, String message, Throwable throwable, String body) {

                    if(mRequest == request) { //이미지 재사용
                        iconView.setImageResource(android.R.drawable.ic_dialog_info);
                    }
                }
            });

            iconView.setImageResource(android.R.drawable.ic_dialog_email);
        }
        else{
            iconView.setImageResource(R.mipmap.ic_launcher);
        }
    }
}

package com.example.tacademy.sampletstore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class ImageRequest extends NetworkRequest<Bitmap> {

    String url;
    public ImageRequest(String url){
        this.url = url;
    }

    @Override
    URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    Bitmap parse(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }
}

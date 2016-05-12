package com.example.tacademy.samplegooglelocation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tacademy on 2016-05-09.
 */


public class NetworkManager {
    private static NetworkManager instance;
    public static NetworkManager getInstance(){
        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }

    private static final int DEFAULT_CACHE_SIZE = 50*1024*1024;
    private static final String DEFAULT_CACHE_DIR="miniapp";
    OkHttpClient mClient;

    private NetworkManager(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context context = MyApplication.getContext();
        CookieManager cookieManager = new CookieManager();
        builder.cookieJar(new JavaNetCookieJar(cookieManager)); //메모리 저장하는 쿠키

        File dir = new File(context.getExternalCacheDir(),DEFAULT_CACHE_DIR);
        if(!dir.exists()){
            dir.mkdir();
        }

        builder.cache(new Cache(dir, DEFAULT_CACHE_SIZE));

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        mClient = builder.build(); // 외장메모리 저장하는 캐시

    }

    public interface OnResultListener<T>{
        public  void onSuccess(Request request, T result);
        public void onFail(Request request, IOException exception);
    }

    private static final  int MESSAGE_SUCCESS= 1;
    private static final int MESSAGE_FAIL = 2;

    class NetworkHandler extends Handler{
        public NetworkHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkResult result  = (NetworkResult)msg.obj;

            switch (msg.what){
                case  MESSAGE_SUCCESS:
                    result.listener.onSuccess(result.request, result.result);
                    break;

                case MESSAGE_FAIL:
                    result.listener.onFail(result.request, result.exception);
                    break;

            }
        }
    }

    NetworkHandler mHandler = new NetworkHandler(Looper.getMainLooper());

    static class NetworkResult<T>{
        Request request;
        OnResultListener<T> listener;
        IOException exception;
        T result;

    }

    Gson gson = new Gson();

    private static final String TMAP_SERVER="https://apis.skplanetx.com";
    private static final String TMAP_REVERSE_GEOCODING = TMAP_SERVER+"/tmap/geo/reversegeocoding?coordType=WGS84GEO&addressType=A02&lat=%s&lon=%s&version=1";
    public Request getTmapReverseGeoCoding(Object tag,double lat, double lng, OnResultListener<AddressInfo> listener){

        String url= String.format(TMAP_REVERSE_GEOCODING,"" + lat, ""+lng);

        Request request = new Request.Builder()
                .url(url)
                .header("Accept","application/json")
                .header("appKey", "ec449f14-3190-30de-9143-75e1be5e7521")
                .build();

        final  NetworkResult<AddressInfo> result = new NetworkResult<>();
        result.request= request;
        result.listener = listener;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.exception = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){

                    AddressInfoResult data = gson.fromJson(response.body().charStream(),AddressInfoResult.class);

                    result.result=data.addressInfo;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS,result));


                }else{
                    throw new IOException(response.message());
                }

            }
        });

        return request;
    }


}

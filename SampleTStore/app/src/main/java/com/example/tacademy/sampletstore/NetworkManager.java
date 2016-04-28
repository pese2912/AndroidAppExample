package com.example.tacademy.sampletstore;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class NetworkManager { //싱글턴

    private  static NetworkManager instance;
    public static NetworkManager getInstance(){
        if(instance == null)
        {
            instance =new NetworkManager();
        }
        return  instance;
    }
    ThreadPoolExecutor executor;

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 64;
    private static final int KEEP_ALIVE_TIME = 5000;
    LinkedBlockingDeque<Runnable> mQueue = new LinkedBlockingDeque<>();

    private NetworkManager()
    {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS ,mQueue);


    }
    public interface OnrResultListener<T>{
        public void onSuccess(NetworkRequest<T> request, T result);
        public void onFail(NetworkRequest<T> request, int code, String message, Throwable throwable, String body);
    }


    public<T> void getNetworkData(NetworkRequest<T> request,OnrResultListener<T> listener ){
        request.setOnResultListener(listener);
        executor.execute(request);


    }
    private static final  int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;
    Handler mHandler = new Handler(Looper.getMainLooper()){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkRequest request = (NetworkRequest)msg.obj;

            switch (msg.what){
                case MESSAGE_SUCCESS:
                    request.sendSuccess();
                    break;
                case  MESSAGE_FAIL:
                    request.sendFail();
                    break;
            }
        }
    };
     void sendSuccess(NetworkRequest request){
         Message msg = mHandler.obtainMessage(MESSAGE_SUCCESS,request);
         mHandler.sendMessage(msg);

    }
    void sendFail(NetworkRequest request){
        Message msg = mHandler.obtainMessage(MESSAGE_FAIL, request);
        mHandler.sendMessage(msg);

    }
}

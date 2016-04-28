package com.example.tacademy.sampletstore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tacademy on 2016-04-28.
 */
public abstract class NetworkRequest<T> implements Runnable { //템플릿 패턴

    public  static final String METHOD_GET ="GET";
    public static final String METHOD_POST= "POST";
    abstract URL getURL() throws MalformedURLException;
    public String getRequestMethod(){
        return  METHOD_GET;
    }
    NetworkManager.OnrResultListener<T> mListener;
    public void setOnResultListener(NetworkManager.OnrResultListener<T> listener){

        mListener= listener;
    }

    void sendSuccess(){//main thread
        if(mListener != null && !isCancel){
            mListener.onSuccess(this,result);
        }

    }
    void sendFail(){//main thread

        if(mListener!=null && !isCancel){
            mListener.onFail(this, errorCode,errorMessage,errorException,errorBody);
        }
    }

    public  void writeOutput(OutputStream out){

    }

    public void  setRequestHeaders(HttpURLConnection conn){


    }
    public  void setConfiguration(HttpURLConnection conn){

    }
    T result;
    protected  void processSuccess(InputStream is){
        result = parse(is);
        NetworkManager.getInstance().sendSuccess(this);

    }
    abstract T parse(InputStream is);

    int errorCode;
    String errorMessage;
    Throwable errorException;
    String errorBody;

    protected void processError(int code, String message, Throwable exception, InputStream body){
        errorCode = code;
        errorMessage = message;
        errorException= exception;
        if(body != null){
            //
        }

        NetworkManager.getInstance().sendFail(this);
    }
    int retry= 3;

    boolean isCancel = false;
    public void cancel(){
        isCancel = true;
    }
    public boolean isCancel(){
        return isCancel;
    }

    public void run() {
        int statucCode = 0;
        String statusMessage = null;
        Throwable exception = null;
        InputStream body = null;
        int retryCount = retry;
        while(retryCount > 0 && !isCancel) {
            try {
                URL url = getURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                setRequestHeaders(conn);
                setConfiguration(conn);
                if (getRequestMethod().equals(METHOD_POST)) {
                    conn.setDoOutput(true);
                    conn.setRequestMethod(METHOD_POST);
                    OutputStream out = conn.getOutputStream();
                    if (isCancel) continue;
                    writeOutput(out);
                }
                if (isCancel) continue;
                int code = conn.getResponseCode();
                if (isCancel) continue;
                if (code >= 200 && code < 300) {
                    InputStream is = conn.getInputStream();
                    processSuccess(is);
                    return;
                }
                statucCode = code;
                statusMessage = conn.getResponseMessage();
                body = conn.getInputStream();
                retryCount = 0;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                exception = e;
                retryCount = 0;
            } catch (IOException e) {
                e.printStackTrace();
                exception = e;
                retryCount--;
            }
        }
        if (!isCancel) {
            processError(statucCode, statusMessage, exception, body);
        }
    }
}

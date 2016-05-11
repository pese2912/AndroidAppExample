package com.example.tacademy.samplefacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginManager mLoginManager;
    AccessTokenTracker mTracker;

    Button mloginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        callbackManager = CallbackManager.Factory.create();

        mLoginManager = LoginManager.getInstance();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
           public void onSuccess(LoginResult loginResult) { //로그인 성공시

            }

            @Override
            public void onCancel() {//이미 로그인되어 있는 경우

            }

            @Override
            public void onError(FacebookException error) { // 로그인 실패시

            }
        });

        mloginButton= (Button)findViewById(R.id.btn_login);
        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i("!isLogin()" ,!isLogin()+"");
                if(!isLogin()) { // 토큰이 널이면 로그인 안된경우

                    mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {


                            Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                            //loginButton.setText("logout");
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(MainActivity.this, "login cancel", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(FacebookException error) {

                            Toast.makeText(MainActivity.this, "login fail", Toast.LENGTH_SHORT).show();
                        }
                    });


                    mLoginManager.logInWithReadPermissions(MainActivity.this, null); //로그인

                }else{ // 토큰이 있으면 로그아웃함

                    mLoginManager.logOut();
                   // loginButton.setText("login");
                }
            }
        });

   }

    @Override
    protected void onStart() {
        super.onStart();
        if(mTracker == null){ //로그인 여부에따라 변경함
            mTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) { //로그인 상태가 바뀌면

                    changeButtonText(); //버튼 텍스트 바꿈
                }
            };
        }else{
            mTracker.startTracking(); // 추적시작
        }
        changeButtonText();
    }
    private void changeButtonText(){

        if(isLogin()){
            mloginButton.setText("logout");
        }else{
            mloginButton.setText("login");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTracker.stopTracking();
    }

    private boolean isLogin(){ // 로그인 되어있는지 확인
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token!=null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

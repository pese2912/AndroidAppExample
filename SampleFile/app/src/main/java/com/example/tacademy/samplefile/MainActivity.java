package com.example.tacademy.samplefile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    View sourceView;
    ImageView targetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceView = findViewById(R.id.view_source);
        targetView = (ImageView)findViewById(R.id.image_target);

        Button btn = (Button)findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener() { //  이미지 저장
            @Override
            public void onClick(View v) {
                Bitmap bitmap = getViewBitmap(sourceView);
                File dir = getExternalFilesDir(null); //외장 디렉토리 가져오기
                if(!dir.exists()){ //폴더 없으면 만들기
                    dir.mkdir();
                }
                File saveFile = new File(dir,"my_image.jpg"); //디렉토리에 파일 생성
                try {
                    FileOutputStream fos = new FileOutputStream(saveFile); // 저장
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos); // 이미지 저장
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        btn = (Button)findViewById(R.id.btn_load);
        btn.setOnClickListener(new View.OnClickListener() { //이미지 불러오기
            @Override
            public void onClick(View v) {
                File loadFile = new File(getExternalFilesDir(null), "my_image.jpg");

                try {
                    FileInputStream fis = new FileInputStream(loadFile); //불러오기
                    Bitmap bm = BitmapFactory.decodeStream(fis); //이미지 가져오기
                    targetView.setImageBitmap(bm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private Bitmap getViewBitmap(View v) { //캡쳐
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }
}


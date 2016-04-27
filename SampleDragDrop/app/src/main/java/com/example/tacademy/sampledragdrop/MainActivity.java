package com.example.tacademy.sampledragdrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{

    ListView listView;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        TextView textView = (TextView)findViewById(R.id.btn_item1);
        textView.setOnLongClickListener(this);
        findViewById(R.id.btn_item2).setOnLongClickListener(this);
        findViewById(R.id.btn_item3).setOnLongClickListener(this);

        listView.setOnDragListener(new View.OnDragListener() {
            Drawable oldColor;
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()){

                    case DragEvent.ACTION_DRAG_STARTED: //드래그 시작
                        View source = (View)event.getLocalState();
                        source.setVisibility(View.GONE);
                        return true;

                    case DragEvent.ACTION_DRAG_ENTERED: //리스트뷰 안에 들어갔을 때
                        oldColor = v.getBackground();
                        v.setBackgroundColor(Color.GREEN);
                        return  true;

                    case DragEvent.ACTION_DRAG_EXITED: //밖으로 나오면
                        v.setBackgroundDrawable(oldColor);
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        break;

                    case DragEvent.ACTION_DROP: //드롭할때
                        int x = (int) event.getX();
                        int y= (int) event.getY();
                        ClipData data = event.getClipData(); //데이터
                        String text = data.getItemAt(0).getText().toString();
                        int position = listView.pointToPosition(x,y); // 좌표 얻어오기


                        if(position != ListView.INVALID_POSITION){ //리스트뷰에 올바른 위치이면 삽입
                            mAdapter.insert(text,position);

                        }else{
                            mAdapter.add(text);
                        }
                        break;

                    case DragEvent.ACTION_DRAG_ENDED: //드래그 끝나면
                        View source2 = (View)event.getLocalState();
                        source2.setVisibility(View.VISIBLE);
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        String text = (String)v.getTag();
        ClipData.Item item = new ClipData.Item(text);
        ClipData data = new ClipData(text, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);
        View.DragShadowBuilder builder = new View.DragShadowBuilder(v);

        v.startDrag(data,builder,v,0); //드래그
        return true;
    }
}

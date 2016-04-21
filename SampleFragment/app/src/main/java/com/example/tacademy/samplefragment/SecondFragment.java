package com.example.tacademy.samplefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }

    public interface OnMessageCallBack{ //인터페이스 정의
        public void receiveMessage(String message);

    }
    OnMessageCallBack callBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMessageCallBack){ // 액티비티가 인터페이스를 상속한경우
            callBack = (OnMessageCallBack)context; //형변환
        }else{
            callBack = null; //아니면 null
        }
    }

    ListView listView;
    ArrayAdapter<String> mAdaptr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        mAdaptr = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdaptr);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트뷰 아이템 선택시
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String)listView.getItemAtPosition(position);
                //...

             //   ((MainActivity)getActivity()).receiveText(text); //메인액티비티 메소드 호출 (값전달) - > 확장성 떠어짐
                if(callBack != null){
                    callBack.receiveMessage(text); //메인액티비티로 값 전달 호출 (값전달)
                }

            }
        });
        initData();
        return view;
    }

    public void initData(){

        for(int i=0; i<40; i++){
            mAdaptr.add("item " + i);
        }
    }

}



package com.example.android1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DayAdapter  extends BaseAdapter implements OnItemClickListener{

        private static final String TAG="grid Adapter View";

        private final Context mContext;
        private LayoutInflater inflater;
        //일(1~31)을 저장할 벡터
        private ArrayList<Integer> items = new ArrayList<Integer>();
        private View view;
        private int year,month;

        OnItemClickListener listener;


    public DayAdapter(Context context, ArrayList < Integer > items,int year,int month) {
        this.mContext = context;
        this.items = items;
        this.year=year;
        this.month=month+1;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void setOnItemClickListener(AdapterView.OnItemClickListener 클릭) {
        this.listener=listener;
    }

    @Override
    public int getCount () {
        return items.size();
    }

    @Override
    public Object getItem ( int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView ( int position, View view, ViewGroup parent){
        if (view == null) {
            view = inflater.inflate(R.layout.day_item, parent, false);
            this.view = view;
        }

        Log.d(TAG,"create "+position);

        TextView dayTv = view.findViewById(R.id.day);//시작 문자는 대문자 X 소문자로 시작


        if(items.get(position) == null) {//null 확인 후에 공백 문자 넣음
            dayTv.setText("");
        } else {
            dayTv.setText(items.get(position) + "");//String 으로 해야해서 +"" 추가함
        }
        Log.d(TAG, items.get(position) + ", "+dayTv.getText());

        dayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "클릭");
                if (getItem(position) != null) {//null 값일 때 출력 X
                    print(year + "년" + month + "월" + ((int) getItem(position)) + "일");
                    Log.d(TAG, year + "년" + month + "월" + ((int)getItem(position)) + "일");
                }
            }
        });
        return view;
    }


    void print(String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }
}
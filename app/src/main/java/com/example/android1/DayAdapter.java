package com.example.android1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class DayAdapter  extends BaseAdapter implements OnItemClickListener{

    private static final String TAG="grid Adapter View";

    private final Context mContext;
    //일(1~31)을 저장할 벡터
    private ArrayList<Integer> items = new ArrayList<Integer>();
    private View convertView;

    OnItemClickListener listener;

    public DayAdapter(Context context, ArrayList<Integer> items) {
        this.mContext = context;
        this.items = items;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (listener != null) {
            listener.onItemClick(view, position);
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getDayText(int position){
        TextView dayText=convertView.findViewById(R.id.day);
        return dayText.getText().toString();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.day_item,parent, false);
            this.convertView=convertView;
        }
        TextView dayTv = convertView.findViewById(R.id.day);//시작 문자는 대문자 X 소문자로 시작
        if(items.get(position)==null){//null 확인 후에 공백 문자 넣음
            dayTv.setText("");
            Log.d(TAG,items.get(position)+"");
        }
        else {
            dayTv.setText(items.get(position) + "");//String으로 해야해서 +"" 추가함
            Log.d(TAG, items.get(position) + "");
        }

        return convertView;
    }
}

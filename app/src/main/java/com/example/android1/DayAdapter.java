package com.example.android1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class DayAdapter  extends BaseAdapter {
    private final Context mContext;
    private LayoutInflater inflater;
    //일(1~31)을 저장할 벡터
    private Vector<Integer> days = new Vector<Integer>();

    public DayAdapter(Context context, Vector<Integer> days) {
        this.mContext = context;
        this.days = days;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View View, ViewGroup parent) {
        if(View == null) {
            View = inflater.inflate(R.layout.day_item,parent, false);
        }
        TextView DayTv = View.findViewById(R.id.datTV_item);
        DayTv.setText(days.get(position)+"");

        return View;
    }
}

package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MonthViewActivity extends AppCompatActivity {

    private static final String TAG="month View";
    int year;
    int month;
    int firstDay; //시작 요일
    int allDay; //한달 일 수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intent 정보 뽑아오기
        Intent intent=getIntent();
        year=intent.getIntExtra("year",-1);//없는 경우엔 -1 return
        month=intent.getIntExtra("month",-1);

        if(year==-1||month==-1){//year, month에 넘어온 값이 없으면(-1이면) 현재값 읽어오기
            year= Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
        }

        //상단 연,월 표시
        TextView yearMonth=findViewById(R.id.yearMonthText);
        yearMonth.setText(year+"년 "+(month+1)+"월");//month(0~11이기때문에)


        //다음 버튼 클릭 이벤트
        Button nextBtn =findViewById(R.id.nextMonthButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MonthViewActivity.class);

                month++;
                if(month>=12){//12월이면 1월로 바꾸고 연도 +1
                    month=month-12;
                    year++;
                }
                //시작될 때 year, month 정보도 같이 전달
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivity(intent);
                finish();
            }
        });


        //이전 버튼 클릭 이벤트
        Button lastBtn = findViewById(R.id.lastMonthButton);
        lastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MonthViewActivity 재시작
                Intent intent = new Intent(getApplicationContext(),
                        MonthViewActivity.class);

                //시작될 때 year, month 정보도 같이 전달
                if(month <= 0){//1월이면 12월로 바꾸고 연도 -1
                    month = 11;
                    intent.putExtra("year",(year-1));
                    intent.putExtra("month", month);
                }
                else {
                    intent.putExtra("year", year);
                    intent.putExtra("month", (month-1));
                }

                startActivity(intent);
                finish();
            }
        });


        //gridView 일 표시
        String date=(month+1)+"/"+"01/"+year;
        String pattern ="MM/dd/yyyy";
        Date selDate= null;
        try {
            selDate = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c=Calendar.getInstance();
        c.setTime(selDate);
        String test="실패";
        if(selDate!=null)
        {
            test="성공";
        }

        firstDay = c.get(Calendar.DAY_OF_WEEK); //첫째날
        allDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);//그 달의 마지막 일

        ArrayList<Integer> days = new ArrayList<Integer>();

        for(int i=1; i<firstDay; i++){
            days.add(null);
        }
        for(int i=1; i<=allDay; i++){
            days.add(i);
        }

        Log.d(TAG,test+": "+date+", "+days.size());

        for(int i=0;i<days.size();i++){
            Log.d(TAG,i+". "+days.get(i)+"");
        }

        //어댑터 생성
        DayAdapter adapter= new DayAdapter(getApplicationContext(),days);
        // 어탭터 연결
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//토스트 메시지, 뜨지 않음
                //int item=(int)(adapter.getItem(position));
                Log.d(TAG,"클릭");
                if(adapter.getItem(position)!=null){//null 값일 때 출력 X
                    print(year+"년"+month+"월"+((int)adapter.getItem(position))+"일");
                    Log.d(TAG, year+"년"+month+"월"+((int)adapter.getItem(position))+"일");
                }
            }

        });

    }

    void print(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
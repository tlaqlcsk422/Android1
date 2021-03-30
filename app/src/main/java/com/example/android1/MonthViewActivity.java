package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android1.R;

import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {

    int year;
    int month;
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
            month=Calendar.getInstance().get(Calendar.MONTH);
        }

        TextView yearMonth=findViewById(R.id.yearMonthText);
        yearMonth.setText(year+"년 "+(month+1)+"월");//month(0~11이기때문에)

        //print(year+"."+(month+1));

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

        Button lastBtn=findViewById(R.id.lastMonthButton);
        lastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MonthViewActivity.class);

                month--;
                if(month<0){//0월이면 12월로 바꾸고 연도 -1
                    month=month+12;
                    year--;
                }
                //시작될 때 year, month 정보도 같이 전달
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivity(intent);
                finish();
            }
        });
    }

    void print(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {
    int year;
    int month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intent 정보 뽑아오기
        Intent intent = getIntent();
        //extra 값도
        year = intent.getIntExtra("year",-1);//없는 경우엔 -1 return
        month = intent.getIntExtra("month",-1);

        //year, month에 넘어온 값이 없으면(-1이면) 현재값 읽어오기
        if(year == -1 || month == -1) {
            //상단 년, 월
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
        }

        TextView yearMonth = findViewById(R.id.year_month);
        yearMonth.setText(year + "년" + month+1 + "월");//month(0~11이기때문에)

        //다음 버튼 클릭 이벤트
        Button nextBtn = findViewById(R.id.next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MonthViewActivity 재시작
                Intent intent = new Intent(getApplicationContext(),
                        MonthViewActivity.class);
                month += 1;
                //시작될 때 year, month 정보도 같이 전달
                if(month >= 13){//12월이면 1월로 바꾸고 연도 +1
                    intent.putExtra("year",year+1);
                    intent.putExtra("month", month = 0);
                }
                else {
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                }
                startActivity(intent);
                finish();//현재 activity 죽이기
            }
        });
    }
}
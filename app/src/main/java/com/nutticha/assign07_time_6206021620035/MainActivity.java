package com.nutticha.assign07_time_6206021620035;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Time , Showtime;
    private Button btnStart , btnStop;
    private long mstartTime = 0L;
    private Handler mHandler = new Handler(Looper.myLooper());
    private boolean time_start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Time = (TextView) findViewById(R.id.time);
        Showtime = (TextView) findViewById(R.id.showtime);
        Time.setText("00:00:00");
        Showtime.setText("00:00:00");
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop  = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnStart){
            mstartTime = SystemClock.uptimeMillis();
            mHandler.postDelayed(mUpdateTimeTask, 1000);
            time_start = true;
            Time.setText("00:00:00");
            Toast.makeText(this,"Start Time",Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.btnStop){
            time_start = false;
            mHandler.removeCallbacks(mUpdateTimeTask);
            Toast.makeText(this , "Stop Time" , Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable mUpdateTimeTask = new Runnable(){
        public void run() {
            if (time_start)
                mHandler.postDelayed(this , 1000);
            final long nowTime = SystemClock.uptimeMillis();
            final long startTime = mstartTime;

            long millis = ( nowTime - startTime) ;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;


            Time.setText(String.format("%02d",hours) + ":" +String.format("%02d",minutes) + ":" + String.format("%02d",seconds));
        }
    };
}
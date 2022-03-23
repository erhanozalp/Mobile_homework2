package com.example.a2019141047_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private long start_timer_millis_button1 = 600000;
    private long start_timer_millis_button2 = 600000;

    private Button button1;
    private Button button2;
    private Button pause;
    private Button reset;

    private CountDownTimer bt1CountdownTimer;
    private CountDownTimer bt2CountdownTimer;

    private boolean button1TimerRunning = false;
    private boolean button2TimerRunning = false;
    private long timeLeftButton1 = start_timer_millis_button1;
    private long timeLeftButton2 = start_timer_millis_button2;

    private String checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        pause = findViewById(R.id.pause);
        reset = findViewById(R.id.restart);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!button2TimerRunning){
                    bt2StartTimer();
                }

                if(button1TimerRunning){
                    bt1PauseTimer();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!button1TimerRunning){
                    bt1StartTimer();
                }

                if(button2TimerRunning){
                    bt2PauseTimer();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button1TimerRunning){
                    bt1PauseTimer();
                }
                else if(button2TimerRunning){
                    bt2PauseTimer();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });


    }

    private void updateCountDownButton1(){
        int minutes = (int) (timeLeftButton1 / 1000) / 60;
        int seconds = (int) (timeLeftButton1 / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);

        button1.setText(timeLeftFormatted);
    }

    private void updateCountDownButton2(){
        int minutes = (int) (timeLeftButton2 / 1000) / 60;
        int seconds = (int) (timeLeftButton2 / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);

        button2.setText(timeLeftFormatted);
    }

    private void bt1StartTimer(){
        bt1CountdownTimer = new CountDownTimer(timeLeftButton1, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftButton1 = l;
                updateCountDownButton1();
            }

            @Override
            public void onFinish() {
                button1TimerRunning = false;
            }
        }.start();

        button1TimerRunning = true;
    }

    private void bt2StartTimer(){
        bt2CountdownTimer = new CountDownTimer(timeLeftButton2, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftButton2 = l;
                updateCountDownButton2();
            }

            @Override
            public void onFinish() {
                button2TimerRunning = false;
            }
        }.start();

        button2TimerRunning = true;
    }

    private void bt1PauseTimer() {
        bt1CountdownTimer.cancel();
        button1TimerRunning = false;
    }

    private void bt2PauseTimer() {
        bt2CountdownTimer.cancel();
        button2TimerRunning = false;
    }

    private void resetTimer() {
        timeLeftButton1 = start_timer_millis_button1;
        updateCountDownButton1();
        timeLeftButton2 = start_timer_millis_button2;
        updateCountDownButton2();

        if(button1TimerRunning){
            bt1PauseTimer();
        }
        else if(button2TimerRunning){
            bt2PauseTimer();
        }
    }
}

/*else{
                    if(checker == "bt1"){
                        bt1StartTimer();
                        checker = "default";
                        pause.setText("Pause");
                    }
                    else if(checker == "bt2"){
                        bt2StartTimer();
                        checker = "default";
                        pause.setText("Pause");
                    }
                    else{
                        checker = "default";
                    }
                }

                if(button1TimerRunning) {
                    bt1PauseTimer();
                    button2.setBackgroundColor(0x00FF00);
                    bt2StartTimer();
                }
                else{
                    if(button2TimerRunning = false){
                        button2.setBackgroundColor(0x00FF00);
                        bt2StartTimer();
                    }
                }

 */
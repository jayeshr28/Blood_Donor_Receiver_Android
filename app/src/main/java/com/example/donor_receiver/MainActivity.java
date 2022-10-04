package com.example.donor_receiver;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                MainActivity.this.setContentView(R.layout.activity_main);
            }
        }.start();
    }
    public void donate(View view) {
        Intent intent= new Intent(this,DonorSignIn.class);
        startActivity(intent);
    }
    public void receive(View view) {
        Intent intent= new Intent(this,receiverSignIn.class);
        startActivity(intent);
    }
}

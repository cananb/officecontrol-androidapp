package com.exam.ex_rec_card.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.exam.ex_rec_card.MainActivity;
import com.exam.ex_rec_card.R;

public class SplashScreen extends Activity {
    Animation uptodown,downtoup;

    RelativeLayout first,second;

    AnimationDrawable empaAnimation ,empaAnimation2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        first=findViewById(R.id.first);
        second=findViewById(R.id.second);
       // uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
       // downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
       // first.setAnimation(uptodown);
        //second.setAnimation(downtoup);

        ImageView imageView =(ImageView)findViewById(R.id.image);
        imageView.setBackgroundResource(R.drawable.animation);
       // empaAnimation=(AnimationDrawable) imageView.getBackground();
       // empaAnimation.start();

        Thread timerThread= new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }
        };
        timerThread.start();

        ImageView imageView2 =(ImageView)findViewById(R.id.image2);
        imageView2.setBackgroundResource(R.drawable.animation2);
        empaAnimation2=(AnimationDrawable) imageView2.getBackground();
        empaAnimation2.start();



    }

}

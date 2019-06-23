package com.maruf.mb.algotech;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.maruf.mb.algotech.Read_data.Session;

import static maes.tech.intentanim.CustomIntent.customType;

public class Splash_Screen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    Session session;
    ImageView SplashImg,SplashImg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        session = new Session( Splash_Screen.this );
        customType(Splash_Screen.this,"fadein-to-fadeout");
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein
         * */
        SplashImg=findViewById(R.id.splash_img);
        SplashImg2=findViewById(R.id.full_logo);
//        YoYo.with(Techniques.BounceInUp)
//                .duration(700)
//                .repeat(0)
//                .playOn(SplashImg);
        YoYo.with(Techniques.ZoomIn)
                .duration(900)
                .repeat(0)
                .playOn(SplashImg2);
        new Handler().postDelayed( new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (session.getemail().isEmpty()){
                    startActivity(new Intent(Splash_Screen.this, Home_without_Login.class));
                    Splash_Screen.this.finish();
//                    overridePendingTransition();


                }
                else{
                    startActivity(new Intent(Splash_Screen.this,Courses_list.class));
                    Splash_Screen.this.finish();


                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

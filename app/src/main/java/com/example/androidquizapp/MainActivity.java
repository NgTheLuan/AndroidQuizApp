package com.example.androidquizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnSignUp, btnSignIn;
    ImageView imgChuongNoel,imgstar;
    Animation animation;

//    private VideoView videoBG;
//    MediaPlayer mMediaPlayer;
//    int mCurrentVideoPosition;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        videoBG = findViewById(R.id.videoView);
//
//        Uri uri = Uri.parse("android.resource://"
//                +getPackageName()
//                +"/"
//                +R.raw.backgroundeffetnoel);
//
//        videoBG.setVideoURI(uri);
//
//        videoBG.start();
//
//        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mMediaPlayer = mp;
//
//                mMediaPlayer.setLooping(true);
//
//                if (mCurrentVideoPosition!=0){
//                    mMediaPlayer.seekTo(mCurrentVideoPosition);
//                    mMediaPlayer.start();
//                }
//            }
//        });


        imgChuongNoel = findViewById(R.id.imgChuongNoel);
        animation = AnimationUtils.loadAnimation(this,R.anim.chuongnoel);
        imgChuongNoel.startAnimation(animation);

//        imgstar = findViewById(R.id.imgstart);
//        animation = AnimationUtils.loadAnimation(this,R.anim.hieuung);
//        imgstar.startAnimation(animation);

        btnSignIn=findViewById(R.id.btnSignIn);
        btnSignUp=findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });

    }
}

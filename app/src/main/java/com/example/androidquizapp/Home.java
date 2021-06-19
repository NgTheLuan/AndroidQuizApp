package com.example.androidquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidquizapp.Common.Common;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    FirebaseDatabase database;

    Button BTstart,BTrank,BTtips,BTlogout;
    TextView TxtFullName;
//    ImageView imgHinh;
//    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database=FirebaseDatabase.getInstance();

//        imgHinh = findViewById(R.id.imghinh);
//        animation = AnimationUtils.loadAnimation(this,R.anim.hieuung);
//        imgHinh.startAnimation(animation);


        BTstart = findViewById(R.id.buttonstart);
        BTrank = findViewById(R.id.buttonrank);
        BTtips = findViewById(R.id.buttonhelp);
        BTlogout = findViewById(R.id.buttonexit);


        TxtFullName = findViewById(R.id.txtFullName);
        TxtFullName.setText(Common.currentUser.getName());

        BTstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LevelGame.class);
                startActivity(intent);
            }

        });

        BTrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRank=new Intent(Home.this,RankScore2.class);
                startActivity(intentRank);
            }

        });

        BTtips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGuide=new Intent(Home.this, TipsEnglish.class);
                startActivity(intentGuide);
            }

        });

        BTlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}

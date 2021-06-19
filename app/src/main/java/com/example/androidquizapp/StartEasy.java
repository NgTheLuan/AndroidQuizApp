package com.example.androidquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidquizapp.Common.Common;
import com.example.androidquizapp.Model.QuestionEasy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class StartEasy extends AppCompatActivity {

    Button btnPlay;

    FirebaseDatabase database;
    DatabaseReference questionsEasy;

    ImageView imgChuongNoel;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imgChuongNoel = findViewById(R.id.imgChuongNoel);
        animation = AnimationUtils.loadAnimation(this,R.anim.chuongnoel);
        imgChuongNoel.startAnimation(animation);


        database = FirebaseDatabase.getInstance();
        questionsEasy = database.getReference("QuestionsEasy");


        loadQuestion(Common.cateloryId);


        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartEasy.this, EasyPlaying.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadQuestion(String cateloryId) {
        // First, clear List if have old question
        if (Common.questionEasyList.size() > 0 )
            Common.questionEasyList.clear();
        questionsEasy.orderByChild("categoryId").equalTo(cateloryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            QuestionEasy quesEasy = postSnapshot.getValue(QuestionEasy.class);
                            Common.questionEasyList.add(quesEasy);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//        // Random list
//        Collections.shuffle(Common.questionEasyList);
    }
}

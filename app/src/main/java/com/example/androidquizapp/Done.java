package com.example.androidquizapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidquizapp.Common.Common;
import com.example.androidquizapp.Model.History;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Done extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore,getTxtResultQuestion;
    ProgressBar progressBar;

    //FirebaseDatabase database;
    //DatabaseReference question_score;
    int score = 0;
    int Level= 0;

    private VideoView videoBG;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);


        videoBG = findViewById(R.id.videoViewSnow);

        Uri uri = Uri.parse("android.resource://"
                +getPackageName()
                +"/"
                +R.raw.hieuunghoaroicucdep);

        videoBG.setVideoURI(uri);

        videoBG.start();

        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaPlayer = mp;

                mMediaPlayer.setLooping(true);

                if (mCurrentVideoPosition!=0){
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });


        //database = FirebaseDatabase.getInstance();
        //question_score = database.getReference("Question_Score");

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = findViewById(R.id.totalQuestion);
        progressBar = findViewById(R.id.doneProgressBar);
        btnTryAgain = findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Done.this, LevelGame.class);
                startActivity(intent);
                finish();
            }
        });

        //Get data from bundle and set to view
        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");
            Level=extra.getInt("Level");

            txtResultScore.setText(String.format("SCORE : %d", score));
            getTxtResultQuestion.setText(String.format("PASSED : %d / %d", correctAnswer, 10));
            Toast.makeText(Done.this, "Thank you !!!", Toast.LENGTH_SHORT).show();
            progressBar.setMax(10);
            progressBar.setProgress(correctAnswer);
        }
//
//        }//Upload point to DB
//            question_score.child(String.format("%s_%s", Common.currentUser.getName(),
//                    Common.cateloryId))
//                    .setValue(new QuestionScore(String.format("%s_%s",Common.currentUser.getName(),
//                            Common.cateloryId),
//                            Common.currentUser.getName(),
//                            String.valueOf(score)));

//        RankTest rankTest  = new RankTest();
//        rankTest.setUserName(Common.currentUser.getName());
//        rankTest.setScore(score);
//        FirebaseDatabase.getInstance().getReference("Ranking").child(Common.currentUser.getName()).setValue(rankTest);

        Calendar calendar = Calendar.getInstance();

        String time = "Day " + calendar.get(Calendar.DATE)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);


        History history = new History();
        history.setUser(Common.currentUser.getName());
        history.setScore(String.valueOf(score));
        history.setLevel(String.valueOf(Level));
        FirebaseDatabase.getInstance().getReference("History").child(time).setValue(history);
    }
}

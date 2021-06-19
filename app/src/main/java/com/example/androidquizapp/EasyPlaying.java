package com.example.androidquizapp;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidquizapp.Common.Common;
import com.example.androidquizapp.Model.QuestionEasy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EasyPlaying extends Activity implements View.OnClickListener {

    final static long INTERVAL = 450;
    final static long TIMEOUT = 10000;
    int progressValue = 0;

    CountDownTimer mCountDown;
    SoundPool MouseClick = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

    int index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer,id_mClick;
    MediaPlayer SoundBackground = new MediaPlayer();
    CheckBox SoundTest;

    ArrayList <QuestionEasy> ranListQuesEasy = new ArrayList<>();
    ProgressBar progressBar;
    ImageView question_image;
    Button btnA,btnB,btnC,btnD;
    TextView txtScore, txtQuestionNum,question_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_playing);

        //id_mClick = MouseClick.load(this, R.raw.click, 1);
        id_mClick = MouseClick.load(this, R.raw.click, 1);
        SoundBackground = MediaPlayer.create(this, R.raw.level_easy);
        SoundBackground.start();
        SoundTest = findViewById(R.id.bgSpeaker);
        SoundTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean kt) {
                if (kt) {
                    SoundBackground.stop();
                } else try {
                    SoundBackground.prepare();
                    SoundBackground.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        question_image= findViewById(R.id.question_image);
        txtScore = findViewById(R.id.txtScore);
        txtQuestionNum = findViewById(R.id.totalQuestion);
        question_text = findViewById(R.id.question_text);

        progressBar = findViewById(R.id.progressBar);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mCountDown.cancel();
        if (index < totalQuestion){
            Button clickedButton = (Button)view;
            if (clickedButton.getText().equals(ranListQuesEasy.get(index).getCorrectAnswer())){
                //Choose correct answer;
                score+=10;
                ++index;
                correctAnswer++;
                showQuestion(index);//next question
            }
            else{
                //choose wrong answer
                correctAnswer++;
                ++index;
                showQuestion(index);//next question

            }
            txtScore.setText(String.format("Score: %d",score));

        }

    }

    private void showQuestion(int index) {
        if (index < 10){
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuestion,10));
            progressBar.setProgress(0);
            progressValue = 0;

            if (ranListQuesEasy.get(index).getIsImageQuestion().equals("true")){
                // if is image
                Picasso.with(getBaseContext())
                        .load(ranListQuesEasy.get(index).getQuestion())
                        .into(question_image);
                question_image.setVisibility(View.VISIBLE);
                question_text.setVisibility(View.INVISIBLE);
            }
            else{
                question_text.setText(ranListQuesEasy.get(index).getQuestion());

                // if question is text, we will set image invisible
                question_image.setVisibility(View.INVISIBLE);
                question_text.setVisibility(View.VISIBLE);
            }

            btnA.setText(ranListQuesEasy.get(index).getAnswerA());
            btnB.setText(ranListQuesEasy.get(index).getAnswerB());
            btnC.setText(ranListQuesEasy.get(index).getAnswerC());
            btnD.setText(ranListQuesEasy.get(index).getAnswerD());

            mCountDown.start(); // StartEasy timer
        }
        else {
            // if it is final question
            Intent intent = new Intent(this,Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            dataSend.putInt("Level",1);
            intent.putExtras(dataSend);
            startActivity(intent);
            SoundBackground.stop();
            finish();
        }
    }

    //Press Ctrl + o

    @Override
    protected void onPostResume() {
        super.onPostResume();

        totalQuestion = Common.questionEasyList.size();

        for(int i=0;i<totalQuestion;i++)
        {
            ranListQuesEasy.add(Common.questionEasyList.get(i));
        }

        Collections.shuffle(ranListQuesEasy);
        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);

            }
        };
        showQuestion(index);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDown.cancel();
    }
}

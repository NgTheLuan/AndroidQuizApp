package com.example.androidquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelGame extends AppCompatActivity {

    Button BT,BTEasy, BTMedium, BTHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        BTEasy = findViewById(R.id.btnde);
        BTEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelGame.this, StartEasy.class);
                startActivity(intent);
            }
        });

        BTHard = findViewById(R.id.btnkho);
        BTHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelGame.this, StartHard.class);
                startActivity(intent);
            }
        });

        BTMedium = findViewById(R.id.btnbt);
        BTMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelGame.this, StartNormal.class);
                startActivity(intent);
            }
        });

        BT = findViewById(R.id.btntrove);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelGame.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

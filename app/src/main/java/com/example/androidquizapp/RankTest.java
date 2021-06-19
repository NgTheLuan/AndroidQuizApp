package com.example.androidquizapp;

import android.content.Intent;

public class RankTest implements Comparable<RankTest>{
    public long score;
    public String userName;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(RankTest o) {
        int compareScore = Integer.parseInt (String.valueOf(o.getScore()));
        return compareScore - Integer.parseInt (String.valueOf(this.getScore())) ;
    }
}

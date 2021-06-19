package com.example.androidquizapp.Common;

import com.example.androidquizapp.Model.QuestionEasy;
import com.example.androidquizapp.Model.QuestionHard;
import com.example.androidquizapp.Model.QuestionNormal;
import com.example.androidquizapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static String cateloryId;
    public static User currentUser;
    public static List<QuestionEasy> questionEasyList = new ArrayList<>();
    public static List<QuestionNormal> questionNormalList = new ArrayList<>();
    public static List<QuestionHard> questionHardList = new ArrayList<>();
}

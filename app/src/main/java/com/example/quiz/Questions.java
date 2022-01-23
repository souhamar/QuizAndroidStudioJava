package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Questions extends AppCompatActivity {

public static  List<QuestionsList> getQuestions(){
    final List<QuestionsList>  questionsLists = new ArrayList<>();

    final QuestionsList q1 = new QuestionsList(" What does HTML stands for", "Hypertext Markup Language", "Hypertext Machine language","Hightext machine language","Hypertext Markup Language","Hypertext and links markup language","");
    final QuestionsList q2 = new QuestionsList("Which statement is used to stop a loop in java ", "break", "return","break","exit","stop","");
    final QuestionsList q3 = new QuestionsList("Which of the following is the correct way to use the standard namespace in C++", "Using namespace std;", "Using namespace std;","Standard namespace used;","Using standard namespace;","Using namespace standard;","");
    final QuestionsList q4 = new QuestionsList("Which of the following is not a type of computer code related to Program Execution", "Hex Code", "Source code","Machine Code","Bytecode","Hex Code","");

    questionsLists.add(q1);
    questionsLists.add(q2);
    questionsLists.add(q3);
    questionsLists.add(q4);



    return  questionsLists;
}

}
package com.example.a303.teachlink;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StudentQuestionShow extends AppCompatActivity {
    private TextView TV_Question,TV_A,TV_B,TV_C,TV_D;
    private Button BT_Ans;
    private User user;
    private List<Question> questionList=new ArrayList<>();
    //*****juiz*******
    private static Handler mHandler;
    //***************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_question_show);

        findViews();
        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");

        //*****juiz*******get
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //What you want to do with these data? Just write here!
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                int select = msg.what;
                /*num 1: login*/
                String content = bundle.getString("content");//json
                //#code......................................................
                Gson gson = new Gson();
                JsonArray jsonArray = gson.fromJson(content, JsonArray.class);
                for (JsonElement element : jsonArray) {
                    Question question = gson.fromJson(element, Question.class);
                    questionList.add(question);
                }
                setQuestion();


                //#code............................................................
            }
        };
        //*****juiz*******get
        //********juiz*********send
        try {
            URL url = new URL("http://192.168.1.170");
            ArrayMap<String , String> reqData = new ArrayMap();
            reqData.put("select","class_history");

            WebData webData = new WebData(url,mHandler);
            webData.setReqData(reqData);
            webData.getData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //*****************

    }

    private void setQuestion() {
        Question askquestion=questionList.get(questionList.size()-1);
        TV_Question.setText(askquestion.getTitle());
        Log.d("AA",askquestion.getQuesAns().get(0));
        TV_A.setText(askquestion.getQuesAns().get(0));
        TV_B.setText(askquestion.getQuesAns().get(1));
        TV_C.setText(askquestion.getQuesAns().get(2));
        TV_D.setText(askquestion.getQuesAns().get(3));
    }

    private void findViews() {
        TV_Question=(TextView) findViewById(R.id.TV_Question);
        TV_A=(TextView) findViewById(R.id.TV_A);
        TV_B=(TextView) findViewById(R.id.TV_B);
        TV_C=(TextView) findViewById(R.id.TV_C);
        TV_D=(TextView) findViewById(R.id.TV_D);
        BT_Ans=(Button) findViewById(R.id.BT_Ans);
        BT_Ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentQuestionShow.this,StudentQuestion.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

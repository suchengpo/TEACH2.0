package com.example.a303.teachlink;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TeacherQuestion extends AppCompatActivity {
    private User user;
    private EditText ET_Class,ET_Question,ET_A,ET_B,ET_C,ET_D,ET_Correct;
    private Button BT_send;
    private String jsonStr;
    private Question question;
    private String classname,title,std_ans,A,B,C,D;
    private ArrayList<String> ans=new ArrayList<String>();
    //*****juiz*******
    private static Handler mHandler;
    //***************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_question);

        findViews();
        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");

        if (user !=null)
        {
            BT_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generateQuestion();
                    Gson gson = new Gson();
                    jsonStr = gson.toJson(question);
                    Log.d("JsonTry",jsonStr);
                    //********juiz*********send
                    try {
                        URL url = new URL("http://192.168.1.170");
                        ArrayMap<String , String> reqData = new ArrayMap();
                        reqData.put("select","c_ques");
                        reqData.put("quesData",jsonStr);
                        WebData webData = new WebData(url,mHandler);
                        webData.setReqData(reqData);
                        webData.getData();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    //*****************
//                    Intent intent=new Intent(TeacherQuestion.this,Detail.class);
//                    Bundle bundle =new Bundle();
//                    bundle.putSerializable("Question",question);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
            });
        }
        //*****juiz*******get
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //What you want to do with these data? Just write here!
                super.handleMessage(msg);
                Bundle bundle= msg.getData();
                int select=msg.what;
                /*num 1: login*/
                String content = bundle.getString("content");//json

            }
        };
        //*****juiz*******
    }

    private void generateQuestion() {
        classname=ET_Class.getText().toString();
        title=ET_Question.getText().toString();
        std_ans=ET_Correct.getText().toString();
        A=ET_A.getText().toString();
        B=ET_B.getText().toString();
        C=ET_C.getText().toString();
        D=ET_D.getText().toString();
        ans.add(A);
        ans.add(B);
        ans.add(C);
        ans.add(D);
        question=new Question(classname,title,std_ans,ans);

    }

    private void findViews() {
        ET_Class=(EditText)findViewById(R.id.ET_Class);
        ET_Question=(EditText)findViewById(R.id.ET_Question);
        ET_A=(EditText)findViewById(R.id.ET_A);
        ET_B=(EditText)findViewById(R.id.ET_B);
        ET_C=(EditText)findViewById(R.id.ET_C);
        ET_D=(EditText)findViewById(R.id.ET_D);
        ET_Correct=(EditText)findViewById(R.id.ET_Correct);
        BT_send=(Button) findViewById(R.id.BT_send);
    }
}

package com.example.a303.teachlink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class StudentQuestion extends AppCompatActivity {
    private User user;
    private Button BT_AnswerA,BT_AnswerB,BT_AnswerC,BT_AnswerD;
    private String myanswer,correctanswer;
    private boolean AnswerAlready=false;
    //*****juiz*******
    private static Handler mHandler;
    //***************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_question);

        findViews();


        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");

        if (user !=null)
        {
            setListener();
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
                    // get JsonArray's value from JSON
                    Toast.makeText(StudentQuestion.this,content,Toast.LENGTH_LONG).show();
                    //Toast.makeText(Loging.this, content, Toast.LENGTH_SHORT).show();
                    //#code............................................................

                }
        };
            //*****juiz*******get
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int intScreenWidth = dm.widthPixels;
            int intScreenHeight = dm.heightPixels;

            int numberevenWidth= ((intScreenWidth)/3);
            int numberevenHeight=(intScreenHeight/3);
            myimageviewsize(BT_AnswerA,numberevenWidth,numberevenHeight);
            myimageviewsize(BT_AnswerB,numberevenWidth,numberevenHeight);
            myimageviewsize(BT_AnswerC,numberevenWidth,numberevenHeight);
            myimageviewsize(BT_AnswerD,numberevenWidth,numberevenHeight);
            BT_AnswerA.setTextSize((intScreenHeight)/12);
            BT_AnswerB.setTextSize((intScreenHeight)/12);
            BT_AnswerC.setTextSize((intScreenHeight)/12);
            BT_AnswerD.setTextSize((intScreenHeight)/12);
        }
    }


    private void myimageviewsize(Button loginimage, int numberevenWidth, int numberevenWidth1) {


        ViewGroup.LayoutParams params = loginimage.getLayoutParams();
        params.width = numberevenWidth;
        params.height = numberevenWidth1;
        loginimage.setLayoutParams(params);
    }
    private void setListener() {
        BT_AnswerA.setOnClickListener(listener);
        BT_AnswerB.setOnClickListener(listener);
        BT_AnswerC.setOnClickListener(listener);
        BT_AnswerD.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        Context context=StudentQuestion.this;
        @Override
        public void onClick(View v) {
            if (AnswerAlready==false){
                if (v.getId()==R.id.BT_AnswerA)
                {
                    myanswer="a";
                    AnswerAlready=true;

                }
                if (v.getId()==R.id.BT_AnswerB)
                {
                    myanswer="b";
                    AnswerAlready=true;

                }
                if (v.getId()==R.id.BT_AnswerC)
                {
                    myanswer="c";
                    AnswerAlready=true;

                }
                if (v.getId()==R.id.BT_AnswerD)
                {
                    myanswer="d";
                    AnswerAlready=true;

                }

                //********juiz*********send
                try {
                    URL url = new URL("http://192.168.1.170");
                    ArrayMap<String , String> reqData = new ArrayMap();
                    reqData.put("select","stu_ans");
                    reqData.put("stuAns",myanswer);
                    WebData webData = new WebData(url,mHandler);
                    webData.setReqData(reqData);
                    webData.getData();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                //*****************
            }
            else {
                Toast.makeText(StudentQuestion.this,R.string.Answered,Toast.LENGTH_SHORT).show();
            }

            Intent intent=new Intent(context,StudentMain.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("user",user);
            intent.putExtras(bundle);
            startActivity(intent);



        }
    };





    private void findViews() {
        BT_AnswerA=(Button)findViewById(R.id.BT_AnswerA);
        BT_AnswerB=(Button)findViewById(R.id.BT_AnswerB);
        BT_AnswerC=(Button)findViewById(R.id.BT_AnswerC);
        BT_AnswerD=(Button)findViewById(R.id.BT_AnswerD);
    }
}

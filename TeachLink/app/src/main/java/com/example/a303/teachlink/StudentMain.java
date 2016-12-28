package com.example.a303.teachlink;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentMain extends AppCompatActivity {
    private TextView TV_Student;
    private ImageButton IB_Rollcall,IB_Question,IB_Discuss,IB_Analyze,IB_History;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        findViews();

        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");
        if (user !=null)
        {
            TV_Student.setText(getString(R.string.Welcome)+"  "+user.getUsername());
            setListener();
        }


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int intScreenWidth = dm.widthPixels;
        int intScreenHeight = dm.heightPixels;

        int numberevenWidth= ((intScreenHeight)/4);
        int numberevenHeight=(intScreenHeight/5);



        myimageviewsize(IB_Rollcall,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Question,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Discuss,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Analyze,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_History,numberevenWidth,numberevenHeight);


    }

    private void myimageviewsize(ImageView loginimage, int numberevenWidth, int numberevenWidth1) {


        ViewGroup.LayoutParams params = loginimage.getLayoutParams();
        params.width = numberevenWidth;
        params.height = numberevenWidth1;
        loginimage.setLayoutParams(params);
    }

    private void setListener() {
        IB_Rollcall.setOnClickListener(listener);
        IB_Question.setOnClickListener(listener);
        IB_Discuss.setOnClickListener(listener);
        IB_Analyze.setOnClickListener(listener);
        IB_History.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        Context context=StudentMain.this;
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.IB_S_Rollcall)
            {
                Intent intent=new Intent(context,StudentRollcall.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_S_Question)
            {
                Intent intent=new Intent(context,StudentQuestionShow.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            if (v.getId()==R.id.IB_S_Discuss)
            {
                Intent intent=new Intent(context,StudentDiscuss.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_S_Analyze)
            {
                Intent intent=new Intent(context,StudentAnalyze.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_S_History)
            {
                Intent intent=new Intent(context,StudentHistory.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    };

    private void findViews() {
        TV_Student=(TextView)findViewById(R.id.TV_Student);
        IB_Rollcall=(ImageButton)findViewById(R.id.IB_S_Rollcall);
        IB_Question=(ImageButton)findViewById(R.id.IB_S_Question);
        IB_Discuss=(ImageButton)findViewById(R.id.IB_S_Discuss);
        IB_Analyze=(ImageButton)findViewById(R.id.IB_S_Analyze);
        IB_History=(ImageButton)findViewById(R.id.IB_S_History);
    }
}

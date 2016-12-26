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

public class TeacherMain extends AppCompatActivity {
    private TextView TV_Teacher;
    private ImageButton IB_Rollcall,IB_Question,IB_Push,IB_Discuss,IB_Analyze,IB_History;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        findViews();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int intScreenWidth = dm.widthPixels;
        int intScreenHeight = dm.heightPixels;

        int numberevenWidth= ((intScreenHeight)/4);
        int numberevenHeight=(intScreenHeight/4);

        myimageviewsize(IB_Rollcall,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Question,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Push,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Discuss,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_Analyze,numberevenWidth,numberevenHeight);
        myimageviewsize(IB_History,numberevenWidth,numberevenHeight);

        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");
        if (user !=null)
        {
            TV_Teacher.setText(getString(R.string.Welcome)+"  "+user.getUsername());
            setListener();
        }

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
        IB_Push.setOnClickListener(listener);
        IB_Discuss.setOnClickListener(listener);
        IB_Analyze.setOnClickListener(listener);
        IB_History.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        Context context=TeacherMain.this;
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.IB_T_Rollcall)
            {
                Intent intent=new Intent(context,TeacherRollcall.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_T_Question)
            {
                Intent intent=new Intent(context,TeacherQuestion.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_T_Push)
            {
                Intent intent=new Intent(context,TeacherPush.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_T_Discuss)
            {
                Intent intent=new Intent(context,TeacherDiscuss.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_T_Analyze)
            {
                Intent intent=new Intent(context,TeacherAnalyze.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (v.getId()==R.id.IB_T_History)
            {
                Intent intent=new Intent(context,TeacherHistoryShow.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    };

    private void findViews() {
        TV_Teacher=(TextView)findViewById(R.id.TV_Teacher);
        IB_Rollcall=(ImageButton)findViewById(R.id.IB_T_Rollcall);
        IB_Question=(ImageButton)findViewById(R.id.IB_T_Question);
        IB_Push=(ImageButton)findViewById(R.id.IB_T_Push);
        IB_Discuss=(ImageButton)findViewById(R.id.IB_T_Discuss);
        IB_Analyze=(ImageButton)findViewById(R.id.IB_T_Analyze);
        IB_History=(ImageButton)findViewById(R.id.IB_T_History);
    }
}

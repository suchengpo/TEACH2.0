package com.example.a303.teachlink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherRollcall extends AppCompatActivity {
    private EditText ET_Class,ET_Time;
    private Button BT_send;
    private User user;
    private String classes,time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_rollcall);

        findViews();
        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");
        setListener();
    }

    private void setListener() {
        BT_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classes=ET_Class.getText().toString();
                time=ET_Time.getText().toString();
                user.setChooseclass(classes);
                user.setChoosetime(time);

                if (true)//課名判斷
                {
                    if (true)//時間判斷
                    {
                        Intent intent=new Intent(TeacherRollcall.this,ScanQR.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("user",user);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(TeacherRollcall.this,R.string.ErrorMessage3,Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(TeacherRollcall.this,R.string.ErrorMessage2,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void findViews() {
        ET_Class=(EditText) findViewById(R.id.ET_Class);
        ET_Time=(EditText) findViewById(R.id.ET_Time);
        BT_send=(Button) findViewById(R.id.BT_send);
    }
}

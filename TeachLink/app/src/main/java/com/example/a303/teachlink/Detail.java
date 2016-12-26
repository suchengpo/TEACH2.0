package com.example.a303.teachlink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    private TextView TV_ClassName,TV_Accuracy,TV_A,TV_B,TV_C,TV_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();
        Bundle bundle=getIntent().getExtras();
        Question question=(Question) bundle.getSerializable("Question");

        if (question!=null)
        {
            TV_ClassName.setText(question.getTitle());
            TV_Accuracy.setText(Integer.toString(question.getRate()));
            TV_A.setText(question.getQuesAns().get(0));
            TV_B.setText(question.getQuesAns().get(1));
            TV_C.setText(question.getQuesAns().get(2));
            TV_D.setText(question.getQuesAns().get(3));
        }
    }

    private void findViews() {
        TV_ClassName=(TextView) findViewById(R.id.TV_ClassName);
        TV_Accuracy=(TextView) findViewById(R.id.TV_Accuracy);
        TV_A=(TextView) findViewById(R.id.TV_A);
        TV_B=(TextView) findViewById(R.id.TV_B);
        TV_C=(TextView) findViewById(R.id.TV_C);
        TV_D=(TextView) findViewById(R.id.TV_D);
    }
}

package com.example.a303.teachlink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TeacherHistoryShow extends AppCompatActivity {
    private RecyclerView rvClass;
    private User user;
    private List<Question> questionListList=new ArrayList<>();
    //*****juiz*******
    private static Handler mHandler;
    //***************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_history_show);

        //List<Classes> classesList=getClassList();
        rvClass=(RecyclerView) findViewById(R.id.RV_Class);
        rvClass.setLayoutManager(new LinearLayoutManager(this));
        rvClass.setAdapter(new ClassAdapter(this,questionListList));
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
                    questionListList.add(question);
                }

                Log.d("Data",content);


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



    public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHolder>{
        Context context;
        List<Question> questionListList;
        public ClassAdapter(Context context, List<Question> questionListList) {
            this.context=context;
            this.questionListList=questionListList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.item_view,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Question question =questionListList.get(position);
            holder.TV_ClassName.setText(question.getTitle());
            holder.TV_Accuracy.setText(Integer.toString(question.getRate()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,spot.getName(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,Detail.class);
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("Question",question);
//                    bundle.putInt("ImageID",spot.getImageId());
//                    bundle.putString("name",spot.getName());
//                    bundle.putString("phone",spot.getPhone());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return questionListList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView TV_ClassName,TV_Accuracy;
            public MyViewHolder(View itemView) {
                super(itemView);
                TV_ClassName=(TextView) itemView.findViewById(R.id.TV_ClassName);
                TV_Accuracy=(TextView) itemView.findViewById(R.id.TV_Accuracy);
            }
        }
    }


}

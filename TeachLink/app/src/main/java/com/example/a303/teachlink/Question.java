package com.example.a303.teachlink;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 303 on 2016/12/18.
 */

public class Question implements Serializable {
    private String classname,title,std_ans;
    private ArrayList<String> ans=new ArrayList<String>();
    private int rate;

    public Question(String classname, String title, String std_ans, ArrayList<String> quesAns) {
        this.classname = classname;
        this.title = title;
        this.std_ans = std_ans;
        this.ans = quesAns;
    }

    public Question(String classname, String title, ArrayList<String> ans) {
        this.classname = classname;
        this.title = title;
        this.ans = ans;
    }

    public Question(String title, ArrayList<String> ans, int rate) {
        this.title = title;
        this.ans = ans;
        this.rate = rate;
    }

    public String getClassname() {
        return classname;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getQuesAns() {
        return ans;
    }

    public int getRate() {
        return rate;
    }
}

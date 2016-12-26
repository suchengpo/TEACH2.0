package com.example.a303.teachlink;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 303 on 2016/12/2.
 */
//每一門課
public class Classes implements Serializable {
    private ArrayList<User> userList=new ArrayList<User>();
    private RollCall classrollcall;
    private String classname;
    private int accuracy;

    public Classes(String classname, int accuracy) {
        this.classname = classname;
        this.accuracy = accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}

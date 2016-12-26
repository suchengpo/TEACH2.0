package com.example.a303.teachlink;

import java.util.ArrayList;

/**
 * Created by 303 on 2016/12/26.
 */

public class RollcallData {
    private ArrayList<String> stuname=new ArrayList<>();
    private String classname;

    public RollcallData(ArrayList<String> stuname, String classname) {
        this.stuname = stuname;
        this.classname = classname;
    }
}

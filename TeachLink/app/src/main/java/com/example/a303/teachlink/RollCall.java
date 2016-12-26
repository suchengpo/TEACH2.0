package com.example.a303.teachlink;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 303 on 2016/12/17.
 */

public class RollCall implements Serializable {
    private String date,name,key;
    private Map<String, String> dictionary = new HashMap<String, String>();


    public void downlaodData(){
        dictionary.put("key", "True");
    }

    public void checkRollcall(String check_date, String check_name){
        String check_key;
        check_key=check_name+check_date;
        String result = dictionary.get(check_key);
    }
}

package com.example.a303.teachlink;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 303 on 2016/11/28.
 */
public class User implements Serializable {
    private String username,password;
    private int identity;//0:身分錯誤，1:教師，2:學生
    private ArrayList<Classes> classList=new ArrayList<Classes>();//修或教的所有課

    private String chooseclass,choosetime;

    public User(String username, String password, int identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
        updateClasses();
    }

    private void updateClasses() {//給Username抓class名回來
        classList.add(0,new Classes("math",5));
        classList.add(1,new Classes("English",5));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getIdentity() {
        return identity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public ArrayList<Classes> getClassList() {return classList;}

    public void setClassList(ArrayList<Classes> classList) {this.classList = classList;}

    public String getChooseclass() {
        return chooseclass;
    }

    public String getChoosetime() {
        return choosetime;
    }

    public void setChooseclass(String chooseclass) {
        this.chooseclass = chooseclass;
    }

    public void setChoosetime(String choosetime) {
        this.choosetime = choosetime;
    }
}

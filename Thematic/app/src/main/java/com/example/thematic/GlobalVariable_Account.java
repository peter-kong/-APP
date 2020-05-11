package com.example.thematic;
import  android.app.Application;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GlobalVariable_Account extends Application{
    private String 帳號;
    private ArrayList HistoryDate;
    private ArrayList Name;
    private ArrayList Uid;
    private ArrayList Finish;
    private boolean t;
    //帳號
    public void setAccount(String input){
        帳號 = input;
    }

    public String returnAcc(){
        return 帳號;
    }

    //歷史日期
    public void setDate(ArrayList str) {HistoryDate = str; }

    public ArrayList returnDate() {return HistoryDate; }

    //工作報表 名字
    public void setName(ArrayList str) {Name = str; }

    public ArrayList returnName() {return Name; }

    //工作報表 UID
    public void setUID(ArrayList str) {Uid = str; }

    public ArrayList returnUID() {return Uid; }

    public  void println(){

        if(Uid.size() != 0)
            for(int i = 0;i < Uid.size();i++){
                Log.e("db",Uid.get(i).toString());
            }
        else
            Log.e("Empty","E");
    }

    public void  setFinish(ArrayList str){Finish = str;}
    public ArrayList returnFinish(){return Finish;}

    public void setTommorrowoToday(boolean number){t = number;}
    public boolean returnTommorrowToday(){return t;}

}

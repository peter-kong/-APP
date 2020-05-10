package com.example.thematic;
import  android.app.Application;
import android.util.Log;

import java.util.ArrayList;

public class GlobalVariable_Account extends Application{
    private String 帳號;
    private ArrayList HistoryDate;
    private ArrayList Name;
    private ArrayList Uid;

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

        for(int i = 0;i < Name.size();i++){
            Log.e("db",Name.get(i).toString());
        }

    }

}

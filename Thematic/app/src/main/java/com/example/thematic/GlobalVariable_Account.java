package com.example.thematic;
import  android.app.Application;

import java.util.ArrayList;

public class GlobalVariable_Account extends Application{
    private String 帳號;
    private ArrayList HistoryDate;

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

}

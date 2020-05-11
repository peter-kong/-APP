package com.example.thematic;
import  android.app.Application;

import java.util.ArrayList;

public class GlobalVariable_Account extends Application{
    private String 帳號,人力配置日期;
    private ArrayList HistoryDate,Scheduletime;      //歷史日期,照服員管理的工作時間
    private int Month; //月概況鎖進入的月份
    private ArrayList 星期數; //月概況spinner紀錄有幾週
    private String caregiverID,ScheduleDate; //Schedule的caregiverID,Schedule的日期

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

    //照服員管理
    public void setScheduletime(ArrayList str, String ID,String Date) {Scheduletime = str ; caregiverID = ID; ScheduleDate = Date;}

    public ArrayList returnScheduletime() { return Scheduletime; }

    public String returncaregiverID() {return caregiverID; }

    public String returnScheduleDate() {return ScheduleDate; }

    //人力配置
    public void setPeopleUseRead(String Date) {人力配置日期 = Date;}

    public String returnPeopleUseRead(){return 人力配置日期; }


    //月概況的月份

    public void setMonth(int month) {Month = month; }

    public int returnMonth(){return Month;}

    public void setweek_count(ArrayList 星期List) {星期數 = 星期List; }

    public ArrayList returnWeekCount(){return 星期數;}

}

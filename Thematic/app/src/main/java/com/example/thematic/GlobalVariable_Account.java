package com.example.thematic;
import  android.app.Application;

public class GlobalVariable_Account extends Application{
    private String 帳號;

    public void setAccount(String input){
        帳號 = input;
    }

    public String returnAcc(){
        return 帳號;
    }

}

package com.example.mysql_connect;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.thematic.GlobalVariable_Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLCon {

    //final String[] user_data = getResources().getStringArray(R.array.user_data);
    // 資料庫定義
    String mysql_ip = "192.168.0.180";
    //String mysql_ip = "134.208.41.237";
    int mysql_port = 3306; // Port 預設為 3306
    int check_bits = 0;
    String db_name = "longcare";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String db_user = "LongCareUser";
    String db_password = "12345";
    String sql = "";

    int counter = 0;
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.e("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public String getData(String 帳號, String 需求) {
        String 關聯表名稱="",屬性="";
        int CID = Integer.parseInt(帳號);
//========以下為Personal_data的內容=========
        if(需求.equals("account_get")){
            關聯表名稱 = "user";
            屬性 = "UAccount";
        }
        else if(需求.equals("name_get")){
            關聯表名稱 = "user";
            屬性 = "UName";
        }
        //gender 還沒好
        /*
        else if(需求.equals("gender_get")){
            關聯表名稱 = "user";
            屬性 = "Ugender";
        }*/
        else if(需求.equals("birthday_get")){
            關聯表名稱 = "user";
            屬性 = "UBirth";
        }
        else if(需求.equals("level_get")){
            關聯表名稱 = "user";
            屬性 = "ULevel";
        }
        else if(需求.equals("connectnum_get")){
            關聯表名稱 = "user";
            屬性 = "UPhone";
        }
        else if(需求.equals("email_get")){
            關聯表名稱 = "user";
            屬性 = "UMail";
        }
        else if(需求.equals("address_get")){
            關聯表名稱 = "user";
            屬性 = "UAddress";
        }
        else if(需求.equals("healthsitu")){
            關聯表名稱 = "user";
            屬性 = "UMedHistory";
        }
        else if(需求.equals("personid_get")){
            關聯表名稱 = "user";
            屬性 = "UIDNumber";
        }
        else if(需求.equals("我要manager密碼")){  //帳號登入找manager
            關聯表名稱 = "manager";
            屬性 = "MPassword";
        }
        else if(需求.equals("我要user密碼")){    //帳號登入找user
            關聯表名稱 = "user";
            屬性 = "UPassword";
        }
        else if(需求.equals("我要caregiver密碼")){    //帳號登入找caregiver
            關聯表名稱 = "caregiver";
            屬性 = "CPassword";
        }
        else if(需求.equals("我要caregiver名字")){
            關聯表名稱 = "caregiver";
            屬性 = "CName";
        }
        String data = "";
        try {
            //Log.v("DB","Test:"+關聯表名稱+屬性);
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            //在關聯表裡面找到帳號
            if(關聯表名稱 == "user") {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UAccount` = " + "\"" + 帳號 + "\"";
            }
            else if(關聯表名稱 == "manager"){
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `MAccount` = " + "\"" + 帳號 + "\"";
            }
            else if(關聯表名稱 == "caregiver"){
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `CID` = " + "\"" + CID + "\"";
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            counter = 0;
            while (rs.next())
            {
                counter = 1;
                String id = rs.getString(屬性);
                data = id;
                Log.e("144",""+data);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(counter == 0)
            data = "There are no data in the database";

        return data;
    }
    //寫入註冊的資料
    public int  insertRegisterData(String UName,String UAccount,String UPassword,
                           String UIDNumber,String UAddress,String Uphone,
                           String UEmail,String UMedHistory,String ULevel,
                           String UBirth) {

        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "INSERT INTO `user` (`UName`,`UAccount`,`UPassword`,`UIDNumber`,`UAddress`,`UPhone`,`UMail`,`UMedHistory`,`ULevel`,`UBirth`)VALUES ('" + UName + "','" + UAccount + "','" + UPassword + "','" + UIDNumber + "','" + UAddress + "','" + Uphone + "','" + UEmail + "','" + UMedHistory + "','" + ULevel + "','" + UBirth + "')";

            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            Log.v("DB", "寫入資料完成：");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
            return 0;
        }
    }

    public ArrayList getschedule(String Date, String 需求,String user帳號){
            ArrayList data = new ArrayList();
        try{
            String 關聯表名稱 = "schedule";
            Log.e("173",user帳號);
            Connection con = DriverManager.getConnection(url, db_user, db_password);

            String sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UID` = "+ "\"" + user帳號 + "\"";
            Log.e("line 175",sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                int int_date = Integer.parseInt(Date);
                String nextDate = rs.getString("Date");
                int db_date = Integer.parseInt(nextDate);
                while (int_date < db_date) {
                    if (int_date < db_date) {

                        String firsttime = rs.getString("FirstTime");
                        String lasttime = rs.getString("LastTime");
                        int CID = rs.getInt("CID");
                        String input_CID = String.valueOf(CID);
                        Log.e("CID",""+CID);
                        String caregiver = getData(input_CID, "我要caregiver名字");
                        String sql1 = "SELECT * FROM `longcare`.`usertime` WHERE `Date` = \"" + nextDate + "\" ORDER BY `UID` = \"" + user帳號 + "\"";
                        Statement st1 = con.createStatement();
                        ResultSet rs1 = st1.executeQuery(sql1);
                        String request = "";
                        while (rs1.next()) {
                            request = rs1.getString("Request");
                        }

                        data.add(firsttime);
                        data.add(lasttime);
                        data.add(caregiver);
                        data.add(request);
                        data.add(nextDate);
                        break;
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            Log.e("DB","獲取schedule資料失敗");
            Log.e("DB",e.toString());
        }
        return data;
    }
}

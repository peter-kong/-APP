package com.example.mysql_connect;

import android.text.format.Time;
import android.util.Log;

import com.example.thematic.GlobalVariable_Account;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class MySQLCon {

    //final String[] user_data = getResources().getStringArray(R.array.user_data);
    // 資料庫定義
    //String mysql_ip = "192.168.0.180";
    String mysql_ip = "134.208.41.237";
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
    /*
    public ArrayList getusername(String ID,String 需求)
    {
        String 關聯表名稱="",屬性="";

        if (需求.equals("want_names"))
        {
            關聯表名稱 = "user";
            屬性 = "UName";
        }

        ArrayList name = new ArrayList();

        try
        {
            Connection con = DriverManager.getConnection(url, db_user, db_password);

            if(關聯表名稱 == "user")
        }
    }*/

    public ArrayList gotoschedule(String 日期, String 需求)
    {
        String 關聯表名稱 = "",屬性 = "";

        //要個案ID
        if(需求.equals("want_objects"))
        {
            關聯表名稱 =  "schedule";
            屬性 = "UID";
        }

        ArrayList name = new ArrayList();

        try
        {
            Connection con = DriverManager.getConnection(url, db_user, db_password);

            //在關聯表中將當日的UID取出
            if(關聯表名稱 == "schedule")
            {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `Date` = " + "\"" + 日期 + "\"";
            }


            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList uids = new ArrayList();

            //將取出來的關聯表放入ArrayList中
            while(rs.next())
            {
                String X = rs.getString(屬性);
                uids.add(X);
            }
            //篩選有沒有重複的
            HashSet different = new HashSet(uids);
            ArrayList cleanid = new ArrayList(different);

            //將id轉成名字
            for(int i = 0;i<cleanid.size();i++)
            {
                sql = "SELECT * FROM `" +"longcare`.`"+"user" + "` WHERE `UID` = " + "\"" + cleanid.get(i) + "\"";
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(sql);
                while(rs1.next())
                {
                    name.add(rs1.getString("UName"));
                }
            }

            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return name;
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
        else if(需求.equals("我要caregiver名字")){    //取得caregiver名字
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
    //獲得schedule內容放入最近照服時間
    public ArrayList getschedule(String Date, String 需求,String user帳號){
        ArrayList data = new ArrayList();
        int int_date = Integer.parseInt(Date);  //目前的日期
        int db_date;
        String need_date = "";
        try{


            String 關聯表名稱 = "schedule";
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UID` = "+ "\"" + user帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                need_date = rs.getString("Date"); //想要找的日期
                db_date = Integer.parseInt(need_date);
                //取得時間
                if (需求.equals("我要下次工作內容")){
                    while (int_date > db_date) {
                        if (int_date <= db_date) {   //第一個超過目前日期的日期
                            GetUsertimeData(data,rs,need_date,user帳號);
                            break;
                        }
                    }
                }
                else if(需求.equals("我要上次工作內容")){
                    String check_date = "";
                    while(rs.next()){
                        check_date = rs.getString("Date"); //想要找的日期
                        db_date = Integer.parseInt(check_date);
                        if(db_date < int_date){
                            need_date = check_date;
                        }
                        else if(db_date > int_date){
                            GetUsertimeData(data, rs, need_date,user帳號);
                            break;
                        }
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
    //獲得usertime中的資料(包含起終時間、照服員名稱、服務內容)
    public ArrayList GetUsertimeData(ArrayList data, ResultSet rs,String 日期, String user帳號){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String firsttime = rs.getString("FirstTime");
            String lasttime = rs.getString("LastTime");
            int CID = rs.getInt("CID");
            String input_CID = String.valueOf(CID);
            String caregiver = getData(input_CID, "我要caregiver名字");
            String sql1 = "SELECT * FROM `longcare`.`usertime` WHERE `Date` = \"" + 日期 + "\" ORDER BY `UID` = \"" + user帳號 + "\"";
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
            data.add(日期);
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e("DB","獲取usertime資料失敗");
            Log.e("DB",e.toString());
        }
        return data;
    }
    //獲取schedule的日期
    public ArrayList GetDate(String Date, String user帳號){
        ArrayList data = new ArrayList();
        int IntDate = Integer.parseInt(Date);
        try{
            //藉由帳號取得對應的UID
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `user` WHERE `UAccount` = "+ "\"" + user帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String id = rs.getString("UID");
            //使用UID進schedule取得對應的所有資料
            String sql1 = "SELECT * FROM `schedule` WHERE `UID` = "+ "\"" + id + "\"";
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            while(rs1.next()){
                String DbDate = rs1.getString("Date");
                int CheckDate = Integer.parseInt(DbDate);
                if(CheckDate < IntDate){
                    data.add(DbDate);
                }

            }
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e("DB","獲取日期資料失敗");
            Log.e("DB",e.toString());
        }
        return data;
    }

    public ArrayList getUserUID(String 照服員帳號){

        ArrayList UID = new ArrayList();
        UID.add("No data");

        //藉由帳號取得對應的CID
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `caregiver` WHERE `CAccount` = " + "\"" + 照服員帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            rs.next();
            String cid = rs.getString("CID");

            String sql2 = "SELECT * FROM `schedule` WHERE `CID` = " + "\"" + cid + "\"";
            Statement st2 = con.createStatement();
            ResultSet re2 = st2.executeQuery(sql2);

            SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
            Date date = new Date();
            String strDate = sdFormat.format(date);

            //Log.e("tag",strDate);

            int flag = 0;
            while(re2.next()){
                if(re2.getString("Date").equals(strDate)){
                    if(UID.size() == 1 && flag == 0) {
                        UID.set(0, re2.getString("UID"));
                        flag = 1;
                    }else {
                        UID.add(re2.getString("UID"));
                      //  Log.e("Yes",re2.getString("UID"));
                    }
                }else{
                   // Log.e("Not time:",re2.getString("Date"));
                }
            }

        }catch(SQLException e){
            Log.e("DB","獲取UID失敗");
        }

       // Log.e("DBtest",UID.get(0).toString());

        return UID;
    }

    public ArrayList getName(ArrayList UID){

        ArrayList name = new ArrayList();
        name.add("no data");

        try {

            Connection con = DriverManager.getConnection(url, db_user, db_password);

            int flag = 0;
            for (int i = 0; i < UID.size(); i++) {

                String sql = "SELECT * FROM `user` WHERE `UID` = " + "\"" + UID.get(i) + "\"";
                //Log.e("Ins: ",sql);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                rs.next();
                if(name.size() == 1 && flag == 0){
                    name.set(0,rs.getString("UName"));
                    flag = 1;
                }else{
                    name.add(rs.getString("UName"));
                }

            }
        }catch(SQLException e){
            Log.e("Name","姓名取得失敗");
        }

        return name;
    }

    public ArrayList getcaregiverworkcontent(String 照服員帳號, String UID){

        ArrayList content = new ArrayList();
        content.add("no data");

        try{
           // Log.e("照服員帳號",照服員帳號);
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql =  "SELECT * FROM `caregiver` WHERE `CAccount` = " + "\"" + 照服員帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            //Log.e("sql: ",sql);

            //第一照服員名字
            rs.next();
            content.set(0,rs.getString("CName"));
            String cid = rs.getString("CID");

            SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
            Date date = new Date();
            String strDate = sdFormat.format(date);

            //對照CID UID DATE
            String sql2 =  "SELECT * FROM `schedule` WHERE `CID` = " + "\"" + cid + "\"";
            ResultSet rs2 = st.executeQuery(sql2);

            while(rs2.next()){
               // Log.e("rs2:",rs2.getString("CID").toString() + rs2.getString("UID") + rs2.getString("Date"));
                //Log.e("target:",cid+UID+strDate);
                if(cid.equals(rs2.getString("CID")) && UID.equals(rs2.getString("UID")) && strDate.equals(rs2.getString("Date"))){
                    content.add(rs2.getString("FirstTime"));
                    content.add(rs2.getString("LastTime"));
                   // Log.e("Time:",content.get(1).toString() + " - " + content.get(2).toString());
                    break;
                }
            }
/*
            String sql3 =  "SELECT * FROM `usertime` WHERE `Request` = " + "\"" + cid + "\"";
            Statement st = con.createStatement();
            ResultSet rs3 = st.executeQuery(sql3);
*/
        }catch(SQLException e){

        }

        return content;//照服員名字,開始時間,結束時間
    }
}


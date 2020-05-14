
package com.example.mysql_connect;

import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class MySQLCon {

    //final String[] user_data = getResources().getStringArray(R.array.user_data);
    // 資料庫定義
    //String mysql_ip = "192.168.0.180";
    String mysql_ip = "172.20.10.5";
    //String mysql_ip = "134.208.41.237";
    //String mysql_ip = "192.168.1.124";
    int mysql_port = 3306; // Port 預設為 3306
    int check_bits = 0;
    String db_name = "longcare";
    String url = "jdbc:mysql://" + mysql_ip + ":" + mysql_port + "/" + db_name;
    String db_user = "LongCareUser";
    String db_password = "12345";
    String sql = "";

    int counter = 0;

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB", "加載驅動成功");
        } catch (ClassNotFoundException e) {
            Log.e("DB", "加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            Log.e("DB", "遠端連接成功");
        } catch (SQLException e) {
            Log.e("DB", "遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public ArrayList gotoschedule(String 日期, String 需求) {
        String 關聯表名稱 = "", 屬性 = "";

        //要個案ID
        if (需求.equals("want_objects")) {
            關聯表名稱 = "schedule";
            屬性 = "UID";
        }

        ArrayList name = new ArrayList();

        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);

            //在關聯表中將當日的UID取出
            if (關聯表名稱 == "schedule") {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `Date` = " + "\"" + 日期 + "\"";
            }


            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList uids = new ArrayList();

            //將取出來的關聯表放入ArrayList中
            while (rs.next()) {
                String X = rs.getString(屬性);
                uids.add(X);
            }
            //篩選有沒有重複的
            HashSet different = new HashSet(uids);
            ArrayList cleanid = new ArrayList(different);

            //將id轉成名字
            for (int i = 0; i < cleanid.size(); i++) {
                sql = "SELECT * FROM `" + "longcare`.`" + "user" + "` WHERE `UID` = " + "\"" + cleanid.get(i) + "\"";
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(sql);
                while (rs1.next()) {
                    name.add(rs1.getString("UName"));
                }
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public String getData(String 帳號, String 需求) {
        String 關聯表名稱 = "", 屬性 = "",Date = "";
        int CID = 0;
//========以下為Personal_data的內容=========
        if (需求.equals("account_get")) {
            關聯表名稱 = "user";
            屬性 = "UAccount";
        } else if (需求.equals("name_get")) {
            關聯表名稱 = "user";
            屬性 = "UName";
        }
        else if(需求.equals("gender_get")){
            關聯表名稱 = "user";
            屬性 = "Ugender";
        }
        else if (需求.equals("birthday_get")) {
            關聯表名稱 = "user";
            屬性 = "UBirth";
        } else if (需求.equals("level_get")) {
            關聯表名稱 = "user";
            屬性 = "ULevel";
        } else if (需求.equals("connectnum_get")) {
            關聯表名稱 = "user";
            屬性 = "UPhone";
        } else if (需求.equals("email_get")) {
            關聯表名稱 = "user";
            屬性 = "UMail";
        } else if (需求.equals("address_get")) {
            關聯表名稱 = "user";
            屬性 = "UAddress";
        } else if (需求.equals("healthsitu")) {
            關聯表名稱 = "user";
            屬性 = "UMedHistory";
        } else if (需求.equals("personid_get")) {
            關聯表名稱 = "user";
            屬性 = "UID";
        } else if (需求.equals("我要manager密碼")) {  //帳號登入找manager
            關聯表名稱 = "manager";
            屬性 = "MPassword";
        } else if (需求.equals("我要user密碼")) {    //帳號登入找user
            關聯表名稱 = "user";
            屬性 = "UPassword";
        } else if (需求.equals("我要caregiver密碼")) {
            關聯表名稱 = "caregiver";
            屬性 = "CPassword";
        } else if (需求.equals("我要caregiver名字")) {    //取得caregiver名字
            CID = Integer.parseInt(帳號);
            關聯表名稱 = "caregiver";
            屬性 = "CName";
        }
        else if(需求.equals("我要schedule當日所有的UID")){
            關聯表名稱 = "schedule";
            屬性 = "UID";
            Date = 帳號;
        }
        String data = "";
        try {
            //Log.v("DB","Test:"+關聯表名稱+屬性);
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            //在關聯表裡面找到帳號
            if (關聯表名稱 == "user") {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UAccount` = " + "\"" + 帳號 + "\"";
            } else if (關聯表名稱 == "manager") {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `MAccount` = " + "\"" + 帳號 + "\"";
            } else if (關聯表名稱 == "caregiver") {
                if(CID == 0){
                    Log.e("123","123");
                    sql ="SELECT * FROM `" + 關聯表名稱 + "` WHERE `CAccount` = " + "\"" + 帳號 + "\"";
                }
                else {
                    Log.e("456","456");
                    sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `CID` = " + "\"" + CID + "\"";
                }
            } else if(關聯表名稱 == "schedule"){
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `Date` = " + "\"" + Date + "\"";
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            counter = 0;
            while (rs.next()) {
                counter = 1;
                String id = rs.getString(屬性);
                data = id;
                Log.e("144", "" + data);
            }
            st.close();
        } catch (SQLException e) {
            Log.e("Here","Line 187");
            e.printStackTrace();

        }
        if (counter == 0)
            data = "no data";

        Log.e("Line 194",data);

        return data;
    }

    //寫入註冊的資料
    public int insertRegisterData(String UName, String UAccount, String UPassword,
                                  String UID, String UAddress, String Uphone,
                                  String UEmail, String UMedHistory, String ULevel,
                                  String UBirth,String UGender){

        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "INSERT INTO `user` (`UName`,`UAccount`,`UPassword`,`UID`,`UAddress`,`UPhone`,`UMail`,`UMedHistory`,`ULevel`,`UBirth`,`UGender`)VALUES ('" + UName + "','" + UAccount + "','" + UPassword + "','" + UID + "','" + UAddress + "','" + Uphone + "','" + UEmail + "','" + UMedHistory + "','" + ULevel + "','" + UBirth + "','" + UGender + "')";
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

    //傳送報表
    public void SendFinishandnotice(String 使用者ID,ArrayList Finish,String meg,String time){

        //資料前處理
        String a  = "";
        for(int i = 0;i < Finish.size();i++){

            //Log.e("項目: ",Finish.get(i).toString());

            if(i != 0)
                a = a.concat("、" + Finish.get(i).toString());
            else
                a = a.concat(Finish.get(i).toString());

        }

        Log.e("Finish0",Finish.get(0).toString());
        Log.e("Finish1",Finish.get(1).toString());
        Log.e("Finish2",Finish.get(2).toString());
        Log.e("a:",a);

        //在 schedule 資料表內找出 UID 的資料，並將 Finish 欄位內的資料修改為 a.toString
        String sql = "UPDATE `schedule` SET `Finish` = '" + a + "' WHERE `UID` = '"+ 使用者ID + "' AND  `Date` = '" + time + "'";
        String sql2 = "UPDATE `schedule` SET `備註` = '" + meg + "' WHERE `UID` = '" + 使用者ID + "' AND  `Date` = '" + time + "'";

        Log.e("sql1",sql);
        Log.e("sql2",sql2);

        try {

            Connection con = DriverManager.getConnection(url, db_user, db_password);

            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
            st.close();
            //



        }catch(SQLException e){
            Log.e("SendFinishandnotice","傳送報表失敗");
        }



    }

    //獲得schedule內容放入最近照服時間
    public String get_ID(String 帳號,String 需求){
        String data = new String();
        String 關聯表名稱 = "",屬性 = "";

        try{
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            if(需求.equals("我要caregiverID")){
                關聯表名稱 = "caregiver";
                屬性 = "CID";
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `CAccount` = " + "\"" + 帳號 + "\"";
            }
            if(需求.equals("我要userID")){
                關聯表名稱 = "user";
                屬性 = "UID";
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UAccount` = " + "\"" + 帳號 + "\"";
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String id = rs.getString(屬性);
            data = id;
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e("DB", "獲取ID失敗");
            Log.e("DB", e.toString());
        }
        return data;
    }
    public ArrayList getschedule(String Date, String 需求, String 帳號) {
        ArrayList data = new ArrayList();
        int int_date = Integer.parseInt(Date), db_date;  //目前的日期,db選擇的日期
        String sql = new String(), need_date = new String();
        try {
            String 關聯表名稱 = "schedule";
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            if (需求.equals("我要caregiver工作時間")) {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `CID` = " + "\"" + 帳號 + "\"";
            } else {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UID` = " + "\"" + 帳號 + "\"";
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String answer_date = "0000";
            while (rs.next()) {
                Log.e("getschedule", "進入rs");
                need_date = rs.getString("Date"); //想要找的日期
                db_date = Integer.parseInt(need_date);
                Log.e("input_date,db_date", int_date + "," + db_date);
                //取得時間
                if (需求.equals("我要下次工作內容")) {
                    if (int_date <= db_date) {//第一個超過目前日期的日期
                        if(answer_date.equals("0000")){
                            answer_date =  need_date;
                        }
                        else if(Integer.parseInt(answer_date) > db_date){
                            answer_date = need_date;
                        }
                    }
                } else if (需求.equals("我要這次工作內容")) {
                    String check_date = "";
                    check_date = rs.getString("Date"); //想要找的日期
                    Log.e("檢查的日期", check_date);
                    db_date = Integer.parseInt(check_date);
                    Log.e("db_date,int_date,Integer.parseInt(answer_date)", db_date + "," +
                            int_date + "," + Integer.parseInt((answer_date)));
                    if (db_date == int_date) {
                            answer_date = check_date;
                            break;

                    }
                } else if (需求.equals("我要caregiver工作時間")) {
                    if (db_date == int_date) {
                        String firsttime = rs.getString("FirstTime");
                        String lasttime = rs.getString("LastTime");
                        data.add(firsttime + "~" + lasttime);
                    }
                } else if (需求.equals("我要排程工作內容")) {
                    if (rs.getString("Date").equals(Date)) {
                        data.add(rs.getString("備註"));
                        data.add(rs.getString("Finish"));
                        data.add(rs.getString("FirstTime"));
                        data.add(rs.getString("LastTime"));
                        String caregiverID = "" + rs.getString("CID");
                        data.add(getData(caregiverID, "我要caregiver名字"));
                        String request = "" + getrequest(Date, rs.getString("FirstTime"), 帳號);
                        data.add(request);
                        Log.e("OK", "資料讀取完成");
                        //break;
                    }
                } else if (需求.equals("我要歷史工作內容")) {
                    if (rs.getString("Date").equals(Date)) {
                        data.add(rs.getString("FirstTime"));
                        data.add(rs.getString("LastTime"));
                        String caregiverID = "" + rs.getString("CID");
                        data.add(getData(caregiverID, "我要caregiver名字"));
                        String request = "" + getrequest(Date, rs.getString("FirstTime"), 帳號);
                        data.add(request);
                    }
                } else if (需求.equals("我要歷史工作報表內容")) {
                    data.add(rs.getString("備註"));
                    data.add(rs.getString("Finish"));
                    data.add(rs.getString("FirstTime"));
                    data.add(rs.getString("LastTime"));
                    String caregiverID = get_ID(帳號, "我要caregiverID");
                    data.add(getData(caregiverID, "我要caregiver名字"));
                    String request = "" + getrequest(Date, rs.getString("FirstTime"), 帳號);
                    data.add(request);
                    Log.e("OK", "歷史工作報表內容獲取完成");
                }
                Log.e("rs->finish", "這一筆結束");
            }
            if(需求.equals("我要這次工作內容")){
                if(answer_date.equals("0000")){
                    Log.e("上次工作內容找不到","無資料");
                }
                else {
                    Log.e("answer_date after search", answer_date);
                    rs = st.executeQuery(sql);
                    rs.next();
                    GetUsertimeData(data, rs, answer_date, 帳號, "我要工作內容");
                }
            }
            else if(需求.equals("我要下次工作內容")){
                if(answer_date.equals("0000")){
                    Log.e("下次工作內容找不到","無資料");
                }
                else {
                    Log.e("answer_date after search", answer_date);
                    rs = st.executeQuery(sql);
                    rs.next();
                    GetUsertimeData(data, rs, answer_date, 帳號, "我要工作內容");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取schedule資料失敗");
            Log.e("DB", e.toString());
        }
        return data;
    }

    public String getrequest(String Date,String firsttime,String 帳號){
        String data = new String();

        try{
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `usertime` WHERE `UID` = " + "\"" + 帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(Date.equals(rs.getString("Date")) && firsttime.equals(rs.getString("Firsttime"))){
                    data = rs.getString("request");
                    break;
                }
            }
        }
        catch(SQLException e){

        }
        return data;
    }
    //獲得usertime中的資料(包含起終時間、照服員名稱、服務內容)
    public ArrayList GetUsertimeData(ArrayList data, ResultSet rs, String 日期, String user帳號, String 需求) {
        String firsttime = new String(), lasttime = new String(), input_CID = new String(), caregiver = new String();
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            firsttime = rs.getString("FirstTime");
            Log.e("OK","獲取usertime資料成功");
            lasttime = rs.getString("LastTime");
            Log.e("OK","獲取usertime資料成功");
            int CID = rs.getInt("CID");
            input_CID = String.valueOf(CID);
            Log.e("OK","獲取usertime資料成功");
            caregiver = getData(input_CID, "我要caregiver名字");
            Log.e("OK","獲取usertime資料成功");
            String sql1 = "SELECT * FROM `longcare`.`usertime` WHERE `Date` = \"" + 日期 + "\" ORDER BY `UID` = \"" + user帳號 + "\"";
            Log.e("OK","獲取usertime資料成功");
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            String request = "";
            while (rs1.next()) {
                request = rs1.getString("Request");
            }
            Log.e("OK","獲取usertime資料成功");
            data.add(firsttime);
            data.add(lasttime);
            data.add(caregiver);
            data.add(request);
            data.add(日期);
            Log.e("OK", "data獲取成功");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取usertime資料失敗");
            Log.e("DB", e.toString());
        }
        return data;
    }

    //獲取schedule的日期
    public ArrayList GetDate(String Date, String user帳號,String 需求) {
        ArrayList data = new ArrayList();
        int IntDate = Integer.parseInt(Date);
        try {
            //藉由帳號取得對應的UID
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `user` WHERE `UAccount` = " + "\"" + user帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String id = rs.getString("UID");
            Log.e("尋找ID成功",id);
            //使用UID進schedule取得對應的所有資料
            String sql1 = "SELECT * FROM `schedule` WHERE `UID` = " + "\"" + id + "\"";
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            while (rs1.next()) {
                String DbDate = rs1.getString("Date");
                int CheckDate = Integer.parseInt(DbDate);
                if(需求.equals("我要本月日期")){
                    if((CheckDate/100) == Integer.parseInt(Date)/100){
                        data.add(DbDate);
                    }
                }
                else {
                    Log.e("檢查的日期,現在的日期", DbDate + "," + IntDate);
                    if (CheckDate < IntDate) {
                        data.add(DbDate);
                    }
                }
            }

            int[] data1 = new int[data.size()];
            for(int i= 0 ;i<data.size();i++){
                String a = ""+data.get(i);
                int d = Integer.parseInt(a);
                data1[i]=d;
            }
            for(int i=0;i<data.size();i++){
                for(int j=0;j<data.size();j++){
                    if(data1[i]<data1[j]){
                        int c = data1[i];
                        data1[i]=data1[j];
                        data1[j]=c;
                    }
                }
            }
            for (int i = 0; i < data.size();i++){
                int m = data1[i]/100;
                int d = data1[i]%100;
                if(m<10){
                    if(d<10){
                        data.set(i,"0"+m+"0"+d);
                    }
                    else{
                        data.set(i,"0"+m+d);
                    }
                }
                else{
                    if(d<10){
                        data.set(i,m+"0"+d);
                    }
                    else{
                        data.set(i,m+d);
                    }
                }
            }
            Log.e("資料獲取成功",data.size()+"");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取日期資料失敗");
            Log.e("DB", e.toString());
        }
        return data;
    }

    public String getAccount(String ID, String 需求) {
        String 關聯表名稱 = "", 屬性 = "", data = "";
        if (需求.equals("我要user帳號")) {
            關聯表名稱 = "user";
            屬性 = "UAccount";
        }

        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            if (關聯表名稱.equals("user")) {
                sql = "SELECT * FROM `" + 關聯表名稱 + "` WHERE `UID` = " + "\"" + ID + "\"";
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            data = rs.getString(屬性);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取user帳號失敗");
            Log.e("DB", e.toString());
        }
        return data;
    }

    //獲得使用人數/總人數的資料
    public void get_people_use(ArrayList data, ArrayList data1, String week_index, String Date, String 需求) {
        Log.e("get_people_sue", "進入搜尋人力配置資料");
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            if (需求.equals("我要單日的Empty")) {
                sql = "SELECT * FROM `people_use` WHERE `Date` = " + "\"" + Date + "\"";
            }
            if (需求.equals("我要一週的Empty")) {
                sql = "SELECT  `Date`,`星期`,`time`,`used`,`total`  FROM `people_use` WHERE  `Date` LIKE  '%" + Date + "%' ";
            }
            Log.e("week_index",week_index);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int 上限星期 = 5;
            int count = 1;
            while (rs.next()) {
                if (需求.equals("我要一週的Empty")) {
                    String db_date = rs.getString("Date");
                    if(Integer.parseInt(db_date)/100 == Integer.parseInt(week_index)/100){
                        String total = rs.getString("total");
                        String used = rs.getString("used");
                        String 星期 = rs.getString("星期");
                        String 隔間 = rs.getString("time");

                        int 區間 = ((Integer.parseInt(星期))-1)*18+Integer.parseInt(隔間);
                        if(Integer.parseInt(db_date) == Integer.parseInt(week_index)){
                            data.set(區間+1,used+"/");
                            data1.set(區間+1,total);
                            上限星期 = Integer.parseInt(rs.getString("星期"));
                            Log.e("更改上限日期成功",""+上限星期);
                        }
                        else if(Integer.parseInt(db_date) == Integer.parseInt(week_index)+1 && Integer.parseInt(星期) > 上限星期){
                            data.set(區間+1,used+"/");
                            data1.set(區間+1,total);
                        }
                        else if(Integer.parseInt(db_date) == Integer.parseInt(week_index)+2 && Integer.parseInt(星期) > 上限星期){
                            data.set(區間+1,used+"/");
                            data1.set(區間+1,total);
                        }
                        else if(Integer.parseInt(db_date) == Integer.parseInt(week_index)+3 && Integer.parseInt(星期) > 上限星期){
                            data.set(區間+1,used+"/");
                            data1.set(區間+1,total);
                        }
                        else if(Integer.parseInt(db_date) == Integer.parseInt(week_index)+4 && Integer.parseInt(星期) > 上限星期){
                            data.set(區間+1,used+"/");
                            data1.set(區間+1,total);
                        }
                    }
                } else if (需求.equals("我要單日的Empty")) {
                    String total = rs.getString("total");
                    String used = rs.getString("used");
                    data.set(count, used + "/");
                    data1.set(count, total);
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取日人力配置失敗");
            Log.e("DB", e.toString());
        }
    }

    //計算月概況總共有幾週
    public ArrayList get_week_count(int month) {
        ArrayList data = new ArrayList();
        String Date = new String();
        if (month < 10) {
            Date = "0" + String.valueOf(month);
        } else {
            Date = String.valueOf(month);
        }
        int count = 0;
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            sql = "SELECT  `Date`,`星期`,`used`,`total`  FROM `people_use` WHERE  `Date` LIKE  '%" + Date + "%' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String db_date = rs.getString("Date");
                String 星期 = rs.getString("星期");
                if(count == 0){
                    if(Integer.parseInt(db_date)/100 == month){
                        if(Integer.parseInt(星期) != 1){
                            if(!data.contains(db_date+"~")) {
                                data.add(db_date+"~");
                                count++;
                            }
                        }
                    }
                }
                else{
                    if(Integer.parseInt(db_date)/100 == month) {
                        if (Integer.parseInt(星期) == 1) {
                            if(!data.contains(db_date+"~")) {
                                data.add(db_date+"~");
                                count++;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取週的數目配置失敗");
            Log.e("DB", e.toString());
        }
        return data;
    }
    public ArrayList getworkreport_UID(String Date,String 需求,String 帳號){
        ArrayList data = new ArrayList();
        String sql = "";
        try{
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String ID = get_ID(帳號,"我要caregiverID");
            sql = "SELECT * FROM `schedule` WHERE `CID` = " + "\"" + ID + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                if(Date.equals(rs.getString("Date"))) {
                    String UID = rs.getString("UID");
                    data.add(UID);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            Log.e("DB", "獲取工作報表UID失敗");
            Log.e("DB", e.toString());
        }

        return data;
    }
    public ArrayList getschedule_work(String Date, String firsttime, String 需求, String 帳號) {
        ArrayList data = new ArrayList();
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `schedule` WHERE `CID` = " + "\"" + 帳號 + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (firsttime.equals(rs.getString("FirstTime"))) {
                    GetUsertimeData(data, rs, Date, rs.getString("UID"), "我要schedule工作內容");
                    String sql1 = "SELECT * FROM `longcare`.`user` WHERE `UID` = \"" + rs.getString("UID") + "\"";
                    Statement st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery(sql1);
                    rs1.next();
                    data.set(2, rs1.getString("UName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "獲取週的數目配置失敗");
            Log.e("DB", e.toString());
        }
        return data;

    }


    public ArrayList getUserUID(String 照服員帳號,String strDate){

        ArrayList UID = new ArrayList();
        //UID.add("No data");

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

            Log.e("照服員帳號",照服員帳號);

            int flag = 0;
            while(re2.next()){
                if(re2.getString("Date").equals(strDate)){
                    if(UID.size() == 1 && flag == 0) {
                        UID.set(0, re2.getString("UID"));
                        flag = 1;
                        //Log.e("Yes",re2.getString("UID"));
                    }else {
                        UID.add(re2.getString("UID"));
                       // Log.e("Yes",re2.getString("UID"));
                    }
                }else{
                     Log.e("Not time:",re2.getString("Date"));
                }
            }

        }catch(SQLException e){
            Log.e("DB","獲取UID失敗");
        }

        for(int j = 0;j < UID.size();j++)
            Log.e("DBtest1",UID.get(j).toString());

        return UID;
    }

    public ArrayList getSchedule_UID(String Date,String 需求){
        String sql = new String();
        ArrayList data = new ArrayList();
        try {
            if (需求.equals("我要schedule單日的所有的UID")) {
                sql = "SELECT * FROM `schedule` WHERE `Date` = " + "\"" + Date + "\"";
            }
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String id = rs.getString("UID");
                data.add(id);
            }
        }
        catch(SQLException e){
            Log.e("DB","獲取schedule當日所有UID失敗");
        }
        return data;
    }
    public ArrayList getName(ArrayList UID){

        ArrayList name = new ArrayList();

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

    public ArrayList getcaregiverworkcontent(String 照服員帳號, String UID,String strDate){

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

            //工作內容
            String sql3 =  "SELECT * FROM `usertime` WHERE `UID` = " + "\"" + UID + "\"";
            ResultSet rs3 = st.executeQuery(sql3);
            //Log.e("UID",UID);
            //Log.e("Sql3:",sql3);
            //Log.e("time",strDate);
            content.add("No data");

            while(rs3.next()){
                //Log.e("Data",rs3.getString("Date")+rs3.getString("UID"));
                if(rs3.getString("Date").equals(strDate) &&
                    rs3.getString("UID").equals(UID)){
                    content.set(3,rs3.getString("Request"));
                    //Log.e("Request: ",rs3.getString("Request"));
                    break;
                }
            }



            // Log.e("Final",content.get(3).toString());

        }catch(SQLException e){

        }

        return content;//照服員名字,開始時間,結束時間,工作內容
    }

    public ArrayList get_recent_date(String Date,String 需求,String 帳號){
        ArrayList data = new ArrayList();
        ArrayList data_chase = new ArrayList();
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String CID = get_ID(帳號,"我要caregiverID");
            String sql = "SELECT * FROM `schedule` WHERE `CID` = " + "\"" + CID + "\"";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String db_date = rs.getString("Date");
                if(Integer.parseInt(db_date) <= Integer.parseInt(Date)){
                    data_chase.add(db_date);
                }
            }
            Log.e("data_chase",""+data_chase.size());
            for(int i = 0 ; i < data_chase.size() ; i ++){
                for(int j = 1 ; j < data_chase.size() ; j++){
                    String a = ""+data_chase.get(i);
                    String b = ""+data_chase.get(j);
                    if(Integer.parseInt(a)> Integer.parseInt(b)){
                        String c = a;
                        a = b ;
                        b = c;
                    }
                }
            }

            for (int i = 0 ; i < data_chase.size() ; i ++){
                data.add(data_chase.get(i));
                if(i == 4){
                    break;
                }
            }

            Log.e("DB_GetRecentDate","獲取資料成功");
            //Log.e("Date",data.get(0)+","+data.get(1)+","+data.get(2)+","+data.get(3)+","+data.get(4));
        }
        catch(SQLException e){
            Log.e("DB","獲取以前日期失敗");
            Log.e("DB",e.toString());
        }
        return data;
    }

}




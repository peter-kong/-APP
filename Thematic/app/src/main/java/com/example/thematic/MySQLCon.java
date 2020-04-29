package com.example.mysql_connect;


import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLCon {

    //final String[] user_data = getResources().getStringArray(R.array.user_data);
    // 資料庫定義
    String mysql_ip = "192.168.0.180";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "longcare";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String db_user = "LongCareUser";
    String db_password = "12345";

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
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }


    public String getData() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM Manager";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("MID");
                String name = rs.getString("Mname");
                data += id + ", " + name + "\n";
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    //寫入註冊的資料
    public void insertRegisterData(String UName,String UAccount,String UPassword,
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
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
    }

}
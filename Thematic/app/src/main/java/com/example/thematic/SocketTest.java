package com.example.thematic;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thematic.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SocketTest extends AppCompatActivity {

    Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SocketTest.this.setContentView(R.layout.activity_socket_test);

        //在onCreate()內，註冊按鈕按下的監聽(schedule.setOnClickListener)
        schedule = (Button) findViewById(R.id.Scheduling);
        schedule.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View V){
                schedule.setEnabled(false);//不可按
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InetAddress serverAddr = null;//利用裡面的方法來撈網址
                        SocketAddress sc_add = null;//不帶任何協議附件的 Socket Address
                        Socket socket = null;

                        String message = "I need SC";
                        String IP = "192.168.0.141";
                        int port = 6606;
                        try {
                            //設定 Server IP, Port
                            serverAddr = InetAddress.getByName(IP);
                            sc_add = new InetSocketAddress(serverAddr, port);

                            Log.e("Line 44", "Good");

                            socket = new Socket();
                            //與 Server 連線，timeout 時間 2 秒


                            Log.e("Line 50", "Good");
                            //  Log.e("Line 51",sc_add.toString());
                            socket.connect(sc_add, 10000);


                            Log.e("Line 55", "Good");

                            //傳送資料(無視機器平台輸出語法)
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                            Log.e("Line 60", "Good");

                            out.writeUTF(message);

                            Log.e("Line 64", "Good");

                            socket.close();

                        } catch (UnknownHostException e) {
                            schedule.setText("InetAddress物件建立失敗");
                        } catch (SocketException e) {
                            schedule.setText("socket建立失敗");
                        } catch (IOException e) {
                            schedule.setText("傳送失敗");
                        }

                    }
                }).start();
            }
        });
    }
}

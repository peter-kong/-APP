package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Schedule0 extends AppCompatActivity {

    Button sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_schedule0);
        getSupportActionBar().hide(); //隱藏標題

        sc = (Button)findViewById(R.id.schedule);
        sc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View V) {
                sc.setEnabled(false);
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        InetAddress serverAddr = null;
                        SocketAddress sc_add = null;
                        Socket socket = null;
                        String message = "I need SC";
                        String IP = "134.208.41.238";
                        int port = 21;

                        try {
                            serverAddr = InetAddress.getByName(IP);
                            sc_add = new InetSocketAddress(serverAddr, port);

                            socket = new Socket();
                            socket.connect(sc_add,2000);
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                            out.writeUTF(message);

                            socket.close();
                        }catch(UnknownHostException e){
                            sc.setText("InetAddress物件建立失敗");
                        }catch(SocketException e){
                            sc.setText("socket建立失敗");
                        }catch (IOException e){
                            sc.setText("傳送失敗");
                        }
                    }
                }).start();

            }


        });

        Button search = (Button) findViewById(R.id.Schedule_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Schedule0.this, Schedule.class);
                startActivity(intent);
            }
        });
    }
}

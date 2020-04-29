package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
=======
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

>>>>>>> 50809e177bedb70164662f11d974e89ecc08d882

public class Register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        getSupportActionBar().hide(); //隱藏標題

<<<<<<< HEAD
        Button reset = (Button)findViewById(R.id.register_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Register.this, Register.class);
                startActivity(intent);
            }
        });

}
=======
        Button register_confirm_btn = (Button) findViewById(R.id.register_confirm);
        register_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        final EditText UName_view = (EditText) findViewById(R.id.UName);
                        final EditText UAccount_view = (EditText) findViewById(R.id.UAccount);
                        final EditText UPassowrd_view = (EditText) findViewById(R.id.UPassword);
                        final EditText UIDNumber_view = (EditText) findViewById(R.id.UIDNumber);
                        final EditText UAddress_view = (EditText) findViewById(R.id.UAddress);
                        final EditText Uphone_view = (EditText) findViewById(R.id.UPhone);
                        final EditText UEmail_view = (EditText) findViewById(R.id.UEmail);
                        final EditText UMedHistory_view = (EditText) findViewById(R.id.UMedHistory);
                        final EditText ULevel_view = (EditText) findViewById(R.id.ULevel);
                        //final EditText UGenderB_view = (EditText) findViewById(R.id.Boy);
                        //final EditText UGenderG_view = (EditText) findViewById(R.id.girl);
                        final EditText UBirth_view = (EditText) findViewById(R.id.UBirth);

                        String UName = UName_view.getText().toString();
                        String UAccount = UAccount_view.getText().toString();
                        String UPassword = UPassowrd_view.getText().toString();
                        String UIDNumber = UIDNumber_view.getText().toString();
                        String UAddress = UAddress_view.getText().toString();
                        String Uphone = Uphone_view.getText().toString();
                        String UEmail = UEmail_view.getText().toString();
                        String UMedHistory = UMedHistory_view.getText().toString();
                        String ULevel = ULevel_view.getText().toString();

                        String UBirth = UBirth_view.getText().toString();

                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.insertRegisterData(UName,UAccount,UPassword,UIDNumber,UAddress,Uphone,UEmail,UMedHistory,ULevel,UBirth);
                    }
                }).start();
            }
        });
    }
>>>>>>> 50809e177bedb70164662f11d974e89ecc08d882
}

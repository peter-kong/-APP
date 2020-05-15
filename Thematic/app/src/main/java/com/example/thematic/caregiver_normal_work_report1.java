package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class caregiver_normal_work_report1 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        Log.e("Line 39","reminded");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_normal_work_report1);
        getSupportActionBar().hide(); //隱藏標題

        Log.e("Line 46","remind");


        final Spinner 個案名稱 = (Spinner)findViewById(R.id.個案下拉選單);

        Log.e("Line 51","remind");


        GlobalVariable_Account t = (GlobalVariable_Account)getApplicationContext();
        String dat = t.returnScheduleDate();
        ArrayList name = t.returnName();
        //ArrayList id = t.returnUID();
        ArrayList nameid = null;

        /*
        for(int i = 0;i < name.size();i++){
            nameid.add(name.get(i).toString()+id.get(i).toString());
            Log.e("nameid", nameid.get(i).toString());
        }
        */
        /*
        for(int i = 0;i < name.size();i++)
            Log.e("name:",name.get(i).toString());
        */
        ArrayList curuid = new ArrayList();
        curuid.add("No data");

        String[] namestr = new String[name.size()];
        name.toArray(namestr);

        for(int j = 0;j < name.size();j++){
            Log.e("namestr",name.get(j).toString());
        }

        ArrayAdapter datelist = new ArrayAdapter(caregiver_normal_work_report1.this, R.layout.myspinner,namestr);
        datelist.setDropDownViewResource(R.layout.myspinner);
        個案名稱.setAdapter(datelist);

        GlobalVariable_Account judgetoday = (GlobalVariable_Account)getApplicationContext();

        //日期
        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
        Date date = new Date();

        if(judgetoday.returnTommorrowToday()) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, 1);
            date = calendar.getTime();
        }

        String strDate= judgetoday.returnScheduleDate();
        final TextView 日期 = (TextView)findViewById(R.id.個案日期);
        日期.setText(strDate);




        //選取spinner
        個案名稱.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被選擇项的值進行連接DB並顯示資料
                String chooseDate = (String) 個案名稱.getSelectedItem();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        String caregiver帳號  = obj.returnAcc();

                        int i = 0;
                        ArrayList 所有當前照護員被照護名字 = obj.returnName();
                        while(!所有當前照護員被照護名字.get(i).equals(chooseDate)){
                            i++;
                        }

                        //Log.e("所有當前照護員被照護UID",obj.returnUID().get(i).toString());
                        curuid.set(0,obj.returnUID().get(i));

                        //Log.e("DB 今日",strDate);


                        ArrayList data = con.getcaregiverworkcontent(caregiver帳號,
                                    obj.returnUID().get(i).toString(),strDate);
                        //照服員名字,開始時間,結束時間
                        String Time = data.get(1).toString() + " - " +data.get(2).toString();
                        final TextView workername = (TextView)findViewById(R.id.workername);
                        final TextView Timeview = (TextView)findViewById(R.id.Time);

                        workername.post(new Runnable() {
                            public void run() {
                                workername.setText(data.get(0).toString());
                            }
                        });

                        Timeview.post(new Runnable() {
                            public void run() {
                                Timeview.setText(Time);
                            }
                        });


                        LinearLayout work = (LinearLayout)findViewById(R.id.工作內容);

                        //reset the Require
                        work.post(new Runnable() {
                            @Override
                            public void run() {
                                if(((LinearLayout) work).getChildCount() > 0)
                                    ((LinearLayout) work).removeAllViews();

                            }
                        });

                        String [] 工作 = data.get(3).toString().split("、");
                        //Log.e("工作一", 工作[0]);

                        GlobalVariable_Account tmp = (GlobalVariable_Account)getApplicationContext();

                        //裝True False
                        ArrayList 工作名 = new ArrayList();
                        ArrayList Finish = new ArrayList();

                        for(int k = 0;k < 工作.length;k++) {
                            Finish.add("×");
                            工作名.add(工作[k]);
                            //Log.e("tags",工作名.get(k).toString());
                        }


                        //辨識True False
                        CompoundButton.OnCheckedChangeListener checkBoxOnCheckedChange =
                                new CompoundButton.OnCheckedChangeListener()
                                {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                                    { //buttonView 為目前觸發此事件的 CheckBox, isChecked 為此 CheckBox 目前的選取狀態
                                        if(isChecked)//等於 buttonView.isChecked()
                                        {
                                            Toast.makeText(getApplicationContext(),buttonView.getText()+" 被選取", Toast.LENGTH_LONG).show();
                                            for(int k = 0;k < 工作名.size();k++) {
                                                if (工作名.get(k).toString().equals(buttonView.getText())) {
                                                    Finish.set(k, "√");
                                                    Log.e(工作名.get(k).toString(),Finish.get(k).toString());
                                                }
                                            }

                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),buttonView.getText()+" 被取消", Toast.LENGTH_LONG).show();
                                            for(int k = 0;k < 工作名.size();k++) {
                                                if (工作名.get(k).toString().equals(buttonView.getText())) {
                                                    Finish.set(k, "False");
                                                    Log.e(工作名.get(k).toString(),Finish.get(k).toString());
                                                }
                                            }
                                        }

                                        tmp.setFinish(Finish);

                                        tmp.println();
                                    }
                                };


                        for(int k = 0;k < 工作.length;k++){

                            Log.e("Line 122",工作[k]);
                            CheckBox work1 = new CheckBox(caregiver_normal_work_report1.this);
                            work1.setTextSize(30);
                            work1.setText("             "+工作[k]);
                            work1.setOnCheckedChangeListener(checkBoxOnCheckedChange);

                            work.post(new Runnable() {
                                @Override
                                public void run() {
                                    work.addView(work1, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                }
                            });
                            Log.e("Work Normal",工作[k]);
                        }

                    }
                }).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected","沒有選則內容");
            }
        });



        GlobalVariable_Account test = (GlobalVariable_Account)getApplicationContext();

        Button Send = (Button)findViewById(R.id.Send);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        test.println();

                        TextView 備註 = (TextView)findViewById(R.id.備註);
                        String meg = 備註.getText().toString();


                        //檢查沒有輸入
                        ArrayList checkpoint = test.returnFinish();

                        int c = 0;

                        if(checkpoint != null)
                            c = 1;

                        Log.e("meg",meg);

                        if(c == 1) {
                            com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                            con.SendFinishandnotice(curuid.get(0).toString(), test.returnFinish(), meg, strDate);
                            Log.e("Data: ", curuid.get(0).toString() + test.returnFinish().get(0).toString());

                            Intent intent = new Intent();
                            intent.setClass(caregiver_normal_work_report1.this, Menu_for_caregiver.class);
                            startActivity(intent);
                        }

                    }
                }).start();
            }
        });

    }
}

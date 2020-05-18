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

public class caregiver_normal_work_report extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_normal_work_report);
        getSupportActionBar().hide(); //隱藏標題

        final Spinner 個案名稱 = (Spinner)findViewById(R.id.個案下拉選單);
        GlobalVariable_Account t = (GlobalVariable_Account)getApplicationContext();
        String dat = t.returnScheduleDate();
        ArrayList name = t.returnName();
        ArrayList curuid = new ArrayList();
        curuid.add("");
        String[] namestr = new String[name.size()];
        name.toArray(namestr);

        for(int j = 0;j < name.size();j++){
            Log.e("namestr",name.get(j).toString());
        }

        ArrayAdapter datelist = new ArrayAdapter(caregiver_normal_work_report.this, R.layout.myspinner,namestr);
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
        日期.setText(dat);


        //選取spinner
        個案名稱.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被選擇项的值進行連接DB並顯示資料
                String chooseDate = (String) 個案名稱.getSelectedItem();
                int choose_index = 個案名稱.getSelectedItemPosition();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        String caregiver帳號  = obj.returnAcc();

                        //Log.e("所有當前照護員被照護UID",obj.returnUID().get(i).toString());
                        curuid.set(0,obj.returnUID().get(choose_index));


                        ArrayList data = con.getcaregiverworkcontent(caregiver帳號,
                                obj.returnUID().get(choose_index).toString(),strDate);

                        ArrayList work_list = con.獲得填寫工作報表(con.get_ID(caregiver帳號,"我要caregiverID"),""+obj.returnUID().get(choose_index),strDate);
                        ArrayList time_list = con.獲得工作時間(con.get_ID(caregiver帳號,"我要caregiverID"),""+obj.returnUID().get(choose_index),strDate);
                        Log.e("time)list長度",time_list.size()+"");
                        obj.setTimeList(time_list);

                        //照服員名字,開始時間,結束時間
                        String Time = work_list.get(0)+"~"+work_list.get(1);

                        final TextView workername = (TextView)findViewById(R.id.workername);
                        final TextView Timeview = (TextView)findViewById(R.id.Time);

                        workername.post(new Runnable() {
                            public void run() {
                                workername.setText(""+work_list.get(2));
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
                        Log.e("work_list.size",""+work_list.size());
                        String [] 工作 = new String[work_list.size()-3];
                        int work_count = 0;
                        for(int i = 3 ; i < work_list.size();i++){
                            工作[work_count] = work_list.get(i)+"";
                            work_count++;
                        }
                        //Log.e("工作一", 工作[0]);

                        GlobalVariable_Account tmp = (GlobalVariable_Account)getApplicationContext();

                        //裝True False
                        ArrayList 工作名 = new ArrayList();
                        ArrayList Finish = new ArrayList();
                        Log.e("工作長度",""+工作.length);
                        for(int k = 0;k < 工作.length;k++) {
                            Finish.add("×");
                            工作名.add(工作[k]);
                            Log.e("tags",工作名.get(k).toString());
                        }
                        tmp.setFinish(Finish);
                        Log.e("Finish長度",Finish.size()+","+工作.length);
                        工作名.add("");
                        工作名.add("");
                        //辨識True False
                        final CheckBox work1 = (CheckBox) findViewById(R.id. work1);
                        final CheckBox work2 = (CheckBox) findViewById(R.id. work2);
                        final CheckBox work3 = (CheckBox) findViewById(R.id. work3);
                        CompoundButton.OnCheckedChangeListener checkBoxOnCheckedChange =
                                new CompoundButton.OnCheckedChangeListener()
                                {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                                    { //buttonView 為目前觸發此事件的 CheckBox, isChecked 為此 CheckBox 目前的選取狀態
                                       Log.e("進入檢查","check");
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
                                                    Finish.set(k, "×");
                                                    Log.e(工作名.get(k).toString(),Finish.get(k).toString());
                                                }
                                            }
                                        }
                                        Log.e("Finish案時長度",Finish.size()+"");
                                        tmp.setFinish(Finish);

                                        tmp.println();
                                    }
                                };



                        work1.setOnCheckedChangeListener(checkBoxOnCheckedChange);
                        work2.setOnCheckedChangeListener(checkBoxOnCheckedChange);
                        work3.setOnCheckedChangeListener(checkBoxOnCheckedChange);
                        if(工作名.get(0).equals("")){
                            work1.post(new Runnable() {
                                public void run() {
                                    work1.setVisibility(View.GONE);
                                }
                            });

                        }
                        else{
                            work1.setText(工作名.get(0)+"");
                            work1.post(new Runnable() {
                                public void run() {
                                    work1.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        if(工作名.get(1).equals("")){
                            work2.post(new Runnable() {
                                public void run() {
                                    work2.setVisibility(View.GONE);
                                }
                            });


                        }
                        else{
                            work2.setText(工作名.get(1)+"");
                            work2.post(new Runnable() {
                                public void run() {
                                    work2.setVisibility(View.VISIBLE);
                                }
                            });

                        }
                        if(工作名.get(2).equals("")){

                            work3.post(new Runnable() {
                                public void run() {
                                    work3.setVisibility(View.GONE);
                                }
                            });
                        }
                        else{
                            work3.setText(工作名.get(2)+"");
                            work3.post(new Runnable() {
                                public void run() {
                                    work3.setVisibility(View.VISIBLE);
                                }
                            });
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
                        Log.e("進入按鈕","");
                        test.println();
                        Log.e("進入按鈕","");
                        TextView 備註 = (TextView)findViewById(R.id.備註);
                        String meg = 備註.getText().toString();
                        Log.e("進入按鈕","");

                        //檢查沒有輸入
                        ArrayList checkpoint = test.returnFinish();
                        Log.e("進入按鈕","");
                        int c = 0;
                        Log.e("進入按鈕","");
                        if(checkpoint != null)
                            c = 1;
                        Log.e("進入按鈕","");
                        Log.e("meg",meg);
                        Log.e("進入按鈕","");
                        if(c == 1) {
                            com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                            GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                            String caregiver帳號 = obj.returnAcc();
                            Log.e("start上傳","");
                            Log.e("data:",con.get_ID(caregiver帳號,"我要caregiverID")+","+curuid.get(0).toString()+
                                    ","+test.returnFinish()+","+obj.returnTimeList()+","+strDate+","+meg);
                            con.上傳工作報表(con.get_ID(caregiver帳號,"我要caregiverID"), curuid.get(0).toString(), test.returnFinish(),obj.returnTimeList(), strDate,meg);

                            Intent intent = new Intent();
                            intent.setClass(caregiver_normal_work_report.this, caregiver_normal_work_report.class);
                            startActivity(intent);
                        }

                    }
                }).start();
            }
        });

    }

}

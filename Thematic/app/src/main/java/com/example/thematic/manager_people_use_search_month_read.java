package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class manager_people_use_search_month_read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_people_use_search_month_read);
        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
        ArrayList Gobal_week = obj.returnWeekCount();
        String[] week = new String[Gobal_week.size()];
        Gobal_week.toArray(week);
        final TextView count1 = (TextView)findViewById(R.id.count1);
        final TextView count2 = (TextView)findViewById(R.id.count2);
        final TextView count3 = (TextView)findViewById(R.id.count3);
        final TextView count4 = (TextView)findViewById(R.id.count4);
        final TextView count5 = (TextView)findViewById(R.id.count5);
        final TextView count6 = (TextView)findViewById(R.id.count6);
        final TextView count7 = (TextView)findViewById(R.id.count7);
        final TextView count8 = (TextView)findViewById(R.id.count8);
        final TextView count9 = (TextView)findViewById(R.id.count9);
        final TextView count10 = (TextView)findViewById(R.id.count10);
        final TextView count11 = (TextView)findViewById(R.id.count11);
        final TextView count12 = (TextView)findViewById(R.id.count12);
        final TextView count13 = (TextView)findViewById(R.id.count13);
        final TextView count14 = (TextView)findViewById(R.id.count14);
        final TextView count15 = (TextView)findViewById(R.id.count15);
        final TextView count16 = (TextView)findViewById(R.id.count16);
        final TextView count17 = (TextView)findViewById(R.id.count17);
        final TextView count18 = (TextView)findViewById(R.id.count18);
        final TextView count19 = (TextView)findViewById(R.id.count19);
        final TextView count20 = (TextView)findViewById(R.id.count20);
        final TextView count21 = (TextView)findViewById(R.id.count21);
        final TextView count22 = (TextView)findViewById(R.id.count22);
        final TextView count23 = (TextView)findViewById(R.id.count23);
        final TextView count24 = (TextView)findViewById(R.id.count24);
        final TextView count25 = (TextView)findViewById(R.id.count25);
        final TextView count26 = (TextView)findViewById(R.id.count26);
        final TextView count27 = (TextView)findViewById(R.id.count27);
        final TextView count28 = (TextView)findViewById(R.id.count28);
        final TextView count29 = (TextView)findViewById(R.id.count29);
        final TextView count30 = (TextView)findViewById(R.id.count30);
        final TextView count31 = (TextView)findViewById(R.id.count31);
        final TextView count32 = (TextView)findViewById(R.id.count32);
        final TextView count33 = (TextView)findViewById(R.id.count33);
        final TextView count34 = (TextView)findViewById(R.id.count34);
        final TextView count35 = (TextView)findViewById(R.id.count35);
        final TextView count36 = (TextView)findViewById(R.id.count36);
        final TextView count37 = (TextView)findViewById(R.id.count37);
        final TextView count38 = (TextView)findViewById(R.id.count38);
        final TextView count39 = (TextView)findViewById(R.id.count39);
        final TextView count40 = (TextView)findViewById(R.id.count40);
        final TextView count41 = (TextView)findViewById(R.id.count41);
        final TextView count42 = (TextView)findViewById(R.id.count42);
        final TextView count43 = (TextView)findViewById(R.id.count43);
        final TextView count44 = (TextView)findViewById(R.id.count44);
        final TextView count45 = (TextView)findViewById(R.id.count45);
        final TextView count46 = (TextView)findViewById(R.id.count46);
        final TextView count47 = (TextView)findViewById(R.id.count47);
        final TextView count48 = (TextView)findViewById(R.id.count48);
        final TextView count49 = (TextView)findViewById(R.id.count49);
        final TextView count50 = (TextView)findViewById(R.id.count50);
        final TextView count51 = (TextView)findViewById(R.id.count51);
        final TextView count52 = (TextView)findViewById(R.id.count52);
        final TextView count53 = (TextView)findViewById(R.id.count53);
        final TextView count54 = (TextView)findViewById(R.id.count54);
        final TextView count55 = (TextView)findViewById(R.id.count55);
        final TextView count56 = (TextView)findViewById(R.id.count56);
        final TextView count57 = (TextView)findViewById(R.id.count57);
        final TextView count58 = (TextView)findViewById(R.id.count58);
        final TextView count59 = (TextView)findViewById(R.id.count59);
        final TextView count60 = (TextView)findViewById(R.id.count60);
        final TextView count61 = (TextView)findViewById(R.id.count61);
        final TextView count62 = (TextView)findViewById(R.id.count62);
        final TextView count63 = (TextView)findViewById(R.id.count63);
        final TextView count64 = (TextView)findViewById(R.id.count64);
        final TextView count65 = (TextView)findViewById(R.id.count65);
        final TextView count66 = (TextView)findViewById(R.id.count66);
        final TextView count67 = (TextView)findViewById(R.id.count67);
        final TextView count68 = (TextView)findViewById(R.id.count68);
        final TextView count69 = (TextView)findViewById(R.id.count69);
        final TextView count70 = (TextView)findViewById(R.id.count70);
        final TextView count71 = (TextView)findViewById(R.id.count71);
        final TextView count72 = (TextView)findViewById(R.id.count72);
        final TextView count73 = (TextView)findViewById(R.id.count73);
        final TextView count74 = (TextView)findViewById(R.id.count74);
        final TextView count75 = (TextView)findViewById(R.id.count75);
        final TextView count76 = (TextView)findViewById(R.id.count76);
        final TextView count77 = (TextView)findViewById(R.id.count77);
        final TextView count78 = (TextView)findViewById(R.id.count78);
        final TextView count79 = (TextView)findViewById(R.id.count79);
        final TextView count80 = (TextView)findViewById(R.id.count80);
        final TextView count81 = (TextView)findViewById(R.id.count81);
        final TextView count82 = (TextView)findViewById(R.id.count82);
        final TextView count83 = (TextView)findViewById(R.id.count83);
        final TextView count84 = (TextView)findViewById(R.id.count84);
        final TextView count85 = (TextView)findViewById(R.id.count85);
        final TextView count86 = (TextView)findViewById(R.id.count86);
        final TextView count87 = (TextView)findViewById(R.id.count87);
        final TextView count88 = (TextView)findViewById(R.id.count88);
        final TextView count89 = (TextView)findViewById(R.id.count89);
        final TextView count90 = (TextView)findViewById(R.id.count90);
        //spinner添加月份的週數量選項
        Log.e("117","117");
        final Spinner spnWeek = (Spinner)findViewById(R.id.週);
        ArrayAdapter adapWeekList = new ArrayAdapter(manager_people_use_search_month_read.this, android.R.layout.simple_spinner_item, week  );
        adapWeekList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWeek.setAdapter(adapWeekList);

        spnWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ArrayList data = new ArrayList();   //區間的使用人數
                ArrayList data1 = new ArrayList();  //區間的總人數
                for (int i = 0 ; i < 92 ; i++){
                    data.add("");
                    data1.add("");
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String choose_index = (String)spnWeek.getSelectedItem();
                        choose_index = choose_index.replace("~","");
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        int month  = obj.returnMonth();

                        Log.e("choose_index",""+choose_index);
                        for (int i = 0 ; i < 92 ; i ++){
                            data.set(i,"");
                            data1.set(i,"");
                        }
                        con.get_people_use(data ,data1,choose_index,String.valueOf(month),"我要一週的Empty");
                        count1.post(new Runnable() {
                            public void run() {
                                count1.setText(data.get(1)+""+data1.get(1));
                            }
                        });
                        count2.post(new Runnable() {
                            public void run() {
                                count2.setText(data.get(2)+""+data1.get(2));
                            }
                        });
                        count3.post(new Runnable() {
                            public void run() {
                                count3.setText(data.get(3)+""+data1.get(3));
                            }
                        });
                        count4.post(new Runnable() {
                            public void run() {
                                count4.setText(data.get(4)+""+data1.get(4));
                            }
                        });
                        count5.post(new Runnable() {
                            public void run() {
                                count5.setText(data.get(5)+""+data1.get(5));
                            }
                        });
                        count6.post(new Runnable() {
                            public void run() {
                                count6.setText(data.get(6)+""+data1.get(6));
                            }
                        });
                        count7.post(new Runnable() {
                            public void run() {
                                count7.setText(data.get(7)+""+data1.get(7));
                            }
                        });
                        count8.post(new Runnable() {
                            public void run() {
                                count8.setText(data.get(8)+""+data1.get(8));
                            }
                        });
                        count9.post(new Runnable() {
                            public void run() {
                                count9.setText(data.get(9)+""+data1.get(9));
                            }
                        });
                        count10.post(new Runnable() {
                            public void run() {
                                count10.setText(data.get(10)+""+data1.get(10));
                            }
                        });
                        count11.post(new Runnable() {
                            public void run() {
                                count11.setText(data.get(11)+""+data1.get(11));
                            }
                        });
                        count12.post(new Runnable() {
                            public void run() {
                                count12.setText(data.get(12)+""+data1.get(12));
                            }
                        });
                        count13.post(new Runnable() {
                            public void run() {
                                count13.setText(data.get(13)+""+data1.get(13));
                            }
                        });
                        count14.post(new Runnable() {
                            public void run() {
                                count14.setText(data.get(14)+""+data1.get(14));
                            }
                        });
                        count15.post(new Runnable() {
                            public void run() {
                                count15.setText(data.get(15)+""+data1.get(15));
                            }
                        });
                        count16.post(new Runnable() {
                            public void run() { count16.setText(data.get(16)+""+data1.get(16));
                            }
                        });
                        count17.post(new Runnable() {
                            public void run() {
                                count17.setText(data.get(17)+""+data1.get(17));
                            }
                        });
                        count18.post(new Runnable() {
                            public void run() {
                                count18.setText(data.get(18)+""+data1.get(18));
                            }
                        });
                        count19.post(new Runnable() {
                            public void run() {
                                count19.setText(data.get(19)+""+data1.get(19));
                            }
                        });
                        count20.post(new Runnable() {
                            public void run() {
                                count20.setText(data.get(20)+""+data1.get(20));
                            }
                        });
                        count21.post(new Runnable() {
                            public void run() {
                                count21.setText(data.get(21)+""+data1.get(21));
                            }
                        });
                        count22.post(new Runnable() {
                            public void run() {
                                count22.setText(data.get(22)+""+data1.get(22));
                            }
                        });
                        count23.post(new Runnable() {
                            public void run() {
                                count23.setText(data.get(23)+""+data1.get(23));
                            }
                        });
                        count24.post(new Runnable() {
                            public void run() {
                                count24.setText(data.get(24)+""+data1.get(24));
                            }
                        });
                        count25.post(new Runnable() {
                            public void run() {
                                count25.setText(data.get(25)+""+data1.get(25));
                            }
                        });
                        count26.post(new Runnable() {
                            public void run() {
                                count26.setText(data.get(26)+""+data1.get(26));
                            }
                        });
                        count27.post(new Runnable() {
                            public void run() {
                                count27.setText(data.get(27)+""+data1.get(27));
                            }
                        });
                        count28.post(new Runnable() {
                            public void run() {
                                count28.setText(data.get(28)+""+data1.get(28));
                            }
                        });
                        count29.post(new Runnable() {
                            public void run() {
                                count29.setText(data.get(29)+""+data1.get(29));
                            }
                        });
                        count30.post(new Runnable() {
                            public void run() {
                                count30.setText(data.get(30)+""+data1.get(30));
                            }
                        });
                        count31.post(new Runnable() {
                            public void run() {
                                count31.setText(data.get(31)+""+data1.get(31));
                            }
                        });

                        count32.post(new Runnable() {
                            public void run() {
                                count32.setText(data.get(32)+""+data1.get(32));
                            }
                        });
                        count33.post(new Runnable() {
                            public void run() {
                                count33.setText(data.get(33)+""+data1.get(33));
                            }
                        });
                        count34.post(new Runnable() {
                            public void run() {
                                count34.setText(data.get(34)+""+data1.get(34));
                            }
                        });
                        count35.post(new Runnable() {
                            public void run() {
                                count35.setText(data.get(35)+""+data1.get(35));
                            }
                        });

                        count36.post(new Runnable() {
                            public void run() {
                                count36.setText(data.get(36)+""+data1.get(36));
                            }
                        });

                        count37.post(new Runnable() {
                            public void run() {
                                count37.setText(data.get(37)+""+data1.get(37));
                            }
                        });

                        count38.post(new Runnable() {
                            public void run() {
                                count38.setText(data.get(38)+""+data1.get(38));
                            }
                        });

                        count39.post(new Runnable() {
                            public void run() {
                                count39.setText(data.get(39)+""+data1.get(39));
                            }
                        });

                        count40.post(new Runnable() {
                            public void run() {
                                count40.setText(data.get(40)+""+data1.get(40));
                            }
                        });

                        count41.post(new Runnable() {
                            public void run() {
                                count41.setText(data.get(41)+""+data1.get(41));
                            }
                        });

                        count42.post(new Runnable() {
                            public void run() {
                                count42.setText(data.get(42)+""+data1.get(42));
                            }
                        });

                        count43.post(new Runnable() {
                            public void run() {
                                count43.setText(data.get(43)+""+data1.get(43));
                            }
                        });

                        count44.post(new Runnable() {
                            public void run() {
                                count44.setText(data.get(44)+""+data1.get(44));
                            }
                        });

                        count45.post(new Runnable() {
                            public void run() {
                                count45.setText(data.get(45)+""+data1.get(45));
                            }
                        });

                        count46.post(new Runnable() {
                            public void run() {
                                count46.setText(data.get(46)+""+data1.get(46));
                            }
                        });

                        count47.post(new Runnable() {
                            public void run() {
                                count47.setText(data.get(47)+""+data1.get(47));
                            }
                        });

                        count48.post(new Runnable() {
                            public void run() {
                                count48.setText(data.get(48)+""+data1.get(48));
                            }
                        });

                        count49.post(new Runnable() {
                            public void run() {
                                count49.setText(data.get(49)+""+data1.get(49));
                            }
                        });

                        count50.post(new Runnable() {
                            public void run() {
                                count50.setText(data.get(50)+""+data1.get(50));
                            }
                        });

                        count51.post(new Runnable() {
                            public void run() {
                                count51.setText(data.get(51)+""+data1.get(51));
                            }
                        });

                        count52.post(new Runnable() {
                            public void run() {
                                count52.setText(data.get(52)+""+data1.get(52));
                            }
                        });

                        count53.post(new Runnable() {
                            public void run() {
                                count53.setText(data.get(53)+""+data1.get(53));
                            }
                        });

                        count54.post(new Runnable() {
                            public void run() {
                                count54.setText(data.get(54)+""+data1.get(54));
                            }
                        });

                        count55.post(new Runnable() {
                            public void run() {
                                count55.setText(data.get(55)+""+data1.get(55));
                            }
                        });

                        count56.post(new Runnable() {
                            public void run() {
                                count56.setText(data.get(56)+""+data1.get(56));
                            }
                        });

                        count57.post(new Runnable() {
                            public void run() {
                                count57.setText(data.get(57)+""+data1.get(57));
                            }
                        });

                        count58.post(new Runnable() {
                            public void run() {
                                count58.setText(data.get(58)+""+data1.get(58));
                            }
                        });

                        count59.post(new Runnable() {
                            public void run() {
                                count59.setText(data.get(59)+""+data1.get(59));
                            }
                        });

                        count60.post(new Runnable() {
                            public void run() {
                                count60.setText(data.get(60)+""+data1.get(60));
                            }
                        });

                        count61.post(new Runnable() {
                            public void run() {
                                count61.setText(data.get(61)+""+data1.get(61));
                            }
                        });

                        count62.post(new Runnable() {
                            public void run() {
                                count62.setText(data.get(62)+""+data1.get(62));
                            }
                        });

                        count63.post(new Runnable() {
                            public void run() {
                                count63.setText(data.get(63)+""+data1.get(63));
                            }
                        });

                        count64.post(new Runnable() {
                            public void run() {
                                count64.setText(data.get(64)+""+data1.get(64));
                            }
                        });

                        count65.post(new Runnable() {
                            public void run() {
                                count65.setText(data.get(65)+""+data1.get(65));
                            }
                        });

                        count66.post(new Runnable() {
                            public void run() {
                                count66.setText(data.get(66)+""+data1.get(66));
                            }
                        });

                        count67.post(new Runnable() {
                            public void run() {
                                count67.setText(data.get(67)+""+data1.get(67));
                            }
                        });

                        count68.post(new Runnable() {
                            public void run() {
                                count68.setText(data.get(68)+""+data1.get(68));
                            }
                        });

                        count69.post(new Runnable() {
                            public void run() {
                                count69.setText(data.get(69)+""+data1.get(69));
                            }
                        });

                        count70.post(new Runnable() {
                            public void run() {
                                count70.setText(data.get(70)+""+data1.get(70));
                            }
                        });

                        count71.post(new Runnable() {
                            public void run() {
                                count71.setText(data.get(71)+""+data1.get(71));
                            }
                        });

                        count72.post(new Runnable() {
                            public void run() {
                                count72.setText(data.get(72)+""+data1.get(72));
                            }
                        });

                        count73.post(new Runnable() {
                            public void run() {
                                count73.setText(data.get(73)+""+data1.get(73));
                            }
                        });

                        count74.post(new Runnable() {
                            public void run() {
                                count74.setText(data.get(74)+""+data1.get(74));
                            }
                        });

                        count75.post(new Runnable() {
                            public void run() {
                                count75.setText(data.get(75)+""+data1.get(75));
                            }
                        });

                        count76.post(new Runnable() {
                            public void run() {
                                count76.setText(data.get(76)+""+data1.get(76));
                            }
                        });

                        count77.post(new Runnable() {
                            public void run() {
                                count77.setText(data.get(77)+""+data1.get(77));
                            }
                        });

                        count78.post(new Runnable() {
                            public void run() {
                                count78.setText(data.get(78)+""+data1.get(78));
                            }
                        });

                        count79.post(new Runnable() {
                            public void run() {
                                count79.setText(data.get(79)+""+data1.get(79));
                            }
                        });

                        count80.post(new Runnable() {
                            public void run() {
                                count80.setText(data.get(80)+""+data1.get(80));
                            }
                        });

                        count81.post(new Runnable() {
                            public void run() {
                                count81.setText(data.get(81)+""+data1.get(81));
                            }
                        });

                        count82.post(new Runnable() {
                            public void run() {
                                count82.setText(data.get(82)+""+data1.get(82));
                            }
                        });

                        count83.post(new Runnable() {
                            public void run() {
                                count83.setText(data.get(83)+""+data1.get(83));
                            }
                        });

                        count84.post(new Runnable() {
                            public void run() {
                                count84.setText(data.get(84)+""+data1.get(84));
                            }
                        });

                        count85.post(new Runnable() {
                            public void run() {
                                count85.setText(data.get(85)+""+data1.get(85));
                            }
                        });

                        count86.post(new Runnable() {
                            public void run() {
                                count86.setText(data.get(86)+""+data1.get(86));
                            }
                        });

                        count87.post(new Runnable() {
                            public void run() {
                                count87.setText(data.get(87)+""+data1.get(87));
                            }
                        });

                        count88.post(new Runnable() {
                            public void run() {
                                count88.setText(data.get(88)+""+data1.get(88));
                            }
                        });

                        count89.post(new Runnable() {
                            public void run() {
                                count89.setText(data.get(89)+""+data1.get(89));
                            }
                        });

                        count90.post(new Runnable() {
                            public void run() {
                                count90.setText(data.get(90)+""+data1.get(90));
                            }
                        });
                    }
                }).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected","沒有選擇內容");
            }
        });
    }
}

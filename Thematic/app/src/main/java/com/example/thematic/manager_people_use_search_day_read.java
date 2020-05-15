package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class manager_people_use_search_day_read extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_people_use_search_day_read);
        getSupportActionBar().hide(); //隱藏標題

        final TextView count1 = (TextView)findViewById(R.id.區間一人數);
        final TextView count2 = (TextView)findViewById(R.id.區間二人數);
        final TextView count3 = (TextView)findViewById(R.id.區間三人數);
        final TextView count4 = (TextView)findViewById(R.id.區間四人數);
        final TextView count5 = (TextView)findViewById(R.id.區間五人數);
        final TextView count6 = (TextView)findViewById(R.id.區間六人數);
        final TextView count7 = (TextView)findViewById(R.id.區間七人數);
        final TextView count8 = (TextView)findViewById(R.id.區間八人數);
        final TextView count9 = (TextView)findViewById(R.id.區間九人數);
        final TextView count10 = (TextView)findViewById(R.id.區間十人數);
        final TextView count11 = (TextView)findViewById(R.id.區間十一人數);
        final TextView count12 = (TextView)findViewById(R.id.區間十二人數);
        final TextView count13 = (TextView)findViewById(R.id.區間十三人數);
        final TextView count14 = (TextView)findViewById(R.id.區間十四人數);
        final TextView count15 = (TextView)findViewById(R.id.區間十五人數);
        final TextView count16 = (TextView)findViewById(R.id.區間十六人數);
        final TextView count17 = (TextView)findViewById(R.id.區間十七人數);
        final TextView count18 = (TextView)findViewById(R.id.區間十八人數);
        new Thread(new Runnable() {
            @Override
            public void run() {
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                String Date = obj.returnPeopleUseRead();
                ArrayList data = new ArrayList();
                ArrayList data1 = new ArrayList();
                for( int i = 0 ; i < 19 ; i++){
                    data.add("");
                    data1.add("無資料");
                }
                con.get_people_use(data,data1,""+0,Date,"我要單日的Empty");
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
                    public void run() {
                        count16.setText(data.get(16)+""+data1.get(16));
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

            }
        }).start();
    }
}

package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Dynamic_button extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout lineLayout = new LinearLayout(this);
        lineLayout.setOrientation(LinearLayout.VERTICAL);
        lineLayout.setLayoutParams(params);
        lineLayout.setGravity(Gravity.TOP );
        addView(lineLayout);
        setContentView(lineLayout);

    }

    private void addView(final LinearLayout lineLayout)
    {
        final TextView showText = new TextView(this);
        showText.setTextColor(Color.GREEN);
        showText.setTextSize(30);
        showText.setId(10001);//設定 id
        showText.setText("我是在程式中新增的第一個文字");
        showText.setBackgroundColor(Color.GRAY);
        // set 文字大小
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //set 四周距離
        params.setMargins(10, 10, 10, 10);

        showText.setLayoutParams(params);

        //新增文字到主佈局
        lineLayout.addView(showText );

        //建立按鈕
        Button btn = new Button(this);
        btn.setText("點選刪除文字");
        btn.setBackgroundColor(Color.GRAY) ;
        LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btn_params.setMargins(0, 60, 60, 0);
        btn_params.gravity = Gravity.CENTER_HORIZONTAL;
        btn.setLayoutParams(btn_params);
        // 動態新增按鈕到主佈局
        lineLayout.addView(btn);

        //點選按鈕 動態刪除文字
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(null != lineLayout.findViewById(10001))
                {
                    lineLayout.removeView(showText);
                }
                else
                {
                    Toast.makeText(Dynamic_button.this, "文字已被刪除", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //動態建立一個相對佈局
        RelativeLayout relaLayout = new RelativeLayout(this);
        relaLayout.setBackgroundColor(Color.BLUE);
        RelativeLayout.LayoutParams lp11 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 120);


        relaLayout.setLayoutParams(lp11);
        //動態建立一個文字
        final TextView RelaText = new TextView(this);
        RelaText.setTextColor(Color.GREEN);
        RelaText.setTextSize(20);
        RelaText.setText("我是在程式中新增的第二個文字，在相對佈局中");
        RelaText.setBackgroundColor(Color.GRAY);

        //設定文字的佈局
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lp2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        lp2.setMargins(10, 10, 10, 10);
        //將文字新增到相對佈局中
        relaLayout.addView(RelaText,lp2);
        //將這個佈局新增到主佈局中
        lineLayout.addView(relaLayout);

    }
} // class
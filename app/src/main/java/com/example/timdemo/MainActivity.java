package com.example.timdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button BTN;
    private Button BTN1;
    private TextView TV1;
    private TextView TV2;
    private TextView TV3;
    private TextView TV4;
    private TextView TV5;
    private TextView TV6;
    private TextView TV7;

    private TextView MyNum;
    ArrayList<Integer> MyNumList = new ArrayList<Integer>();

    private TextView Result;

    private int nn[] = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTN = findViewById(R.id.BTN);
        BTN.setOnClickListener(this);
        TV1 = findViewById(R.id.TV1);
        //TV1.setOnClickListener(this);
        TV2 = findViewById(R.id.TV2);
        //TV2.setOnClickListener(this);
        TV3 = findViewById(R.id.TV3);
        //TV3.setOnClickListener(this);
        TV4 = findViewById(R.id.TV4);
        //TV4.setOnClickListener(this);
        TV5 = findViewById(R.id.TV5);
        //TV5.setOnClickListener(this);
        TV6 = findViewById(R.id.TV6);
        //TV6.setOnClickListener(this);
        TV7 = findViewById(R.id.TV7);
        //TV7.setOnClickListener(this);

        BTN1 = findViewById(R.id.BTN1);
        BTN1.setOnClickListener(this);

        MyNumList.add(R.id.MyNum1);
        MyNumList.add(R.id.MyNum2);
        MyNumList.add(R.id.MyNum3);
        MyNumList.add(R.id.MyNum4);
        MyNumList.add(R.id.MyNum5);
        MyNumList.add(R.id.MyNum6);

        Result = findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.BTN1) {
            SetNULL();//清空
            int Mynum[] = new int[6];
            //電腦選號塞值
            for (int i = 0; i < Mynum.length; i++) {
                int RdNum2 = (int) (Math.random() * 49);

                //比對重複
                for (int s : Mynum) {
                    if (s == RdNum2) RdNum2 = (int) (Math.random() * 49);
                }
                Mynum[i] = RdNum2;
            }
            Arrays.sort(Mynum);//呼叫庫中的sort方法(排序)

            nn = Mynum;//扔給全域陣列nn去對獎

            for (int i = 0; i < MyNumList.size(); i++) {
                MyNum = findViewById(MyNumList.get(i));
                MyNum.setText(String.valueOf(Mynum[i]));
            }
        }

        //開獎7碼字串
        if (id == R.id.BTN) {
            int num[] = new int[7];
            //開獎塞值
            for (int i = 0; i < num.length; i++) {
                int RdNum = (int) (Math.random() * 49);

                //比對重複
                for (int s : num) {
                    if (s == RdNum) RdNum = (int) (Math.random() * 49);
                }
                num[i] = RdNum;
            }

            int specialNum = num[6]; //最後一個為特別號
            num = Arrays.copyOf(num, num.length - 1);

            Arrays.sort(num);//呼叫庫中的sort方法(排序)

            TV1.setText(String.valueOf(num[0]));
            TV2.setText(String.valueOf(num[1]));
            TV3.setText(String.valueOf(num[2]));
            TV4.setText(String.valueOf(num[3]));
            TV5.setText(String.valueOf(num[4]));
            TV6.setText(String.valueOf(num[5]));
            TV7.setText(String.valueOf(specialNum));
            TV7.setTextColor(Color.BLUE);

            if (nn[0] == 0) Result.setText("尚未選號。");
            else Result.setText(PairAward(num, nn, specialNum));
        }
    }

    //對獎
    private String PairAward(int[] x, int[] y, int spec) {
        String Result = "";
        int OK = 0;
        boolean SP = false;

        for (int i = 0; i < x.length; i++) {
            int a = y[i];

            //比對
            for (int s : x) {
                if (s == a) OK++;

                if (a == spec) SP = true;
            }
        }

        switch (OK) {
            case 6:
                Result = "恭喜你中頭獎！！！";
                break;
            case 5:
                if (SP) Result = "恭喜你中貳獎！！！";
                else Result = "恭喜你中參獎！！！";
                break;
            case 4:
                if (SP) Result = "恭喜你中肆獎！！！";
                else Result = "恭喜你中伍獎！！！";
                break;
            case 3:
                if (SP) Result = "恭喜你中陸獎！！！";
                else Result = "恭喜你中普獎！！！";
                break;
            case 2:
                if (SP) Result = "恭喜你中柒獎！！！";
                else Result = "摃龜！！！";
                break;
            default:
                Result = "摃龜！！！";
                break;
        }


        return Result;
    }
    //清空
    public void SetNULL() {

        TV1.setText("*");
        TV2.setText("*");
        TV3.setText("*");
        TV4.setText("*");
        TV5.setText("*");
        TV6.setText("*");
        TV7.setText("*");
        Result.setText("");
    }
}

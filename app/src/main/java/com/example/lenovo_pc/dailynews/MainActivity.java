package com.example.lenovo_pc.dailynews;

import android.app.Activity;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private ListView lv;
    private Handler handler;
    private HomeFragment h1;
    private MarkFragment h2;
    private SelectFragment h3;
    private SimpleAdapter adapter1;
  //  private HomeFragment cur;
    public static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr=sdf.format(today);
        SharedPreferences sharedPreferences = getSharedPreferences("date", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("today_date",todayStr);
        editor.putString("resource","新浪网");
        editor.commit();
        h1 = HomeFragment.newInstance();
        h2 = MarkFragment.newInstance();
        h3 = SelectFragment.newInstance();
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b1.setBackgroundResource(R.drawable.shape2);
        init();
    }
//    private void showFragment(HomeFragment frag){
//        fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        if(cur!=frag){
//            transaction.hide(cur);
//            cur=frag;
//            if (!frag.isAdded()){
//                transaction.add(R.id.fragment_container,frag).commit();
//                transaction.show(frag);
//            }
//            else{
//                transaction.show(frag).commit();
//            }
//        }
//    }
    private void init() {
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        if (!h1.isAdded()) {
            transaction.add(R.id.fragment_container, h1).commit();
            transaction.show(h1);
        }else {
            transaction.show(h1).commit();
        }
    }

    private void init2() {
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        if (!h2.isAdded()) {
            transaction.add(R.id.fragment_container, h2).commit();
            transaction.show(h2);
        }else {
            transaction.show(h2).commit();
        }
    }

    private void init3() {
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        if (!h3.isAdded()) {
            transaction.add(R.id.fragment_container, h3).commit();
            transaction.show(h3);
        }else {
            transaction.show(h3).commit();
        }
    }

    public void onClick(View btn) {
        b1.setBackgroundResource(R.drawable.shape3);
        b2.setBackgroundResource(R.drawable.shape3);
        b3.setBackgroundResource(R.drawable.shape3);
        String tag="";
        switch (btn.getId()){
            case R.id.btn1:
                Log.i(tag,"按钮1");
                b1.setBackgroundResource(R.drawable.shape2);
                init();
               // showFragment(h1);
                break;
            case R.id.btn2:
                Log.i(tag,"按钮2");
                b2.setBackgroundResource(R.drawable.shape2);
                init2();
              //  showFragment(h2);
                break;
            case R.id.btn3:
                Log.i(tag,"按钮3");
                b3.setBackgroundResource(R.drawable.shape2);
                init3();
              //  showFragment(h3);
                break;
        }
    }
    private void hideFragment(FragmentTransaction transaction){
        String tag1="";
        if(h1 != null){
            Log.i(tag1,"隐藏1");
            transaction.hide(h1);
        }
        if(h2 != null){
            Log.i(tag1,"隐藏2");
            transaction.hide(h2);
        }
        if(h3 != null){
            Log.i(tag1,"隐藏3");
            transaction.hide(h3);
        }
    }

}

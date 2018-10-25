package com.example.lenovo_pc.dailynews;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.LineNumberInputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InternationalNews extends AppCompatActivity implements Runnable {
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    private LayoutInflater inflater;
    //   private int msgWhat = 7;
    private Handler handler;
    private TextView tv;
    private ListView listView;
    private String data[]={"正在加载中......  "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.originalpage);
        tv=(TextView)findViewById(R.id.title_page);
        tv.setText("国际新闻");
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        int msgWhat = 3;
        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 5){
                    List<String> retList = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(InternationalNews.this,R.layout.support_simple_spinner_dropdown_item,retList);
                    listView.setAdapter(adapter);
                    Log.i("handler","reset list...");
                }
                super.handleMessage(msg);
            }
        };
    }
    public void back(View btn){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    @Override
    public void run() {//线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("thread","run.....");
        List<String> rateList = new ArrayList<String>();
        try {
            Document doc = Jsoup.connect("http://www.usd-cny.com/icbc.htm").get();
            Elements tbs = doc.getElementsByClass("tableDataTable");
            Element table = tbs.get(0);
            Elements tds = table.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i+=5) {
                Element td = tds.get(i);
                Element td2 = tds.get(i+3);
                String tdStr = td.text();
                String pStr = td2.text();
                rateList.add(tdStr + "=>" + pStr);
                Log.i("td",tdStr + "=>" + pStr);
            }
        } catch (MalformedURLException e) {
            Log.e("www", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("www", e.toString());
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(5);
        msg.obj = rateList;
        handler.sendMessage(msg);
        Log.i("thread","sendMessage.....");
    }
    }

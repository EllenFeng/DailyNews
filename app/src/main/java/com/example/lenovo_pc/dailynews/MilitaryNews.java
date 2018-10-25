package com.example.lenovo_pc.dailynews;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MilitaryNews extends ListActivity implements Runnable {
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    private LayoutInflater inflater;
 //   private int msgWhat = 7;
    private Handler handler;
    private String data[]={"正在加载中......  "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int msgWhat = 3;
        ListAdapter adapter = new ArrayAdapter<String>(MilitaryNews.this,android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 5){
                    List<String> retList = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(MilitaryNews.this,R.layout.support_simple_spinner_dropdown_item,retList);
                    setListAdapter(adapter);
                    Log.i("handler","reset list...");
                }
                super.handleMessage(msg);
            }
        };
    }
    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "Rate： " + i); // 标题文字
            map.put("ItemOrigin", "Origin" + i); // 来源描述
            listItems.add(map);
        }
        // 生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
                R.layout.list_item, // ListItem的XML布局实现
                new String[]{"ItemTitle", "ItemOrigin"},
                new int[]{R.id.itemTitle, R.id.itemOrigin}
        );

    }
    @Override
    public void run() {//线程
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

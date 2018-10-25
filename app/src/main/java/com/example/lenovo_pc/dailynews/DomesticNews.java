package com.example.lenovo_pc.dailynews;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DomesticNews extends AppCompatActivity implements Runnable{
    Handler handler;
    private TextView tv;
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private String data[]={"正在加载中......  "};
    private SimpleAdapter listItemAdapter;
    private LayoutInflater inflater;
    private int msgWhat = 7;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.originalpage);
        tv=(TextView)findViewById(R.id.title_page);
        tv.setText("国内新闻");
//        NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.list_item, listItems);
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

 //       initListView();
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        Thread t = new Thread(this); // 创建新线程
        t.start(); // 开启线程
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == msgWhat){
                    List<HashMap<String, String>> retList = (List<HashMap<String, String>>) msg.obj;
                    SimpleAdapter adapter1 = new SimpleAdapter(DomesticNews.this, retList, // listItems数据源
                            R.layout.list_item, // ListItem的XML布局实现
                            new String[] { "ItemTitle", "ItemOrigin" },
                            new int[] { R.id.itemTitle, R.id.itemOrigin });
                    listView.setAdapter(adapter1);
                    Log.i("handler","reset list...");
                }
                super.handleMessage(msg);
            }
        };
    }

//
//    private void initListView() {
//        listItems = new ArrayList<HashMap<String, String>>();
//        for (int i = 0; i < 10; i++) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("ItemTitle", "Rate： " + i); // 标题文字
//            map.put("ItemOrigin", "Origin" + i); // 来源描述
//            listItems.add(map);
//        }
//        // 生成适配器的Item和动态数组对应的元素
//        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
//                R.layout.list_item, // ListItem的XML布局实现
//                new String[]{"ItemTitle", "ItemOrigin"},
//                new int[]{R.id.itemTitle, R.id.itemOrigin}
//        );
//
//
//    }
    public void back(View btn){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    @Override
    public void run() {
        Log.i("thread","run.....");
        boolean marker = false;
        List<HashMap<String, String>> rateList = new ArrayList<HashMap<String, String>>();

        try {
            Thread.sleep(3000);
            Document doc = Jsoup.connect("http://www.usd-cny.com/icbc.htm").get();
            Elements tbs = doc.getElementsByClass("tableDataTable");
            Element table = tbs.get(0);
            Elements tds = table.getElementsByTag("td");
            for (int i = 6; i < tds.size(); i+=6) {
                Element td = tds.get(i);
                Element td2 = tds.get(i+3);
                String tdStr = td.text();
                String pStr = td2.text();

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemTitle", tdStr);
                map.put("ItemOrigin", pStr);

                rateList.add(map);
                Log.i("td",tdStr + "=>" + pStr);
            }
            marker = true;
        } catch (MalformedURLException e) {
            Log.e("www", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("www", e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage();
        msg.what = msgWhat;
        if(marker){
            msg.arg1 = 1;
        }else{
            msg.arg1 = 0;
        }

        msg.obj = rateList;
        handler.sendMessage(msg);

        Log.i("thread","sendMessage.....");

    }

}

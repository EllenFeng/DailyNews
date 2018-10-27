package com.example.lenovo_pc.dailynews;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsContent extends AppCompatActivity implements Runnable{
    private String title;
    private String link;
    private String originaldate;  //新闻发布日期
    private String todayStr;  //当前日期，用于收藏时使用
    private TextView tv,tv2;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        title = getIntent().getStringExtra("title");
        link = getIntent().getStringExtra("link");
        originaldate = getIntent().getStringExtra("date");  //新闻发布日期
        tv=(TextView)findViewById(R.id.newstitle);
        tv2=(TextView)findViewById(R.id.newscontent);
        tv.setText(title);
        int msgWhat = 3;
        Thread t = new Thread(NewsContent.this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 5){
//                    List<String> content = (List<String>) msg.obj;
//                    tv2.setText((CharSequence) content);
                    String str = (String) msg.obj;
                    if(str.length()<1)
                        tv2.setText("哎呀，你要找的文章不见啦！");
                    else
                        tv2.setText(str);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void mark_news(View btn){
        SharedPreferences sharedPreferences = getSharedPreferences("date", Activity.MODE_PRIVATE);
        todayStr = sharedPreferences.getString("today_date","");
        NewsItem item1=new NewsItem(title,link,originaldate,todayStr);
        DBManager_detail manager=new DBManager_detail(this);
    //    manager.deleteAll();
        if(!manager.isExisted(title)) {  //如果没有收藏过
            manager.add(item1);
            Toast.makeText(this,"收藏成功！",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"已经在收藏夹啦！",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void run() {
        Log.i("thread","run.....");
//        List<String> constr = new ArrayList<String>();
        String content1="";
        Message msg = handler.obtainMessage(5);
        try {
            Document doc = Jsoup.connect(link).get();
            Elements content = doc.select(".article p");
            for (int i = 0; i < content.size(); i+=1) {
                Element newsCon = content.get(i);
                String contentStr = newsCon.text()+"\n";
                if(contentStr.length()<1)
                    continue;
//                constr.add(contentStr);  //添加正文
                content1=content1+contentStr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.obj=content1;
 //       msg.obj=constr;
        handler.sendMessage(msg);
    }
}

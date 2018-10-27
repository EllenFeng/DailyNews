package com.example.lenovo_pc.dailynews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager_detail {
    private DBHelper dbHelper;
    private String TBNAME;

    public DBManager_detail(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(NewsItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("newstitle", item.getNewsTitle());
        values.put("link", item.getLink());
        values.put("originaldate", item.getOriginalDate());
        values.put("markdate", item.getMarkDate());
        db.insert(TBNAME, null, values);
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void delete(String title){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] title1={title};
        String news="NEWSTITLE";
        db.delete(TBNAME, news+"=?", title1);
        db.close();
    }
    public boolean isExisted(String title){
        String[] title1={title};
        String news="NEWSTITLE";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,news+"=?",title1,null,null,null,null);
        if(cursor.getCount()==0)  //没有添加过这个新闻
            return false;
        else
            return true;
    }
    public List<NewsItem> listAll(){
        List<NewsItem> newsdetail = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            newsdetail = new ArrayList<NewsItem>();
            while(cursor.moveToNext()){
                NewsItem item = new NewsItem();
                item.setNewsTitle(cursor.getString(cursor.getColumnIndex("NEWSTITLE")));
                item.setLink(cursor.getString(cursor.getColumnIndex("LINK")));
                item.setOriginalDate(cursor.getString(cursor.getColumnIndex("ORIGINALDATE")));
                item.setMarkDate(cursor.getString(cursor.getColumnIndex("MARKDATE")));
                newsdetail.add(item);
            }
            cursor.close();
        }
        db.close();
        return newsdetail;

    }

}

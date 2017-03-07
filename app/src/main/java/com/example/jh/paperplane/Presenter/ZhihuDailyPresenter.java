package com.example.jh.paperplane.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.VolleyError;
import com.example.jh.paperplane.bean.BeanType;
import com.example.jh.paperplane.bean.StringModelImpl;
import com.example.jh.paperplane.interfaces.ZhihuDailyContract;
import com.example.jh.paperplane.bean.ZhihuDailyNews;
import com.example.jh.paperplane.db.DatabaseHelper;
import com.example.jh.paperplane.activity.DetailActivity;
import com.example.jh.paperplane.interfaces.OnStringListener;
import com.example.jh.paperplane.service.CacheService;
import com.example.jh.paperplane.util.Api;
import com.example.jh.paperplane.util.DateFormatter;
import com.example.jh.paperplane.util.NetworkState;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    private ZhihuDailyContract.View view;
    private Context context;
    private StringModelImpl model;

    private DateFormatter formatter = new DateFormatter();
    private Gson gson = new Gson();

    private ArrayList<ZhihuDailyNews.Question> list = new ArrayList<ZhihuDailyNews.Question>();

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public ZhihuDailyPresenter(Context context, ZhihuDailyContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        model = new StringModelImpl(context);
        dbHelper = new DatabaseHelper(context, "History.db", null, 5);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void loadPosts(long date, final boolean clearing) {

        if (clearing) {
            view.showLoading();
        }

        if (NetworkState.networkConnected(context)) {

            model.load(Api.ZHIHU_HISTORY + formatter.ZhihuDailyDateFormat(date), new OnStringListener() {
                @Override
                public void onSuccess(String result) {

                    try {
                        ZhihuDailyNews post = gson.fromJson(result, ZhihuDailyNews.class);
                        ContentValues values = new ContentValues();

                        if (clearing) {
                            list.clear();
                        }

                        for (ZhihuDailyNews.Question item : post.getStories()) {
                            list.add(item);
                            if ( !queryIfIDExists(item.getId())) {
                                db.beginTransaction();
                                try {
                                    DateFormat format = new SimpleDateFormat("yyyyMMdd");
                                    Date date = format.parse(post.getDate());
                                    values.put("zhihu_id", item.getId());
                                    values.put("zhihu_news", gson.toJson(item));
                                    values.put("zhihu_content", "");
                                    values.put("zhihu_time", date.getTime() / 1000);
                                    db.insert("Zhihu", null, values);
                                    values.clear();
                                    db.setTransactionSuccessful();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    db.endTransaction();
                                }

                            }
                            Intent intent = new Intent("com.marktony.zhihudaily.LOCAL_BROADCAST");
                            intent.putExtra("type", CacheService.TYPE_ZHIHU);
                            intent.putExtra("id", item.getId());
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                        }
                        view.showResults(list);
                    } catch (JsonSyntaxException e) {
                        view.showError();
                    }

                    view.stopLoading();
                }

                @Override
                public void onError(VolleyError error) {
                    view.stopLoading();
                    view.showError();
                }
            });
        } else {

            if (clearing) {

                list.clear();

                Cursor cursor = db.query("Zhihu", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        ZhihuDailyNews.Question question = gson.fromJson(cursor.getString(cursor.getColumnIndex("zhihu_news")), ZhihuDailyNews.Question.class);
                        list.add(question);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                view.stopLoading();
                view.showResults(list);

            } else {
                view.showError();
            }

        }
    }

    @Override
    public void refresh() {
        loadPosts(Calendar.getInstance().getTimeInMillis(), true);
    }

    @Override
    public void loadMore(long date) {
        loadPosts(date, false);
    }

    @Override
    public void startReading(int position) {

        context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("type", BeanType.TYPE_ZHIHU)
                .putExtra("id", list.get(position).getId())
                .putExtra("title", list.get(position).getTitle())
                .putExtra("coverUrl", list.get(position).getImages().get(0)));

    }

    @Override
    public void feelLucky() {
        if (list.isEmpty()) {
            view.showError();
            return;
        }
        startReading(new Random().nextInt(list.size()));
    }

    @Override
    public void start() {
        loadPosts(Calendar.getInstance().getTimeInMillis(), true);
    }

    private boolean queryIfIDExists(int id){

        Cursor cursor = db.query("Zhihu",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                if (id == cursor.getInt(cursor.getColumnIndex("zhihu_id"))){
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return false;
    }

}

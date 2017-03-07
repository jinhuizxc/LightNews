package com.example.jh.paperplane.Presenter;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jh.paperplane.bean.BeanType;
import com.example.jh.paperplane.bean.DoubanMomentNews;
import com.example.jh.paperplane.bean.GuokrHandpickNews;
import com.example.jh.paperplane.interfaces.SearchContract;
import com.example.jh.paperplane.bean.ZhihuDailyNews;
import com.example.jh.paperplane.db.DatabaseHelper;
import com.example.jh.paperplane.activity.DetailActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.jh.paperplane.adapter.BookmarksAdapter.TYPE_DOUBAN_NORMAL;
import static com.example.jh.paperplane.adapter.BookmarksAdapter.TYPE_DOUBAN_WITH_HEADER;
import static com.example.jh.paperplane.adapter.BookmarksAdapter.TYPE_GUOKR_NORMAL;
import static com.example.jh.paperplane.adapter.BookmarksAdapter.TYPE_GUOKR_WITH_HEADER;
import static com.example.jh.paperplane.adapter.BookmarksAdapter.TYPE_ZHIHU_NORMAL;
import static com.example.jh.paperplane.adapter.BookmarksAdapter.TYPE_ZHIHU_WITH_HEADER;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */
public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private Context context;
    private Gson gson;

    private ArrayList<DoubanMomentNews.posts> doubanList;
    private ArrayList<GuokrHandpickNews.result> guokrList;
    private ArrayList<ZhihuDailyNews.Question> zhihuList;

    private ArrayList<Integer> types;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public SearchPresenter(Context context, SearchContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        gson = new Gson();
        dbHelper = new DatabaseHelper(context, "History.db", null, 5);
        db = dbHelper.getWritableDatabase();

        zhihuList = new ArrayList<>();
        guokrList = new ArrayList<>();
        doubanList = new ArrayList<>();

        types = new ArrayList<>();

    }

    @Override
    public void start() {

    }

    @Override
    public void loadResults(String queryWords) {

        zhihuList.clear();
        guokrList.clear();
        doubanList.clear();
        types.clear();

        types.add(TYPE_ZHIHU_WITH_HEADER);
        Cursor cursor = db.rawQuery("select * from Zhihu where bookmark = 1 and zhihu_news like '%" + queryWords + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                ZhihuDailyNews.Question question = gson.fromJson(cursor.getString(cursor.getColumnIndex("zhihu_news")), ZhihuDailyNews.Question.class);
                zhihuList.add(question);
                types.add(TYPE_ZHIHU_NORMAL);
            } while (cursor.moveToNext());
        }

        types.add(TYPE_GUOKR_WITH_HEADER);
        cursor = db.rawQuery("select * from Guokr where bookmark = 1 and guokr_news like '%" + queryWords + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                GuokrHandpickNews.result result = gson.fromJson(cursor.getString(cursor.getColumnIndex("guokr_news")), GuokrHandpickNews.result.class);
                guokrList.add(result);
                types.add(TYPE_GUOKR_NORMAL);
            } while (cursor.moveToNext());
        }

        types.add(TYPE_DOUBAN_WITH_HEADER);
        cursor = db.rawQuery("select * from Douban where bookmark = 1 and douban_news like '%" + queryWords + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                DoubanMomentNews.posts post = gson.fromJson(cursor.getString(cursor.getColumnIndex("douban_news")), DoubanMomentNews.posts.class);
                doubanList.add(post);
                types.add(TYPE_DOUBAN_NORMAL);
            } while (cursor.moveToNext());
        }

        cursor.close();

        view.showResults(zhihuList, guokrList, doubanList, types);

    }

    @Override
    public void startReading(BeanType type, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        switch (type) {
            case TYPE_ZHIHU:
                ZhihuDailyNews.Question q = zhihuList.get(position - 1);
                intent.putExtra("type", BeanType.TYPE_ZHIHU);
                intent.putExtra("id",q.getId());
                intent.putExtra("title", q.getTitle());
                intent.putExtra("coverUrl", q.getImages().get(0));
                break;

            case TYPE_GUOKR:
                GuokrHandpickNews.result r = guokrList.get(position - zhihuList.size() - 2);
                intent.putExtra("type", BeanType.TYPE_GUOKR);
                intent.putExtra("id", r.getId());
                intent.putExtra("title", r.getTitle());
                intent.putExtra("coverUrl", r.getHeadline_img());
                break;
            case TYPE_DOUBAN:
                DoubanMomentNews.posts p = doubanList.get(position - zhihuList.size() - guokrList.size() - 3);
                intent.putExtra("type", BeanType.TYPE_DOUBAN);
                intent.putExtra("id", p.getId());
                intent.putExtra("title", p.getTitle());
                if (p.getThumbs().size() == 0){
                    intent.putExtra("coverUrl", "");
                } else {
                    intent.putExtra("image", p.getThumbs().get(0).getMedium().getUrl());
                }
                break;
            default:
                break;
        }
        context.startActivity(intent);
    }

}

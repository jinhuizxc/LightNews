package com.example.jh.paperplane.interfaces;



import com.example.jh.paperplane.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface ZhihuDailyContract {

    interface View extends BaseView<Presenter> {

        void showError();

        void showLoading();

        void stopLoading();

        void showResults(ArrayList<ZhihuDailyNews.Question> list);

    }

    interface Presenter extends BasePresenter {

        void loadPosts(long date, boolean clearing);

        void refresh();

        void loadMore(long date);

        void startReading(int position);

        void feelLucky();

    }

}

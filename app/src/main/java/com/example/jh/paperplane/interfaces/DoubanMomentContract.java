package com.example.jh.paperplane.interfaces;


import com.example.jh.paperplane.interfaces.BasePresenter;
import com.example.jh.paperplane.interfaces.BaseView;
import com.example.jh.paperplane.bean.DoubanMomentNews;

import java.util.ArrayList;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface DoubanMomentContract {

    interface View extends BaseView<Presenter> {

        void startLoading();

        void stopLoading();

        void showLoadingError();

        void showResults(ArrayList<DoubanMomentNews.posts> list);

    }

    interface Presenter extends BasePresenter {

        void startReading(int position);

        void loadPosts(long date, boolean clearing);

        void refresh();

        void loadMore(long date);

        void feelLucky();

    }

}

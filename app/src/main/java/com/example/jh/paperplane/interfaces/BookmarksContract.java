package com.example.jh.paperplane.interfaces;


import com.example.jh.paperplane.bean.BeanType;
import com.example.jh.paperplane.bean.DoubanMomentNews;
import com.example.jh.paperplane.bean.GuokrHandpickNews;
import com.example.jh.paperplane.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface BookmarksContract {

    interface View extends BaseView<Presenter> {

        void showResults(ArrayList<ZhihuDailyNews.Question> zhihuList,
                         ArrayList<GuokrHandpickNews.result> guokrList,
                         ArrayList<DoubanMomentNews.posts> doubanList,
                         ArrayList<Integer> types);

        void notifyDataChanged();

        void showLoading();

        void stopLoading();

    }

    interface Presenter extends BasePresenter {

        void loadResults(boolean refresh);

        void startReading(BeanType type, int position);

        void checkForFreshData();

        void feelLucky();

    }

}

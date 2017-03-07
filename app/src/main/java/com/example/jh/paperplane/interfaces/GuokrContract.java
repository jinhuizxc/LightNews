package com.example.jh.paperplane.interfaces;


import com.example.jh.paperplane.interfaces.BasePresenter;
import com.example.jh.paperplane.interfaces.BaseView;
import com.example.jh.paperplane.bean.GuokrHandpickNews;

import java.util.ArrayList;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface GuokrContract {

    interface View extends BaseView<Presenter> {

        void showError();

        void showResults(ArrayList<GuokrHandpickNews.result> list);

        void showLoading();

        void stopLoading();

    }

    interface Presenter extends BasePresenter {

        void loadPosts();

        void refresh();

        void startReading(int position);

        void feelLucky();

    }

}

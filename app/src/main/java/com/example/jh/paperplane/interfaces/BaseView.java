package com.example.jh.paperplane.interfaces;

import android.view.View;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface BaseView<T> {

    /**
     * set the presenter of mvp
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * init the views of fragment
     * @param view
     */
    void initViews(View view);

}

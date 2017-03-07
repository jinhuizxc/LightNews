package com.example.jh.paperplane.interfaces;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */
public interface OpenSourceListenContract {

    interface View extends BaseView<Presenter> {

        void loadLicense(String path);

    }

    interface Presenter extends BasePresenter {

    }

}

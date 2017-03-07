package com.example.jh.paperplane.interfaces;


import com.example.jh.paperplane.interfaces.BasePresenter;
import com.example.jh.paperplane.interfaces.BaseView;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface AboutContract {

    interface View extends BaseView<Presenter> {

        void showRateError();
        void showFeedbackError();
        void showBrowserNotFoundError();

    }

    interface Presenter extends BasePresenter {

        void rate();
        void openLicense();
        void followOnGithub();
        void followOnZhihu();
        void feedback();
        void donate();
        void showEasterEgg();

    }

}

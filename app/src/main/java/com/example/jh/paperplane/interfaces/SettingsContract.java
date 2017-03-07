package com.example.jh.paperplane.interfaces;

import android.support.v7.preference.Preference;

import com.example.jh.paperplane.interfaces.BasePresenter;
import com.example.jh.paperplane.interfaces.BaseView;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void showCleanGlideCacheDone();

    }

    interface Presenter extends BasePresenter {

        void setNoPictureMode(Preference preference);

        void setInAppBrowser(Preference preference);

        void cleanGlideCache();

        void setTimeOfSavingArticles(Preference preference, Object newValue);

        String getTimeSummary();

    }

}

package com.example.jh.paperplane.detail;

import android.webkit.WebView;

import com.example.jh.paperplane.interfaces.BasePresenter;
import com.example.jh.paperplane.interfaces.BaseView;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */
public class DetailContract {

    public interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showLoadingError();

        void showSharingError();

        void showResult(String result);

        void showResultWithoutBody(String url);

        void showCover(String url);

        void setTitle(String title);

        void setImageMode(boolean showImage);

        void showBrowserNotFoundError();

        void showTextCopied();

        void showCopyTextError();

        void showAddedToBookmarks();

        void showDeletedFromBookmarks();

    }

    public interface Presenter extends BasePresenter {

        void openInBrowser();

        void shareAsText();

        void openUrl(WebView webView, String url);

        void copyText();

        void copyLink();

        void addToOrDeleteFromBookmarks();

        boolean queryIfIsBookmarked();

        void requestData();

    }

}

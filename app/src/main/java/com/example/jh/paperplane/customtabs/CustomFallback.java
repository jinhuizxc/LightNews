package com.example.jh.paperplane.customtabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.jh.paperplane.activity.InnerBrowserActivity;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public class CustomFallback implements CustomTabActivityHelper.CustomTabFallback {

    @Override
    public void openUri(Activity activity, Uri uri) {
        activity.startActivity(new Intent(activity, InnerBrowserActivity.class).putExtra("url", uri.toString()));
    }

}

package com.example.jh.paperplane.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/*
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 */

public class KeepAliveService extends Service {

    private static final Binder sBinder = new Binder();

    @Override
    public IBinder onBind(Intent intent) {
        return sBinder;
    }

}

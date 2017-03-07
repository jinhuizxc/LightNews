package com.example.jh.paperplane.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.fragment.InnerBrowserFragment;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */


public class InnerBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, InnerBrowserFragment.getInstance()).commit();

    }

}

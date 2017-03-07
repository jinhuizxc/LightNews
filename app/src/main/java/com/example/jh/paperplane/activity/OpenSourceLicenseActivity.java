package com.example.jh.paperplane.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.fragment.OpenSourceLicenseFragment;
/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public class OpenSourceLicenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        OpenSourceLicenseFragment fragment = OpenSourceLicenseFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();

    }

}

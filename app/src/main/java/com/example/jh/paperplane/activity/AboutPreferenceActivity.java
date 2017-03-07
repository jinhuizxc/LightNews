package com.example.jh.paperplane.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.fragment.AboutPreferenceFragment;
import com.example.jh.paperplane.Presenter.AboutPresenter;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public class AboutPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //初始化控件
        initViews();

        AboutPreferenceFragment fragment = new AboutPreferenceFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.about_container,fragment)
                .commit();

        new AboutPresenter(AboutPreferenceActivity.this, fragment);

    }

    private void initViews() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}

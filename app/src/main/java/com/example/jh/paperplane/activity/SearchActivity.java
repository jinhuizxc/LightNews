package com.example.jh.paperplane.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.fragment.SearchFragment;
import com.example.jh.paperplane.Presenter.SearchPresenter;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */
public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        SearchFragment fragment = SearchFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        new SearchPresenter(this, fragment);

    }
}

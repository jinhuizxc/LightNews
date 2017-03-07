package com.example.jh.paperplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.bean.BeanType;
import com.example.jh.paperplane.fragment.DetailFragment;
import com.example.jh.paperplane.Presenter.DetailPresenter;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */


public class DetailActivity extends AppCompatActivity {

    private DetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        if (savedInstanceState != null) {
            fragment = (DetailFragment) getSupportFragmentManager().getFragment(savedInstanceState,"detailFragment");
        } else {
            fragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

        Intent intent = getIntent();

        DetailPresenter presenter = new DetailPresenter(DetailActivity.this, fragment);

        presenter.setType((BeanType) intent.getSerializableExtra("type"));
        presenter.setId(intent.getIntExtra("id", 0));
        presenter.setTitle(intent.getStringExtra("title"));
        presenter.setCoverUrl(intent.getStringExtra("coverUrl"));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "detailFragment", fragment);
        }
    }
}

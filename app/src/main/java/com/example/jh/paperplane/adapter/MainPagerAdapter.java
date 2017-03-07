package com.example.jh.paperplane.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.fragment.DoubanMomentFragment;
import com.example.jh.paperplane.fragment.GuokrFragment;
import com.example.jh.paperplane.fragment.ZhihuDailyFragment;

/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private final Context context;

    private GuokrFragment guokrFragment;
    private ZhihuDailyFragment zhihuFragment;
    private DoubanMomentFragment doubanFragment;

    public GuokrFragment getGuokrFragment() {
        return guokrFragment;
    }

    public ZhihuDailyFragment getZhihuFragment() {
        return zhihuFragment;
    }

    public DoubanMomentFragment getDoubanFragment() {
        return doubanFragment;
    }

    public MainPagerAdapter(FragmentManager fm,
                            Context context,
                            ZhihuDailyFragment zhihuDailyFragment,
                            GuokrFragment guokrFragment,
                            DoubanMomentFragment doubanMomentFragment) {
        super(fm);
        this.context = context;
        titles = new String[] {
                context.getResources().getString(R.string.zhihu_daily),
                context.getResources().getString(R.string.guokr_handpick),
                context.getResources().getString(R.string.douban_moment)
        };

        this.zhihuFragment = zhihuDailyFragment;
        this.guokrFragment = guokrFragment;
        this.doubanFragment = doubanMomentFragment;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return guokrFragment;
        } else if (position == 2){
            return doubanFragment;
        }

        return zhihuFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}

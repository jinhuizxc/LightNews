package com.example.jh.paperplane.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.jh.paperplane.R;
import com.example.jh.paperplane.activity.OpenSourceLicenseActivity;
import com.example.jh.paperplane.interfaces.OpenSourceListenContract;


/**
 * 作者：jinhui on 2017/3/6
 * 邮箱：1004260403@qq.com
 *
 */

public class OpenSourceLicenseFragment extends Fragment
        implements OpenSourceListenContract.View {

    private WebView webView;

    public OpenSourceLicenseFragment() {
        // requires an empty constructor
    }

    public static OpenSourceLicenseFragment newInstance() {
        return new OpenSourceLicenseFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_source_license, container, false);

        initViews(view);
        setHasOptionsMenu(true);

        loadLicense("file:///android_asset/license.html");

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return true;
    }

    @Override
    public void setPresenter(OpenSourceListenContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {
        AppCompatActivity activity = (OpenSourceLicenseActivity) getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) view.findViewById(R.id.web_view);
    }

    @Override
    public void loadLicense(String path) {
        webView.loadUrl(path);
    }

}

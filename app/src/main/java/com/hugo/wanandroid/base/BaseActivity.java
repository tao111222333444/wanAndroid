package com.hugo.wanandroid.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hugo.wanandroid.App;
import com.hugo.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 基类activity
 * @author 29794
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {


    /***
     * 黄油刀
     */
    private Unbinder unBinder;

    private BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);



        initTitle();
        initView();
        mActivity = this;
        //加入activity管理
        App.getAppContext().getActivityControl().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(unBinder != null){
            unBinder.unbind();
            unBinder = null;
        }

        //移除类
        App.getAppContext().getActivityControl().removeActivity(this);
    }




    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled) {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }
    /**
     * 获取布局的
     * @return
     */
    protected abstract @LayoutRes int getLayoutId() ;

    /**
     * 初始化标题
     */
    protected abstract void initTitle();

    /**
     * 初始化数据
     */
    protected abstract void initView();
}

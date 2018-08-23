package com.hugo.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/5.
 * 版本：v1.0
 * 描述：Fragment基类
 */
public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends RxFragment implements BaseContract.BaseView{

    private Unbinder unBinder;
    protected Context mContext;

    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        mContext = getActivity();
        unBinder = ButterKnife.bind(this,view);
        initView(view);
        attachView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unBinder != null){
            unBinder.unbind();
            unBinder = null;
        }
        detachView();
    }

    /**
     * 添加view
     */
    private void attachView(){
        if(mPresenter !=null){
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离View
     */
    private void detachView(){
        if(mPresenter !=null){
            mPresenter.detachView();
        }
    }

    /**
     * 获取布局的
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutId() ;


    /**
     * 初始化数据
     */
    protected abstract void initView(View view);
}

package com.hugo.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/5.
 * 版本：v1.0
 * 描述：Fragment基类
 */
public abstract class BaseFragment extends Fragment{

    private Unbinder unBinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unBinder = ButterKnife.bind(this,view);

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
    }

    /**
     * 获取布局的
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutId() ;

}

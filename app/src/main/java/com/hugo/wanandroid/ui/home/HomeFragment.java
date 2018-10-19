package com.hugo.wanandroid.ui.home;

import android.view.View;

import com.hugo.wanandroid.R;
import com.hugo.wanandroid.base.BaseFragment;

import io.reactivex.ObservableTransformer;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/5.
 * 版本：v1.0
 * 描述：
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeView{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mPresenter = new HomePresenter();
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showError(String msg) {

    }


}

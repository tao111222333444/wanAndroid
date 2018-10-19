package com.hugo.wanandroid.model;

import com.hugo.wanandroid.base.BaseContract;
import com.hugo.wanandroid.base.BaseObserver;
import com.hugo.wanandroid.http.HttpManager;
import com.hugo.wanandroid.http.apiService.RetrofitServise;
import com.hugo.wanandroid.resp.ResponseArticleListData;
import com.hugo.wanandroid.utils.RxUtils;

import io.reactivex.functions.Consumer;

/**
 * @author 作者：hugo
 * @date 时间：2018/8/7.
 * 版本：v1.0
 * 描述：
 */
public class ArticleModel implements BaseContract.BaseModel {


    public void getData(int page, BaseObserver<ResponseArticleListData> onNext){
        HttpManager.getInstance().getServise(RetrofitServise.class)
                .getArticle(page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribe(onNext);

    }

}

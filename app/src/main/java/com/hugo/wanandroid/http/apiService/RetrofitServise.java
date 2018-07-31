package com.hugo.wanandroid.http.apiService;

import com.hugo.wanandroid.base.BaseResponse;
import com.hugo.wanandroid.resp.ResponseArticleListData;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/18.
 * 版本：v1.0
 * 描述：api 接口类
 */
public interface RetrofitServise {

    /**
     * 获取首页文章列表
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ResponseArticleListData>> getArticle(@Path("page") int page);
}

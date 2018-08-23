package com.hugo.wanandroid.base;

import android.text.TextUtils;

import com.hugo.wanandroid.App;
import com.hugo.wanandroid.http.exception.ServerException;
import com.hugo.wanandroid.utils.LogUtil;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @author 作者：hugo
 * @date 时间：2018/8/7.
 * 版本：v1.0
 * 描述：
 */
public abstract class  BaseObserver<T> extends ResourceObserver {
    private static final String TAG = "BaseObserver";

    private BaseContract.BaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(BaseContract.BaseView view){
        this.mView = view;
    }

    protected BaseObserver(BaseContract.BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(BaseContract.BaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(BaseContract.BaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showError(e.toString());
        } else if (e instanceof HttpException) {
//            mView.showError(App.getInstance().getString(R.string.http_error));
        } else {
//            mView.showError(App.getInstance().getString(R.string.unKnown_error));
            LogUtil.d(TAG,e.toString());
        }
        if (isShowError) {
//            mView.showError();
        }
    }

    @Override
    public void onComplete() {

    }
}

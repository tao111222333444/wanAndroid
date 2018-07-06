package com.hugo.wanandroid.base;

import android.support.annotation.UiThread;

/**
 * @author 作者：hugo
 * @date 时间：2018/6/19.
 * 版本：v1.0
 * 描述：
 */
public interface BaseContract {
    /**
     * M的基类
     */
    interface BaseModel {
    }

    /**
     * V的基类
     */
    interface BaseView {

        /**
         * 显示正在加载view
         */
        void showLoading();
        /**
         * 关闭正在加载view
         */
        void hideLoading();
        /**
         * 显示提示
         * @param msg
         */
        void showToast(String msg);
        /**
         * 显示请求错误提示
         */
        void showError(String msg);
    }

    /**
     * P的基类
     * @param <V>  这个类只能是  BaseView的子类
     * @param <M>  这个类只能是  BaseModel的子类
     */
     abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
        protected  V view;
        protected M model;

        public BasePresenter(){
            this.model = createModel();
        }

        /**
         * 给view赋值
         * @param view
         */
        public void attachView(V view){
            this.view = view;
        }

        /**
         * 当view关闭时  置空view
         */
        @UiThread
        public void detachView(){
            if(this.view != null){
                this.view = null;
            }
        }


        /**
         * 是否与View建立连接
         * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
         */
        public boolean isViewAttached(){
            return this.view != null;
        }
        /**
         * 用于创建model
         * @return
         */
        protected abstract M createModel();
    }
}

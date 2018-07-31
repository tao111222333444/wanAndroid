package com.hugo.wanandroid.utils;

import com.hugo.wanandroid.base.BaseResponse;
import com.hugo.wanandroid.http.exception.OtherException;
import com.hugo.wanandroid.http.exception.ServerException;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/19.
 * 版本：v1.0
 * 描述：RxJava 工具类
 */
public class RxUtils {


    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return  FlowableTransformer
     */
    public static <T>FlowableTransformer<T,T> rxFlSchedulerHelper(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T>ObservableTransformer<T,T> rxSchedulerHelper(){
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     *  统一返回结果处理
     * @param <T>  指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>,T> handleResult(){
        return responseObservable ->
                responseObservable.flatMap((Function<BaseResponse<T>,Observable<T>>) baseResponse -> {
                    if(baseResponse.getErrorCode() >= BaseResponse.SUCCESS
                            && baseResponse.getData() != null
                            && Utils.isNetworkConnected()){
                        return  createData(baseResponse.getData());
                    }else{
                        return Observable.error(new ServerException(
                                baseResponse.getErrorMsg()
                                ,baseResponse.getErrorCode()));
                    }
                });
    }

    /**
     * 收藏返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>,T> handleCollectResult(){
        return responseObservable ->
                responseObservable.flatMap((Function<BaseResponse<T>,Observable<T>>) baseResponse -> {
                    if (baseResponse.getErrorCode() >= BaseResponse.SUCCESS
                            && Utils.isNetworkConnected()){
                        //TODO  这是是未完成的
                        return createData(Utils.cast(new Object()));
                    }else{
                        return Observable.error(new OtherException());
                    }
                });
    }

    /**
     * 得到Observable
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T t){
        return  Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }
        });
    }
}

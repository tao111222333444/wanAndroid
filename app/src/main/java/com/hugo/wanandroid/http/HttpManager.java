package com.hugo.wanandroid.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hugo.wanandroid.BuildConfig;
import com.hugo.wanandroid.http.apiService.RetrofitServise;
import com.hugo.wanandroid.utils.Utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/18.
 * 版本：v1.0
 * 描述：http核心类
 */
public class HttpManager {
    private static final String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 用于保存  不同的Api服务  对应不同的Retrofit
     */
    private HashMap<Class, Retrofit> mRetrofitServiceHashMap = new HashMap<>();

    private Retrofit mApiRetrofit;

    /**
     * 用于缓存  api服务
     */
    private ConcurrentHashMap<Class,Object> cachedApis = new ConcurrentHashMap<>();

    /**
     * 获取单例
     * @return  HttpManager
     */
    public static HttpManager getInstance(){
        return HttpManagerInit.INSTANCE;
    }


    private HttpManager(){

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                //设置超时
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true);
        if(BuildConfig.DEBUG){
            //这个只在Debug模式下设置
            //init okhttp 3 logger   okhttp 日志
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            okHttpClient.addInterceptor(loggingInterceptor)
                    //设置facebook Stetho 的网络拦截器
                    .addNetworkInterceptor(new StethoInterceptor());
        }



        mApiRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                // json解析
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //初始化OkHttp
                .client(okHttpClient.build())
                .build();

        //初始化一个
        mRetrofitServiceHashMap.put(RetrofitServise.class,mApiRetrofit);
    }

    /**
     *  设置Retrofit API 服务
     * @param clz
     * @param <T>
     */
    public <T> void addServise(Class<T> clz){addServise(clz,mApiRetrofit);}

    /**
     * 设置api服务   可以自定义一个retrofit
     * @param clz
     * @param retrofit
     * @param <T>
     */
    public <T> void addServise(Class<T> clz,Retrofit retrofit){mRetrofitServiceHashMap.put(clz,retrofit);}

    /**
     * 获取 Retrofit API 服务  如果没有调用 @addServise 添加api服务 是有可能返回null的
     * @param clz
     * @param <T>
     * @return service
     */
    public <T> T getServise(Class<T> clz){
        Object object = cachedApis.get(clz);
        if(object != null){
            return Utils.cast(object);
        } else {
          Retrofit retrofit =   mRetrofitServiceHashMap.get(clz);
            if(retrofit == null ){
                addServise(clz);
            }
            retrofit =   mRetrofitServiceHashMap.get(clz);
          if(retrofit != null ){
              T service = retrofit.create(clz);
              cachedApis.put(clz,service);
              return service;
          }else{
              return null;
          }
        }

    }

    /**
     * 用内部静态类实现单例
     */
    private static  class HttpManagerInit{
        private static final HttpManager  INSTANCE = new HttpManager();
    }

}

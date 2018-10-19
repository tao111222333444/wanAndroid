package com.hugo.wanandroid.ui.home;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.hugo.wanandroid.R;
import com.hugo.wanandroid.base.BaseActivity;
import com.hugo.wanandroid.http.HttpManager;
import com.hugo.wanandroid.http.apiService.RetrofitServise;
import com.hugo.wanandroid.resp.ResponseArticleData;
import com.hugo.wanandroid.utils.RxUtils;
import com.hugo.wanandroid.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 作者：hugo
 * @date 时间：2018/8/3.
 * 版本：v1.0
 * 描述：
 */
public class HomeActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        TextView textView = findViewById(R.id.tv_text);
        List<Integer > aaa = new ArrayList<Integer>();
        for(int i=0;i<100;i++){
            aaa.add( 25);
        }
        Collections.sort(aaa, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                if(integer>=t1){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

//        String str = getString(R.string.activity_home_text);
//        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(str);
//        TextViewSpan1 span1 = new TextViewSpan1();
//        stringBuilder.setSpan(span1,3,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        TextViewSpan2 span2 = new TextViewSpan2();
//        stringBuilder.setSpan(span2,15,18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(stringBuilder);
//        //一定要记得设置这个方法  不是不起作用
//        textView.setMovementMethod(LinkMovementMethod.getInstance());


//        HttpManager.getInstance()
//                .getServise(RetrofitServise.class)
//                .getArticle(1)
//                .compose(RxUtils.rxSchedulerHelper())
//                .compose(RxUtils.handleResult())
//                .compose( bindToLifecycle());


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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post("");
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false,priority = 3)
    public void onMessageEvent(String  aa){

    }

    private class TextViewSpan1 extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.main_nav_bg_start));
            //设置是否有下划线
            ds.setUnderlineText(true);
        }
        @Override
        public void onClick(View widget) {
            //点击事件
            ToastUtil.showToast("这是测试点击1");
        }
    }

    private class TextViewSpan2 extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.main_nav_bg_end));
            //设置是否有下划线
            ds.setUnderlineText(false);
        }
        @Override
        public void onClick(View widget) {
            //点击事件
            ToastUtil.showToast("这是测试点击2");
        }
    }
}

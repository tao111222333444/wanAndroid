package com.hugo.wanandroid.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Message;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hugo.wanandroid.R;
import com.hugo.wanandroid.base.BaseActivity;
import com.hugo.wanandroid.base.BaseContract;
import com.hugo.wanandroid.utils.LogUtil;

import butterknife.BindView;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/5.
 * 版本：v1.0
 * 描述：
 */
public class WebActivity extends BaseActivity implements BaseContract.BaseView{
    public static final String INPUT_URL = "input_url";

    private String url;

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_progress)
    ContentLoadingProgressBar contentLoadingProgressBar;

    public static void show(Context context,String url){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(INPUT_URL,url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initTitle() {
        initToolBar(toolbar,true);
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra(INPUT_URL);
        initWebClient();
        webView.loadUrl(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebClient() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        settings.setBuiltInZoomControls(false);
        // 缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启DOM storage API功能
        settings.setDomStorageEnabled(true);
        // 开启application Cache功能
        settings.setAppCacheEnabled(true);
        // 判断是否为无图模式
        //settings.setBlockNetworkImage(SettingUtil.getInstance().getIsNoPhotoMode());
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //用于判断在Android系统4.3.1~3.0版本,系统webview默认添加了
            // searchBoxJavaBridge_接口,如果未移除该接口可能导致低版本Android系统远程命令执行漏洞  显式调用removeJavascriptInterface方法移除searchBoxJavaBridge_接口
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
        }

        // 不调用第三方浏览器即可进行页面反应
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!TextUtils.isEmpty(url)) {
//                    view.loadUrl(url);
                }
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }
        });

        webView.setOnKeyListener((view, i, keyEvent) -> {
            if ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return false;
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 90) {
                    hideLoading();
                } else {
                    showLoading();
                }
            }
        });

        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                LogUtil.e("aaaa",title);
                toolbar.setTitle(title);
            }


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

        };
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(wvcc);
    }

    @Override
    protected void onDestroy() {
        webView.removeAllViews();
        webView.destroy();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        if(contentLoadingProgressBar != null) {
            contentLoadingProgressBar.show();
        }
    }

    @Override
    public void hideLoading() {
        if(contentLoadingProgressBar != null) {
            contentLoadingProgressBar.hide();
        }
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showError(String msg) {

    }
}

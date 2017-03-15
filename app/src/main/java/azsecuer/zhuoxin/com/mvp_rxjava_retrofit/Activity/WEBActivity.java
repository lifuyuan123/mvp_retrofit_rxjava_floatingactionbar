package azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.R;

public class WEBActivity extends AppCompatActivity {
private WebView webview;
    private Toolbar toolbar;
    private String url;
    private ProgressBar progressBar;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 100) {
                progressBar.setVisibility(View.GONE);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
    }

    private void init() {
        progressBar= (ProgressBar) findViewById(R.id.progress);
        webview= (WebView) findViewById(R.id.web);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("web新闻");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        url = getIntent().getStringExtra("url");
        webview.loadUrl(url);
        webview.loadUrl(url);
        WebSettings webset = webview.getSettings();//获取设置相关
        webset.setUseWideViewPort(true);//设置此属性，可任意比例缩放 //将图片调整到适合webview的大小
        webset.setLoadWithOverviewMode(true);
        //使页面支持缩放
        webset.setJavaScriptEnabled(true);//支持js
        webset.setBuiltInZoomControls(true);
        webset.setSupportZoom(true);//支持缩放
        //如果webView中需要用户手动输入用户名、密码或其他，则webview必须设置支持获取手势焦点。
        webview.requestFocusFromTouch();
        //设置webview的进度progress
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Message message = new Message();
                message.arg1 = newProgress;
                handler.sendMessage(message);
            }
        });

        //帮助WebView处理各种通知、请求事件的：//防止跳到其他页面，强制在webview中打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&webview.canGoBack()){
            webview.goBack();
        }else {
            finish();
        }
        return true;
    }
}


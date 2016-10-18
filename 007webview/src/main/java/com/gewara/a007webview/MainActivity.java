package com.gewara.a007webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private String url = "http://www.360.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = ((WebView) findViewById(R.id.main_wenView));
        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return false;//不响应手机的浏览器
//            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String response;
                if (url.contains("baidu")) {
                    response = "<html>\n" +
                            "<title>千度</title>\n" +
                            "<body>\n" +
                            "<a href=\"www.taobao.com\">淘宝</a>,比百度知道的多10倍\n" +
                            "</body>\n" +
                            "</html>";
                } else {
                    response = "<html>\n" +
                            "<title>360</title>\n" +
                            "<body>\n" +
                            "<a href=\"www.taobao.com\">360</a>,比百度知道的多10倍\n" +
                            "</body>\n" +
                            "</html>";
                }
                WebResourceResponse webResourceResponse = new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(response.getBytes()));
                return webResourceResponse;
            }
        });
        mWebView.loadUrl(url);
    }
}

package com.example.alc4phase1;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AboutALCActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar mLoadingProcess;
    private String url = "https://andela.com/alc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_alc);
        ALCWebControls();

    }

    private void ALCWebControls() {
        mWebView = findViewById(R.id.abt_alc_webview);
        mLoadingProcess = findViewById(R.id.pb_Loading);

        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mLoadingProcess.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                // for SSLErrorHandler
                final AlertDialog.Builder builder = new AlertDialog.Builder(AboutALCActivity.this);
                builder.setMessage(R.string.notification_error_ssl_cert_invalid);
                builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                        finish();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(AboutALCActivity.this, "Sorry :(", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mLoadingProcess.setVisibility(View.INVISIBLE);
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setBuiltInZoomControls(false);

        if (!isNetworkConnected()) {
            mWebView.loadData("<html><body style='text-align:center;'><h1>Connection Error ...</h1><h2>Check Your Connection ... </h2></body></html>", "text/html", null);
        } else {
            Toast.makeText(AboutALCActivity.this, "Loading...please wait", Toast.LENGTH_SHORT).show();
            mWebView.loadUrl(url);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni == null ? false : true;
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}

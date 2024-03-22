package com.example.todo;

    import android.annotation.TargetApi;
    import android.app.Activity;
    import android.content.Context;
    import android.os.Build;
    import android.os.Bundle;
    import android.view.KeyEvent;
    import android.webkit.WebResourceRequest;
    import android.webkit.WebSettings;
    import android.webkit.WebView;
    import android.webkit.WebViewClient;
    import android.net.Uri;
    import android.content.Intent;
  
    
    public class MainActivity extends Activity {
    
        private WebView webView;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
    
    
    
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    
    
    
            webView = (WebView)findViewById(R.id.web);
            webView.addJavascriptInterface(new LocalStorageInterface(this), "localStorage");
    WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            // webSettings.setDatabaseEnabled(true);
            // String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
            // webSettings.setDatabasePath(databasePath);
            webSettings.setDomStorageEnabled(true);
    
    
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // return false;
  if (url.startsWith("file:///android_asset/") || url.startsWith("http://") || url.startsWith("https://") && !url.endsWith(".apk")) {
      return false;
  } else {
      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                      startActivity(i);
                      return true;
  }
                }
    
                // @TargetApi(Build.VERSION_CODES.N)
                /*
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }
                */
            });
    
            webView.loadUrl("file:///android_asset/index.html");
        }
    
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        if (webView.canGoBack()) {
                            webView.goBack();
                        } else {
                            finish();
                        }
                        return true;
                }
    
            }
            return super.onKeyDown(keyCode, event);
        }
    }
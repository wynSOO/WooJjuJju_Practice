package app.com.godata_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.web_view_page);

        // JS 사용 허용
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        webView.setInitialScale(100);  // 100%
        webView.setBackgroundColor(0);

        // 확대 축소를 할 수 있도록 허용
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

//        ViewAdapter에서 보내준 링크를 문자열로 받기
        String url = getIntent().getStringExtra("HTML");
        webView.loadUrl(url);
    }
}

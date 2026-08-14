package tr.kelimetahtasi.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
    private WebView webView;
    private FrameLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // Sistem çubuklarının kapladığı alanı ana kapsayıcıdan düşürür.
        // Böylece WebView'in gerçek ölçüsü yalnızca kullanılabilir alan olur;
        // HTML'deki 100dvh yüksekliğine fazladan boşluk eklenmez.
        rootLayout = new FrameLayout(this);
        rootLayout.setOnApplyWindowInsetsListener((view, windowInsets) -> {
            int topInset;
            int bottomInset;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                android.graphics.Insets systemBars = windowInsets.getInsets(
                        WindowInsets.Type.systemBars()
                );
                topInset = systemBars.top;
                bottomInset = systemBars.bottom;
            } else {
                topInset = windowInsets.getSystemWindowInsetTop();
                bottomInset = windowInsets.getSystemWindowInsetBottom();
            }
            view.setPadding(0, topInset, 0, bottomInset);
            return windowInsets;
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        rootLayout.addView(webView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        setContentView(rootLayout);
        rootLayout.requestApplyInsets();
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        boolean isVolumeKey = keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
                || keyCode == KeyEvent.KEYCODE_VOLUME_UP;

        if (isVolumeKey) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                // Ses kısma: ileri, ses açma: geri.
                int direction = keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ? 1 : -1;
                webView.evaluateJavascript(
                        "if (typeof go === 'function') { go(" + direction + "); }",
                        null
                );
            }
            // Tuşun sistem sesini değiştirmesini engeller.
            return true;
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}

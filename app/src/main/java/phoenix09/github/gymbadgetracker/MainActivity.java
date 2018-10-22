package phoenix09.github.gymbadgetracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

/*
    App icon
    https://pixabay.com/en/shield-badge-logo-symbol-label-308943/
    +
    https://pixabay.com/en/pokemon-pokeball-pokemon-go-1536849/
    Both licensed CC0
 */

public class MainActivity extends AppCompatActivity {
    private static WebView webview;

    private void reload() {
        webview.loadUrl("https://nhellfire.github.io/gym-badge-tracker/");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        reload();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset: {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Are you sure?");
                alert.setMessage("This will erase your badge levels and can not be undone.");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        webview.evaluateJavascript("localStorage.clear()", null);
                        reload();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.show();
                break;
            }
            case R.id.reload: {
                reload();
                break;
            }
        }
        return false;
    }
}

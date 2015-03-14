package com.etb.sway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by ortal on 08-Mar-15.
 */
public class SplashScreen extends Activity{
    private static int SPLASH_SCREEN_DELAY = 1000;

    TextView percent;

    long init;

    private ProgressBar progressBar;

    private long progressStatus = 0;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_splash_screen);
        percent = (TextView) findViewById(R.id.textView2);
        progressBar = (ProgressBar)  findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            public void run() {
                init = System.currentTimeMillis();
                while (progressStatus < 100) {
                    progressStatus = (System.currentTimeMillis() - init) * 100
                            / SPLASH_SCREEN_DELAY;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress((int) progressStatus);
                            percent.setText(progressStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LocationChooserActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }
}

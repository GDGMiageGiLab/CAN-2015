package com.gdg.miagegi.can2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class SplashscreenActivity extends Activity {

    private static final int STOPSPLASH = 0;
    private static final long SPLASHTIME = 4000;
    private final transient Handler splashHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == STOPSPLASH)
            {
                final Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
        final Message msg = new Message();
        msg.what = STOPSPLASH;
        splashHandler.sendMessageDelayed(msg, SPLASHTIME);
    }

}

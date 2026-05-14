package com.astrocomm.comm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ROOT
        FrameLayout root =
                new FrameLayout(this);

        root.setBackgroundColor(
                Color.parseColor("#050816"));

        // STAR
        TextView star =
                new TextView(this);

        star.setText("✦");

        star.setTextSize(110);

        star.setTextColor(
                Color.parseColor("#8B5CFF"));

        // CENTER
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER;

        star.setLayoutParams(params);

        // ANIMATION
        AlphaAnimation pulse =
                new AlphaAnimation(0.4f, 1f);

        pulse.setDuration(1000);

        pulse.setRepeatMode(
                AlphaAnimation.REVERSE);

        pulse.setRepeatCount(
                AlphaAnimation.INFINITE);

        star.startAnimation(pulse);

        root.addView(star);

        setContentView(root);

        // CHECK ACCOUNT

        new Handler().postDelayed(() -> {

            SharedPreferences prefs =
                    getSharedPreferences(
                            "AstraComm",
                            MODE_PRIVATE);

            boolean registered =
                    prefs.getBoolean(
                            "registered",
                            false);

            Intent intent;

            if (registered) {

                intent =
                        new Intent(
                                SplashActivity.this,
                                MainActivity.class);

            } else {

                intent =
                        new Intent(
                                SplashActivity.this,
                                RegisterActivity.class);

            }

            startActivity(intent);

            finish();

        }, 1600);

    }

}
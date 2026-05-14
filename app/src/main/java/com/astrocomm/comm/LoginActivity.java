package com.astrocomm.comm;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (
                UserSession.isLogged(this)
        ) {

            startActivity(
                    new Intent(
                            this,
                            MainActivity.class));

        } else {

            startActivity(
                    new Intent(
                            this,
                            RegisterActivity.class));

        }

        finish();

    }

}
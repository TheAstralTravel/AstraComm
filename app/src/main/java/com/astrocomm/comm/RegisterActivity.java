package com.astrocomm.comm;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

import android.os.Bundle;

import android.view.Gravity;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import org.json.JSONObject;

import java.net.URI;

public class RegisterActivity extends Activity {

    private WebSocketClient socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ===== CONNECT =====

        connectSocket();

        // ===== ROOT =====

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL);

        root.setBackgroundColor(
                Color.parseColor("#050816"));

        root.setPadding(
                40,
                80,
                40,
                40);

        // ===== TITLE =====

        TextView title =
                new TextView(this);

        title.setText("Create account");

        title.setTextColor(Color.WHITE);

        title.setTextSize(30);

        title.setTypeface(
                null,
                Typeface.BOLD);

        root.addView(title);

        // ===== USERNAME =====

        EditText username =
                new EditText(this);

        username.setHint("Username");

        styleInput(username);

        root.addView(username);

        // ===== PASSWORD =====

        EditText password =
                new EditText(this);

        password.setHint("Password");

        styleInput(password);

        root.addView(password);

        // ===== BUTTON =====

        TextView register =
                new TextView(this);

        register.setText("REGISTER");

        register.setGravity(Gravity.CENTER);

        register.setTextColor(Color.WHITE);

        register.setTextSize(18);

        register.setTypeface(
                null,
                Typeface.BOLD);

        register.setPadding(
                0,
                28,
                0,
                28);

        GradientDrawable btn =
                new GradientDrawable();

        btn.setColor(
                Color.parseColor("#7C3AED"));

        btn.setCornerRadius(100);

        register.setBackground(btn);

        LinearLayout.LayoutParams btnParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        btnParams.setMargins(
                0,
                40,
                0,
                0);

        register.setLayoutParams(btnParams);

        root.addView(register);

        // ===== CLICK =====

        register.setOnClickListener(v -> {

            try {

                String user =
                        username.getText()
                                .toString()
                                .trim();

                JSONObject json =
                        new JSONObject();

                json.put(
                        "type",
                        "register");

                json.put(
                        "username",
                        user);

                socket.send(
                        json.toString());

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

        setContentView(root);

    }

    // ===== SOCKET =====

    private void connectSocket() {

        try {

            socket =
                    new WebSocketClient(

                            new URI(
                                    "wss://computers-screensaver-humanity-kerry.trycloudflare.com")

                    ) {

                        @Override
                        public void onOpen(
                                ServerHandshake handshakedata
                        ) {

                            System.out.println(
                                    "Connected");

                        }

                        @Override
                        public void onMessage(
                                String message
                        ) {

                            runOnUiThread(() -> {

                                try {

                                    JSONObject json =
                                            new JSONObject(
                                                    message);

                                    String type =
                                            json.getString(
                                                    "type");

                                    // ===== REGISTERED =====

                                    if (
                                            type.equals(
                                                    "registered")
                                    ) {

                                        String id =
                                                json.getString(
                                                        "id");

                                        // ===== SAVE =====

                                        UserSession.save(
                                                RegisterActivity.this,
                                                id,
                                                "User"
                                        );

                                        // ===== OPEN =====

                                        startActivity(
                                                new Intent(
                                                        RegisterActivity.this,
                                                        MainActivity.class));

                                        finish();

                                    }

                                } catch (Exception e) {

                                    e.printStackTrace();

                                }

                            });

                        }

                        @Override
                        public void onClose(
                                int code,
                                String reason,
                                boolean remote
                        ) {

                        }

                        @Override
                        public void onError(
                                Exception ex
                        ) {

                            ex.printStackTrace();

                        }

                    };

            socket.connect();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ===== STYLE =====

    private void styleInput(
            EditText input
    ) {

        input.setTextColor(Color.WHITE);

        input.setHintTextColor(
                Color.parseColor("#7E8496"));

        input.setPadding(
                30,
                24,
                30,
                24);

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(
                Color.parseColor("#111827"));

        bg.setCornerRadius(100);

        input.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(
                0,
                30,
                0,
                0);

        input.setLayoutParams(params);

    }

}
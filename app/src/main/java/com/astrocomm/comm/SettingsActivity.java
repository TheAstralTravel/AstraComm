package com.astrocomm.comm;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

import android.os.Bundle;

import android.view.Gravity;

import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL);

        root.setBackgroundColor(
                Color.parseColor("#050816"));

        root.setPadding(
                35,
                60,
                35,
                35);

        // ===== TOP =====

        LinearLayout top =
                new LinearLayout(this);

        top.setOrientation(
                LinearLayout.HORIZONTAL);

        top.setGravity(
                Gravity.CENTER_VERTICAL);

        // ===== BACK =====

        TextView back =
                new TextView(this);

        back.setText("←");

        back.setTextColor(Color.WHITE);

        back.setTextSize(26);

        back.setPadding(
                0,
                0,
                30,
                0);

        back.setOnClickListener(v -> finish());

        top.addView(back);

        // ===== TITLE =====

        TextView title =
                new TextView(this);

        title.setText("Settings");

        title.setTextColor(Color.WHITE);

        title.setTypeface(
                null,
                Typeface.BOLD);

        title.setTextSize(28);

        top.addView(title);

        root.addView(top);

        // ===== PROFILE CARD =====

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL);

        card.setPadding(
                35,
                35,
                35,
                35);

        GradientDrawable cardBg =
                new GradientDrawable();

        cardBg.setColor(
                Color.parseColor("#111827"));

        cardBg.setCornerRadius(45);

        card.setBackground(cardBg);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        cardParams.setMargins(
                0,
                50,
                0,
                0);

        card.setLayoutParams(cardParams);

        // ===== USERNAME =====

        TextView username =
                new TextView(this);

        username.setText(
                UserSession.getUsername(this));

        username.setTextColor(Color.WHITE);

        username.setTextSize(24);

        username.setTypeface(
                null,
                Typeface.BOLD);

        card.addView(username);

        // ===== ID =====

        TextView id =
                new TextView(this);

        id.setText(
                "("
                +
                UserSession.getId(this)
                +
                ")");

        id.setTextColor(
                Color.parseColor("#8B93A7"));

        id.setTextSize(18);

        id.setPadding(
                0,
                10,
                0,
                0);

        card.addView(id);

        root.addView(card);

        // ===== LOGOUT =====

        TextView logout =
                new TextView(this);

        logout.setText("LOG OUT");

        logout.setTextColor(Color.WHITE);

        logout.setGravity(Gravity.CENTER);

        logout.setTextSize(18);

        logout.setTypeface(
                null,
                Typeface.BOLD);

        logout.setPadding(
                0,
                28,
                0,
                28);

        GradientDrawable logoutBg =
                new GradientDrawable();

        logoutBg.setColor(
                Color.parseColor("#7C3AED"));

        logoutBg.setCornerRadius(100);

        logout.setBackground(logoutBg);

        LinearLayout.LayoutParams logoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        logoutParams.setMargins(
                0,
                50,
                0,
                0);

        logout.setLayoutParams(logoutParams);

        logout.setOnClickListener(v -> {

            UserSession.logout(this);

            Intent intent =
                    new Intent(
                            this,
                            LoginActivity.class);

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                    |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);

        });

        root.addView(logout);

        setContentView(root);

    }

}
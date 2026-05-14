package com.astrocomm.comm;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

import android.os.Bundle;

import android.view.Gravity;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ===== ROOT =====

        LinearLayout main =
                new LinearLayout(this);

        main.setOrientation(
                LinearLayout.VERTICAL);

        main.setBackgroundColor(
                Color.parseColor("#050816"));

        main.setPadding(
                35,
                60,
                35,
                30);

        // ===== TOP =====

        LinearLayout top =
                new LinearLayout(this);

        top.setOrientation(
                LinearLayout.HORIZONTAL);

        top.setGravity(
                Gravity.CENTER_VERTICAL);

        // ===== TITLE =====

        TextView title =
                new TextView(this);

        title.setText("AstraComm");

        title.setTextColor(Color.WHITE);

        title.setTextSize(30);

        title.setTypeface(
                null,
                Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        titleParams.weight = 1;

        top.addView(title, titleParams);

        // ===== SETTINGS =====

        TextView settings =
                new TextView(this);

        settings.setText("☰");

        settings.setTextSize(24);

        settings.setTextColor(Color.WHITE);

        settings.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            this,
                            SettingsActivity.class));

        });

        top.addView(settings);

        main.addView(top);

        // ===== SEARCH =====

        TextView search =
                new TextView(this);

        search.setText("Search ⌕");

        search.setTextSize(18);

        search.setTextColor(
                Color.parseColor("#7E8496"));

        search.setPadding(
                35,
                25,
                35,
                25);

        GradientDrawable searchBg =
                new GradientDrawable();

        searchBg.setColor(
                Color.parseColor("#111827"));

        searchBg.setCornerRadius(100);

        search.setBackground(searchBg);

        LinearLayout.LayoutParams searchParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        searchParams.setMargins(
                0,
                40,
                0,
                35);

        search.setLayoutParams(searchParams);

        search.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            this,
                            SearchActivity.class));

        });

        main.addView(search);

        // ===== SCROLL =====

        ScrollView scroll =
                new ScrollView(this);

        LinearLayout chats =
                new LinearLayout(this);

        chats.setOrientation(
                LinearLayout.VERTICAL);

        scroll.addView(chats);

        // ===== SYSTEM LOG CHAT =====

        LinearLayout chat =
                new LinearLayout(this);

        chat.setOrientation(
                LinearLayout.HORIZONTAL);

        chat.setGravity(
                Gravity.CENTER_VERTICAL);

        chat.setPadding(
                30,
                30,
                30,
                30);

        GradientDrawable chatBg =
                new GradientDrawable();

        chatBg.setColor(
                Color.parseColor("#111827"));

        chatBg.setCornerRadius(45);

        chat.setBackground(chatBg);

        // ===== AVATAR =====

        TextView avatar =
                new TextView(this);

        avatar.setText("✦");

        avatar.setTextColor(Color.WHITE);

        avatar.setTextSize(26);

        avatar.setGravity(Gravity.CENTER);

        GradientDrawable avatarBg =
                new GradientDrawable();

        avatarBg.setColor(
                Color.parseColor("#6D4AFF"));

        avatarBg.setShape(
                GradientDrawable.OVAL);

        avatar.setBackground(avatarBg);

        avatar.setWidth(130);

        avatar.setHeight(130);

        chat.addView(avatar);

        // ===== TEXT =====

        LinearLayout textBlock =
                new LinearLayout(this);

        textBlock.setOrientation(
                LinearLayout.VERTICAL);

        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        textParams.weight = 1;

        textParams.setMargins(
                30,
                0,
                0,
                0);

        textBlock.setLayoutParams(textParams);

        // ===== NAME ROW =====

        LinearLayout row =
                new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL);

        TextView name =
                new TextView(this);

        name.setText("SystemLog ");

        name.setTextColor(Color.WHITE);

        name.setTypeface(
                null,
                Typeface.BOLD);

        name.setTextSize(22);

        row.addView(name);

        TextView id =
                new TextView(this);

        id.setText("(0001)");

        id.setTextColor(
                Color.parseColor("#8B93A7"));

        id.setTextSize(18);

        row.addView(id);

        textBlock.addView(row);

        // ===== LAST =====

        TextView last =
                new TextView(this);

        last.setText(
                "Tap to open chat");

        last.setTextColor(
                Color.parseColor("#8B93A7"));

        last.setTextSize(16);

        last.setPadding(
                0,
                12,
                0,
                0);

        textBlock.addView(last);

        chat.addView(textBlock);

        // ===== TIME =====

        TextView time =
                new TextView(this);

        time.setText("now");

        time.setTextColor(
                Color.parseColor("#8B93A7"));

        time.setTextSize(15);

        chat.addView(time);

        // ===== OPEN CHAT =====

        chat.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            MainActivity.this,
                            ChatActivity.class));

        });

        chats.addView(chat);

        main.addView(scroll);

        setContentView(main);

    }

}
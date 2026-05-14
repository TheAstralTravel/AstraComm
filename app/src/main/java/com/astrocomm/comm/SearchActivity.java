package com.astrocomm.comm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SearchActivity extends Activity {

    LinearLayout resultsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ===== MAIN =====

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
                35);

        // ===== TOP =====

        LinearLayout top =
                new LinearLayout(this);

        top.setOrientation(
                LinearLayout.HORIZONTAL);

        top.setGravity(
                Gravity.CENTER_VERTICAL);

        // BACK

        TextView back =
                new TextView(this);

        back.setText("←");

        back.setTextColor(Color.WHITE);

        back.setTextSize(28);

        back.setPadding(
                0,
                0,
                30,
                0);

        back.setOnClickListener(v -> {

            finish();

        });

        top.addView(back);

        // TITLE

        TextView title =
                new TextView(this);

        title.setText("Search");

        title.setTextColor(Color.WHITE);

        title.setTextSize(28);

        title.setTypeface(
                null,
                Typeface.BOLD);

        top.addView(title);

        main.addView(top);

        // ===== INPUT =====

        EditText input =
                new EditText(this);

        input.setHint("Username or ID");

        input.setHintTextColor(
                Color.parseColor("#7E8496"));

        input.setTextColor(Color.WHITE);

        input.setTextSize(17);

        input.setPadding(
                35,
                28,
                35,
                28);

        GradientDrawable inputBg =
                new GradientDrawable();

        inputBg.setColor(
                Color.parseColor("#111827"));

        inputBg.setCornerRadius(100);

        input.setBackground(inputBg);

        LinearLayout.LayoutParams inputParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        inputParams.setMargins(
                0,
                40,
                0,
                35);

        input.setLayoutParams(inputParams);

        main.addView(input);

        // ===== RESULTS =====

        ScrollView scroll =
                new ScrollView(this);

        resultsLayout =
                new LinearLayout(this);

        resultsLayout.setOrientation(
                LinearLayout.VERTICAL);

        scroll.addView(resultsLayout);

        main.addView(scroll);

        setContentView(main);

        // ===== SEARCH =====

        input.addTextChangedListener(
                new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count) {

                search(
                        s.toString()
                                .trim());

            }

            @Override
            public void afterTextChanged(
                    Editable s) {

            }

        });

    }

    // ===== SEARCH =====

    private void search(String query) {

        resultsLayout.removeAllViews();

        if (query.isEmpty()) {

            return;

        }

        SharedPreferences prefs =
                getSharedPreferences(
                        "AstraComm",
                        MODE_PRIVATE);

        String username =
                prefs.getString(
                        "username",
                        "");

        String id =
                prefs.getString(
                        "id",
                        "");

        // SYSTEMLOG НЕЛЬЗЯ НАЙТИ

        if (query.equalsIgnoreCase(
                "systemlog")) {

            return;

        }

        boolean match =
                username.toLowerCase()
                        .contains(
                                query.toLowerCase())
                        ||
                        id.contains(query);

        if (match) {

            LinearLayout card =
                    createUserCard(
                            username,
                            id);

            card.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SearchActivity.this,
                                ChatActivity.class);

                intent.putExtra(
                        "name",
                        username);

                intent.putExtra(
                        "id",
                        id);

                startActivity(intent);

            });

            resultsLayout.addView(card);

        }

    }

    // ===== USER CARD =====

    private LinearLayout createUserCard(
            String username,
            String id) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.HORIZONTAL);

        card.setGravity(
                Gravity.CENTER_VERTICAL);

        card.setPadding(
                30,
                30,
                30,
                30);

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(
                Color.parseColor("#111827"));

        bg.setCornerRadius(45);

        card.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(
                0,
                0,
                0,
                25);

        card.setLayoutParams(params);

        // ===== AVATAR =====

        TextView avatar =
                new TextView(this);

        avatar.setText("✦");

        avatar.setTextColor(Color.WHITE);

        avatar.setTextSize(24);

        avatar.setGravity(Gravity.CENTER);

        GradientDrawable avatarBg =
                new GradientDrawable();

        avatarBg.setColor(
                Color.parseColor("#6D4AFF"));

        avatarBg.setShape(
                GradientDrawable.OVAL);

        avatar.setBackground(avatarBg);

        avatar.setWidth(110);

        avatar.setHeight(110);

        card.addView(avatar);

        // ===== INFO =====

        LinearLayout info =
                new LinearLayout(this);

        info.setOrientation(
                LinearLayout.VERTICAL);

        info.setPadding(
                30,
                0,
                0,
                0);

        // USERNAME

        TextView name =
                new TextView(this);

        name.setText(username);

        name.setTextColor(Color.WHITE);

        name.setTextSize(20);

        name.setTypeface(
                null,
                Typeface.BOLD);

        info.addView(name);

        // ID

        TextView userId =
                new TextView(this);

        userId.setText(
                "ID: " + id);

        userId.setTextColor(
                Color.parseColor("#8B93A7"));

        userId.setTextSize(15);

        userId.setPadding(
                0,
                10,
                0,
                0);

        info.addView(userId);

        card.addView(info);

        return card;

    }

}
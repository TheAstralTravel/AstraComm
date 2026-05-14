package com.astrocomm.comm;

import android.app.Activity;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

import android.os.Bundle;

import android.text.format.DateFormat;

import android.view.Gravity;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends Activity {

    // ===== UI =====

    private LinearLayout messagesLayout;

    private ScrollView scroll;

    private EditText input;

    // ===== STORAGE =====

    private final ArrayList<Message> messages =
            new ArrayList<>();

    // ===== MODEL =====

    private static class Message {

        String text;

        boolean mine;

        String time;

        String senderId;

        boolean delivered;

        Message(
                String text,
                boolean mine,
                String time,
                String senderId,
                boolean delivered
        ) {

            this.text = text;

            this.mine = mine;

            this.time = time;

            this.senderId = senderId;

            this.delivered = delivered;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ===== CONNECT =====

        AstraSocket.connect(

                "wss://computers-screensaver-humanity-kerry.trycloudflare.com",

                message -> runOnUiThread(() -> {

                    receiveMessage(
                            message,
                            "remote");

                })

        );

        // ===== ROOT =====

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL);

        root.setBackgroundColor(
                Color.parseColor("#050816"));

        // ===== TOP =====

        LinearLayout top =
                new LinearLayout(this);

        top.setOrientation(
                LinearLayout.HORIZONTAL);

        top.setGravity(
                Gravity.CENTER_VERTICAL);

        top.setPadding(
                35,
                60,
                35,
                30);

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

        // ===== AVATAR =====

        TextView avatar =
                new TextView(this);

        avatar.setText("✦");

        avatar.setTextColor(Color.WHITE);

        avatar.setTextSize(22);

        avatar.setGravity(Gravity.CENTER);

        GradientDrawable avatarBg =
                new GradientDrawable();

        avatarBg.setShape(
                GradientDrawable.OVAL);

        avatarBg.setColor(
                Color.parseColor("#7C3AED"));

        avatar.setBackground(avatarBg);

        avatar.setWidth(95);

        avatar.setHeight(95);

        top.addView(avatar);

        // ===== INFO =====

        LinearLayout info =
                new LinearLayout(this);

        info.setOrientation(
                LinearLayout.VERTICAL);

        LinearLayout.LayoutParams infoParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        infoParams.setMargins(
                22,
                0,
                0,
                0);

        info.setLayoutParams(infoParams);

        // ===== NAME =====

        TextView name =
                new TextView(this);

        String username =
        UserSession.getUsername(this);

String userId =
        UserSession.getId(this);

name.setText(
        username
        +
        " ("
        +
        userId
        +
        ")");

        name.setTextColor(Color.WHITE);

        name.setTypeface(
                null,
                Typeface.BOLD);

        name.setTextSize(20);

        info.addView(name);

        // ===== STATUS =====

        TextView status =
                new TextView(this);

        status.setText("online");

        status.setTextColor(
                Color.parseColor("#8B93A7"));

        status.setTextSize(13);

        info.addView(status);

        top.addView(info);

        root.addView(top);

        // ===== SCROLL =====

        scroll =
                new ScrollView(this);

        LinearLayout.LayoutParams scrollParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0);

        scrollParams.weight = 1;

        scroll.setLayoutParams(scrollParams);

        // ===== MESSAGE AREA =====

        messagesLayout =
                new LinearLayout(this);

        messagesLayout.setOrientation(
                LinearLayout.VERTICAL);

        messagesLayout.setPadding(
                16,
                10,
                16,
                10);

        scroll.addView(messagesLayout);

        root.addView(scroll);

        // ===== INPUT BAR =====

        LinearLayout bottom =
                new LinearLayout(this);

        bottom.setOrientation(
                LinearLayout.HORIZONTAL);

        bottom.setGravity(
                Gravity.CENTER_VERTICAL);

        bottom.setPadding(
                18,
                10,
                18,
                22);

        // ===== INPUT =====

        input =
                new EditText(this);

        input.setHint("Message");

        input.setHintTextColor(
                Color.parseColor("#7E8496"));

        input.setTextColor(Color.WHITE);

        input.setTextSize(15);

        input.setPadding(
                28,
                18,
                28,
                18);

        GradientDrawable inputBg =
                new GradientDrawable();

        inputBg.setColor(
                Color.parseColor("#111827"));

        inputBg.setCornerRadius(100);

        input.setBackground(inputBg);

        LinearLayout.LayoutParams inputParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        inputParams.weight = 1;

        input.setLayoutParams(inputParams);

        bottom.addView(input);

        // ===== SEND =====

        TextView send =
                new TextView(this);

        send.setText("➤");

        send.setTextColor(Color.WHITE);

        send.setTextSize(18);

        send.setGravity(Gravity.CENTER);

        GradientDrawable sendBg =
                new GradientDrawable();

        sendBg.setShape(
                GradientDrawable.OVAL);

        sendBg.setColor(
                Color.parseColor("#7C3AED"));

        send.setBackground(sendBg);

        send.setWidth(85);

        send.setHeight(85);

        LinearLayout.LayoutParams sendParams =
                new LinearLayout.LayoutParams(
                        85,
                        85);

        sendParams.setMargins(
                14,
                0,
                0,
                0);

        send.setLayoutParams(sendParams);

        bottom.addView(send);

        root.addView(bottom);

        // ===== LOAD =====

        loadMessages();

        // ===== SEND CLICK =====

        send.setOnClickListener(v -> {

            String text =
                    input.getText()
                            .toString()
                            .trim();

            if (text.isEmpty()) {
                return;
            }

            sendMessage(text);

            input.setText("");

        });

        setContentView(root);

    }

    // ===== SEND =====

    private void sendMessage(String text) {

        String currentTime =
                DateFormat.format(
                        "HH:mm",
                        new Date())
                        .toString();

        Message msg =
                new Message(
                        text,
                        true,
                        currentTime,
                        "self",
                        false
                );

        messages.add(msg);

        drawMessage(msg);

        saveMessages();

        // ===== ONLINE SEND =====

        AstraSocket.send(text);

    }

    // ===== RECEIVE =====

    private void receiveMessage(
            String text,
            String senderId
    ) {

        String currentTime =
                DateFormat.format(
                        "HH:mm",
                        new Date())
                        .toString();

        Message msg =
                new Message(
                        text,
                        false,
                        currentTime,
                        senderId,
                        true
                );

        messages.add(msg);

        drawMessage(msg);

        saveMessages();

    }

    // ===== DRAW =====

    private void drawMessage(
            Message data
    ) {

        LinearLayout container =
                new LinearLayout(this);

        container.setOrientation(
                LinearLayout.VERTICAL);

        container.setPadding(
                0,
                4,
                0,
                4);

        container.setGravity(
                data.mine
                        ? Gravity.END
                        : Gravity.START);

        LinearLayout bubble =
                new LinearLayout(this);

        bubble.setOrientation(
                LinearLayout.VERTICAL);

        bubble.setPadding(
                22,
                14,
                22,
                10);

        GradientDrawable bg =
                new GradientDrawable();

        if (data.mine) {

            bg.setColor(
                    Color.parseColor("#7C3AED"));

        } else {

            bg.setColor(
                    Color.parseColor("#111827"));

        }

        bg.setCornerRadius(34);

        bubble.setBackground(bg);

        // ===== TEXT =====

        TextView msg =
                new TextView(this);

        msg.setText(data.text);

        msg.setTextColor(Color.WHITE);

        msg.setTextSize(15);

        bubble.addView(msg);

        // ===== TIME =====

        TextView time =
                new TextView(this);

        time.setText(data.time);

        time.setTextSize(10);

        time.setTextColor(
                data.mine
                        ? Color.parseColor("#DDD6FE")
                        : Color.parseColor("#6B7280"));

        time.setPadding(
                0,
                6,
                0,
                0);

        bubble.addView(time);

        container.addView(bubble);

        messagesLayout.addView(container);

        scroll.post(() ->
                scroll.fullScroll(
                        View.FOCUS_DOWN));

    }

    // ===== SAVE =====

    private void saveMessages() {

        try {

            JSONArray array =
                    new JSONArray();

            for (Message msg : messages) {

                JSONObject obj =
                        new JSONObject();

                obj.put(
                        "text",
                        msg.text);

                obj.put(
                        "mine",
                        msg.mine);

                obj.put(
                        "time",
                        msg.time);

                obj.put(
                        "senderId",
                        msg.senderId);

                obj.put(
                        "delivered",
                        msg.delivered);

                array.put(obj);

            }

            SharedPreferences prefs =
                    getSharedPreferences(
                            "AstraComm",
                            MODE_PRIVATE);

            prefs.edit()
                    .putString(
                            "chat_history",
                            array.toString())
                    .apply();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ===== LOAD =====

    private void loadMessages() {

        try {

            SharedPreferences prefs =
                    getSharedPreferences(
                            "AstraComm",
                            MODE_PRIVATE);

            String json =
                    prefs.getString(
                            "chat_history",
                            "");

            if (json.isEmpty()) {

                receiveMessage(
                        "Welcome to AstraComm",
                        "0001");

                return;

            }

            JSONArray array =
                    new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {

                JSONObject obj =
                        array.getJSONObject(i);

                Message msg =
                        new Message(
                                obj.getString("text"),
                                obj.getBoolean("mine"),
                                obj.getString("time"),
                                obj.getString("senderId"),
                                obj.getBoolean("delivered")
                        );

                messages.add(msg);

                drawMessage(msg);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
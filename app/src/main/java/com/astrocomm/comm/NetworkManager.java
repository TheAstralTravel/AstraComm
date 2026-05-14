package com.astrocomm.comm;

import android.content.Context;
import android.content.SharedPreferences;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import org.json.JSONObject;

import java.net.URI;

public class NetworkManager {

    private static WebSocketClient client;

    // ===== CONNECT =====

    public static void connect(
            Context context,
            String url) {

        try {

            SharedPreferences prefs =
                    context.getSharedPreferences(
                            "AstraComm",
                            Context.MODE_PRIVATE);

            String myId =
                    prefs.getString(
                            "id",
                            "0000");

            client =
                    new WebSocketClient(
                            new URI(url)) {

                        @Override
                        public void onOpen(
                                ServerHandshake handshake) {

                            try {

                                JSONObject login =
                                        new JSONObject();

                                login.put(
                                        "type",
                                        "login");

                                login.put(
                                        "id",
                                        myId);

                                send(
                                        login.toString());

                            } catch (Exception e) {

                                e.printStackTrace();

                            }

                        }

                        @Override
                        public void onMessage(
                                String message) {

                            System.out.println(
                                    "NEW MESSAGE: "
                                            + message);

                        }

                        @Override
                        public void onClose(
                                int code,
                                String reason,
                                boolean remote) {

                        }

                        @Override
                        public void onError(
                                Exception ex) {

                            ex.printStackTrace();

                        }

                    };

            client.connect();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ===== SEND =====

    public static void sendMessage(
            String from,
            String to,
            String text) {

        try {

            JSONObject json =
                    new JSONObject();

            json.put(
                    "type",
                    "message");

            json.put(
                    "from",
                    from);

            json.put(
                    "to",
                    to);

            json.put(
                    "text",
                    text);

            client.send(
                    json.toString());

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
package com.astrocomm.comm;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import org.json.JSONObject;

import java.net.InetSocketAddress;

import java.util.HashMap;

public class AstraNode extends WebSocketServer {

    // ===== USERS =====

    private static final HashMap<
            String,
            WebSocket> users =
            new HashMap<>();

    // ===== CONSTRUCTOR =====

    public AstraNode() {

        super(
                new InetSocketAddress(6000));

    }

    // ===== START NODE =====

    public static void startNode() {

        AstraNode node =
                new AstraNode();

        node.start();

    }

    // ===== OPEN =====

    @Override
    public void onOpen(
            WebSocket conn,
            ClientHandshake handshake) {

    }

    // ===== MESSAGE =====

    @Override
    public void onMessage(
            WebSocket conn,
            String message) {

        try {

            JSONObject json =
                    new JSONObject(message);

            String type =
                    json.getString("type");

            // ===== LOGIN =====

            if (type.equals("login")) {

                String id =
                        json.getString("id");

                users.put(id, conn);

                return;

            }

            // ===== CHAT =====

            if (type.equals("message")) {

                String from =
                        json.getString("from");

                String to =
                        json.getString("to");

                String text =
                        json.getString("text");

                JSONObject packet =
                        new JSONObject();

                packet.put(
                        "type",
                        "message");

                packet.put(
                        "from",
                        from);

                packet.put(
                        "text",
                        text);

                WebSocket target =
                        users.get(to);

                if (target != null) {

                    target.send(
                            packet.toString());

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ===== CLOSE =====

    @Override
    public void onClose(
            WebSocket conn,
            int code,
            String reason,
            boolean remote) {

    }

    // ===== ERROR =====

    @Override
    public void onError(
            WebSocket conn,
            Exception ex) {

        ex.printStackTrace();

    }

    // ===== START =====

    @Override
    public void onStart() {

        System.out.println(
                "AstraNode started");

    }

}
package com.astrocomm.comm;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class AstraSocket {

    private static WebSocketClient client;

    public interface MessageListener {

        void onMessage(String message);

    }

    private static MessageListener listener;

    // ===== CONNECT =====

    public static void connect(
            String serverUrl,
            MessageListener msgListener
    ) {

        listener = msgListener;

        try {

            client =
                    new WebSocketClient(
                            new URI(serverUrl)
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

                            if (listener != null) {

                                listener.onMessage(
                                        message);

                            }

                        }

                        @Override
                        public void onClose(
                                int code,
                                String reason,
                                boolean remote
                        ) {

                            System.out.println(
                                    "Disconnected");

                        }

                        @Override
                        public void onError(
                                Exception ex
                        ) {

                            ex.printStackTrace();

                        }

                    };

            client.connect();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ===== SEND =====

    public static void send(
            String text
    ) {

        if (
                client != null
                &&
                client.isOpen()
        ) {

            client.send(text);

        }

    }

}
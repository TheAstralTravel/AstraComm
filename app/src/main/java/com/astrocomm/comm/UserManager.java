package com.astrocomm.comm;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class UserManager {

    private static final String PREFS =
            "AstraComm";

    // ===== REGISTER =====

    public static void register(
            Context context,
            String username,
            String password
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE);

        // ===== UUID =====

        String uuid =
                UUID.randomUUID()
                        .toString();

        // ===== DISPLAY ID =====

        int lastId =
                prefs.getInt(
                        "last_id",
                        0);

        lastId++;

        String displayId =
                String.format(
                        "%04d",
                        lastId);

        // ===== SAVE =====

        prefs.edit()

                .putBoolean(
                        "registered",
                        true)

                .putString(
                        "username",
                        username)

                .putString(
                        "password",
                        password)

                .putString(
                        "uuid",
                        uuid)

                .putString(
                        "display_id",
                        displayId)

                .putInt(
                        "last_id",
                        lastId)

                .apply();

    }

    // ===== USERNAME =====

    public static String getUsername(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE);

        return prefs.getString(
                "username",
                "Unknown");

    }

    // ===== DISPLAY ID =====

    public static String getDisplayId(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE);

        return prefs.getString(
                "display_id",
                "0000");

    }

    // ===== UUID =====

    public static String getUUID(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREFS,
                        Context.MODE_PRIVATE);

        return prefs.getString(
                "uuid",
                "");

    }

}
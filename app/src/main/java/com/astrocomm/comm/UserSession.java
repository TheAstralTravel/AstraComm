package com.astrocomm.comm;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private static final String PREF =
            "AstraCommUser";

    // ===== SAVE =====

    public static void save(
            Context context,
            String id,
            String username
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        prefs.edit()

                .putString("id", id)

                .putString(
                        "username",
                        username)

                .apply();

    }

    // ===== GET ID =====

    public static String getId(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        return prefs.getString(
                "id",
                "0000");

    }

    // ===== GET USERNAME =====

    public static String getUsername(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        return prefs.getString(
                "username",
                "Unknown");

    }

    // ===== LOGGED =====

    public static boolean isLogged(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        return prefs.contains("id");

    }

    // ===== LOGOUT =====

    public static void logout(
            Context context
    ) {

        SharedPreferences prefs =
                context.getSharedPreferences(
                        PREF,
                        Context.MODE_PRIVATE);

        prefs.edit().clear().apply();

    }

}
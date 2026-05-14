package com.astrocomm.comm;

import java.util.ArrayList;

public class FakeDatabase {

    public static ArrayList<User> users =
            new ArrayList<>();

    static {

        users.add(
                new User("Astra", "0001"));

        users.add(
                new User("Cipher", "0002"));

        users.add(
                new User("Nebula", "0003"));

        users.add(
                new User("Darkside", "0004"));

        users.add(
                new User("Kite", "0005"));

    }

}
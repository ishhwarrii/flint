package com.ishhwarrii.flint;

import java.util.HashMap;

public class Database {

    private HashMap<String, String> store;

    public Database() {
        store = new HashMap<>();
    }

    public void set(String key, String value) {
        store.put(key, value);
    }

    public String get(String key) {
        return store.get(key);
    }

    public boolean exists(String key) {
        return store.containsKey(key);
    }

    public boolean delete(String key) {
        if (store.containsKey(key)) {
            store.remove(key);
            return true;
        }
        return false;
    }
}
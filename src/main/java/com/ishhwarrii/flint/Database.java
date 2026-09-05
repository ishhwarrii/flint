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


    
}

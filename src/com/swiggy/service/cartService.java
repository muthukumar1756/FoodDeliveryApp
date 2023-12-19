package com.swiggy.service;

import com.swiggy.model.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class cartService {
    private static cartService instance;

    private cartService() {
    }

    public static cartService getInstance() {
        if (instance ==  null) {
            instance = new cartService();
        }

        return instance;
    }
}

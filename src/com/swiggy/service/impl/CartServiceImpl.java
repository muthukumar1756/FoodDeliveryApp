package com.swiggy.service.impl;

import com.swiggy.service.CartService;

public class CartServiceImpl implements CartService {
    private static CartServiceImpl instance;

    private CartServiceImpl () {
    }

    public static CartService getInstance() {
        if (instance ==  null) {
            instance = new CartServiceImpl();
        }

        return instance;
    }
}

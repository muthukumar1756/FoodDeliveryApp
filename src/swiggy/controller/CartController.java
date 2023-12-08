package swiggy.controller;

public class CartController {

    private static CartController instance;

    private CartController() {
    }

    public static CartController getInstance() {
        if (instance == null) {
            instance = new CartController();
        }

        return instance;
    }

}

package swiggy.service;

public class CartService {

    private static CartService instance;

    private CartService() {
    }

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }

        return instance;
    }

}

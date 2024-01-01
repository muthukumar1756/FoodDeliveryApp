package com.swiggy.view;

import java.util.Scanner;

public class ScannerInstance {
    private static Scanner scannerInstance;

    public static Scanner getInstance() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
         return scannerInstance;
    }
}

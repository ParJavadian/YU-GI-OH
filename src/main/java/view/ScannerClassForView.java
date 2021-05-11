package view;

import java.util.Scanner;

public class ScannerClassForView {
    private static Scanner scanner = new Scanner(System.in);
    public static Scanner getScanner() {
        return scanner;
    }
}

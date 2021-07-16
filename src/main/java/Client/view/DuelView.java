package Client.view;

public class DuelView {

    public static void printText(String output) {
        System.out.println(output);
    }

    public static String scan() {
        return ScannerClassForView.getScanner().nextLine();
    }
}

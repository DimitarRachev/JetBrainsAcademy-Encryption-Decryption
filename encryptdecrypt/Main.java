package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String msg = scanner.nextLine();
        int key = Integer.parseInt(scanner.nextLine());

        switch (command) {
            case "enc":
                printEncrypted(msg, key);
                break;
            case "dec":
                printDecrypted(msg, key);
                break;
        }
    }

    private static void printDecrypted(String msg, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char curr = msg.charAt(i);
            //those checks are no longer needed in this stage
//            if (curr >= 97 && curr <= 122) {
            curr = (char) (curr - key);
//                while (curr < 97) {
//                    curr = (char) (curr + 26);
//                }
//            }
            sb.append(curr);
        }
        System.out.println(sb);
    }

    private static void printEncrypted(String msg, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char curr = msg.charAt(i);
            //those checks are no longer needed in this stage
            //            if (curr >= 97 && curr <= 122) {
            curr = (char) (curr + key);
//                while (curr > 122) {
//                    curr = (char) (curr - 26);
//                }
//            }
            sb.append(curr);
        }
        System.out.println(sb);
    }
}

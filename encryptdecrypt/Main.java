package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
        String[] input = parceArguments(args);
        String command = input[0];
        int key = Integer.parseInt(input[1]);
        String msg = input[2];

        switch (command) {
            case "enc":
                printEncrypted(msg, key);
                break;
            case "dec":
                printDecrypted(msg, key);
                break;
        }
    }

    private static String[] parceArguments(String[] arr) {
        String command = "enc";
        String key = "0";
        String data = "";
        for (int i = 0; i < arr.length - 1; i += 2) {
            if ("-mode".equals(arr[i])) {
                command = arr[i + 1];
            } else if ("-key".equals(arr[i])) {
                key = arr[i + 1];
            } else if ("-data".equals(arr[i])) {
                data = arr[i + 1];
            }
        }


        return new String[]{command, key, data};
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

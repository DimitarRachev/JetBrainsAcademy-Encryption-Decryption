package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        int key = Integer.parseInt(scanner.nextLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char curr = msg.charAt(i);
            if (curr >= 97 && curr <= 122) {
                curr = (char) (curr + key);
                if (curr > 122) {
                    curr = (char) (curr - 26);
                }
            }
            sb.append(curr);
        }
        System.out.println(sb);
    }
}

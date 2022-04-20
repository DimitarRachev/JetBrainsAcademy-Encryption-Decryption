package encryptdecrypt;

public class Main {
    public static void main(String[] args) {

        String msg = "we found a treasure!";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char curr = msg.charAt(i);
            if (curr >= 97 && curr <= 122) {
                curr = (char) ((122 + 97) - curr);
            }
            sb.append(curr);
        }
        System.out.println(sb.toString());
    }
}

package encryptdecrypt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] input;
        try {
            input = parcelArguments(args);
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }
        String command = input[0];
        int key = Integer.parseInt(input[1]);
        String data = input[2];
        String in = input[3];
        String out = input[4];
        String alg = input[5];
        List<String> msg = new ArrayList<>();
        if ("".equals(data)) {
            if (!"".equals(in)) {
                try {
                    msg = readFile(in);
                } catch (IOException e) {
                    System.out.println("Error" + e.getMessage());
                    return;
                }
            }
        } else {
            msg.add(data);
        }
        try {
            switch (command) {
                case "enc":
                    printEncrypted(msg, key, out, alg);
                    break;
                case "dec":
                    printDecrypted(msg, key, out, alg);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
            return;
        }
    }

    private static List<String> readFile(String in) throws IOException {
        Path path = Paths.get(in);
        return Files.readAllLines(path);
    }

    private static String[] parcelArguments(String[] arr) {
        String command = "enc";
        String key = "0";
        String data = "";
        String in = "";
        String out = "";
        String alg = "shift";
        for (int i = 0; i < arr.length - 1; i += 2) {
            if ("-mode".equals(arr[i])) {
                command = arr[i + 1];
            } else if ("-key".equals(arr[i])) {
                key = arr[i + 1];
            } else if ("-data".equals(arr[i])) {
                data = arr[i + 1];
            } else if ("-in".equals(arr[i])) {
                in = arr[i + 1];
            } else if ("-out".equals(arr[i])) {
                out = arr[i + 1];
            } else if ("-alg".equals(arr[i])) {
                alg = arr[i + 1];
            }
        }


        return new String[]{command, key, data, in, out, alg};
    }

    private static void printDecrypted(List<String> list, int key, String out, String alg) throws IOException {
        StringBuilder sb;
        switch (alg) {
            case "shift":
                sb = decryptShift(list, key);
                break;
            case "unicode":
                 sb = decryptUnicode(list, key);
                break;
            default:
                sb = new StringBuilder();
                break;
        }
        if ("".equals(out)) {
            System.out.print(sb);
        } else {
            writeToFile(sb.toString(), out);
        }
    }

    private static StringBuilder decryptUnicode(List<String> list, int key) {
        StringBuilder sb = new StringBuilder();
        for (String msg : list) {
            for (int i = 0; i < msg.length(); i++) {
                char curr = msg.charAt(i);
                curr = (char) (curr - key);
                sb.append(curr);
            }
            sb.append(System.lineSeparator());
        }
        return sb;
    }

    private static StringBuilder decryptShift(List<String> list, int key) {
        StringBuilder sb = new StringBuilder();
        for (String msg : list) {
            for (int i = 0; i < msg.length(); i++) {
                char curr = msg.charAt(i);
            if (curr >= 97 && curr <= 122) {
                curr = (char) (curr - key);
                while (curr < 97) {
                    curr = (char) (curr + 26);
                }
            }
                sb.append(curr);
            }
            sb.append(System.lineSeparator());
        }
        return sb;
    }

    private static void writeToFile(String str, String out) throws IOException {
        Writer writer = new FileWriter(out);
        writer.write(str);
        writer.close();
    }

    private static void printEncrypted(List<String> list, int key, String out, String alg) throws IOException {
        StringBuilder sb;
        switch (alg) {
            case "shift":
                sb = encryptShift(list, key);

                break;
            case "unicode":
                sb = encryptUnicode(list, key);
                break;
            default:
                sb = new StringBuilder();
                break;
        }
        if ("".equals(out)) {
            System.out.print(sb);
        } else {
            writeToFile(sb.toString(), out);
        }
    }

    private static StringBuilder encryptShift(List<String> list, int key) {
        StringBuilder sb = new StringBuilder();
        for (String msg : list) {
            for (int i = 0; i < msg.length(); i++) {
                char curr = msg.charAt(i);
                            if (curr >= 97 && curr <= 122) {
                curr = (char) (curr + key);
                while (curr > 122) {
                    curr = (char) (curr - 26);
                }
            }
                sb.append(curr);
            }
            sb.append(System.lineSeparator());
        }
        return sb;
    }

    private static StringBuilder encryptUnicode(List<String> list, int key) {
        StringBuilder sb = new StringBuilder();
        for (String msg : list) {
            for (int i = 0; i < msg.length(); i++) {
                char curr = msg.charAt(i);
                curr = (char) (curr + key);
                sb.append(curr);
            }
            sb.append(System.lineSeparator());
        }
        return sb;
    }
}

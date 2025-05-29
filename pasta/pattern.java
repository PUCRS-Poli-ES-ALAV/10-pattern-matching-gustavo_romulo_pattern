package pasta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class pattern {

    public static int countItr = 0;
    public static int countIns = 0;

    public static void main(String[] args) {
        String bigS1 = "";
        String bigS2 = "";

        try {
            bigS1 = new String(Files.readAllBytes(Paths.get("stringA.txt")));
            bigS2 = new String(Files.readAllBytes(Paths.get("pat.txt")));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("exception");
        }

        String s1 = "adjawiljdaçwiojdaiçwjdailçjdawilçjdaiwjdailçwjdaçijdailçwjdailçwjdalçijdailçwjdailwjdailçjdaç";
        String s2 = "jdailçjdaç";
        int resp = match(bigS1, bigS2);
        System.out.println(resp);
        System.out.println("instrucoes: " + countIns);
        System.out.println("iterações: " + countItr);
    }

    public static int match(String s1, String s2) {
        int resp = -1;
        countIns++; // atribuição de resp
        int len1 = s1.length();
        int len2 = s2.length();
        countIns += 2; // length()

        for (int i = 0; i < len1; i++) {
            countItr++; // loop externo
            countIns += 2; // comparação e incremento

            countIns += 2; // charAt(i), charAt(0)
            if (s1.charAt(i) == s2.charAt(0)) {
                countIns++; // comparação

                for (int j = 0; j < len2; j++) {
                    countItr++; // loop interno
                    countIns += 2; // comparação e incremento

                    countIns += 3; // i+j, charAt(i+j), charAt(j)
                    if (i + j >= len1 || s1.charAt(i + j) != s2.charAt(j)) {
                        countIns += 2; // comparação e break
                        break;
                    }

                    countIns++; // j == len2 - 1
                    if (j == len2 - 1) {
                        countIns++; // return
                        return i;
                    }
                }
            }
        }

        countIns++; // return
        return resp;
    }
}
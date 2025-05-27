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
            bigS1 = new String(Files.readAllBytes(Paths.get("stringao.txt")));
            bigS2 = new String(Files.readAllBytes(Paths.get("stringao2.txt")));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("exception");
        }        
        String s1 = "adjawiljdaçwiojdaiçwjdailçjdawilçjdaiwjdailçwjdaçijdailçwjdailçwjdalçijdailçwjdailwjdailçjdaç";
        String s2 = "jdailçjdaç";
        int resp = match(bigS1, bigS2);
        System.out.println(resp);
        System.out.println("instrucoes: "+countIns);
        System.out.println("iterações: "+countItr);
    }

    public static int match(String s1, String s2) {
        int resp = -1;
        countIns++;
        countIns++;
        for (int i = 0; i < s1.length(); i++) {
            countIns+=2;
            countItr++;
            if (s1.charAt(i) == s2.charAt(0)) {
                countIns++;
                countIns++;
                for (int j = 0; j < s2.length(); j++) {
                    countIns+=2;
                    countItr++;
                    if (s1.charAt(i + j) != s2.charAt(j)) {
                        countIns++;
                        break;
                    }
                    if (j == s2.length()-1) {
                        countIns++;
                        return i;
                    }
                }
            }
        }
        countIns++;
        return resp;
    }
}
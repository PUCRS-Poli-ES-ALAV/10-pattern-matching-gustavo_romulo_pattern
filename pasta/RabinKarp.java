import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RabinKarp {

    public static int Q = 2147483647;
    public static int R = 255;
    public static int countIns = 0;
    public static int countItr = 0;

    public static void main(String[] args) {
        String bigS1 = "";
        String bigS2 = "";

        try {
            bigS1 = new String(Files.readAllBytes(Paths.get("pat.txt")));
            bigS2 = new String(Files.readAllBytes(Paths.get("stringA.txt")));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("exception");
        }

        String pat = "ADF";
        String txt = "ABCDCBDCBDACBDABDCBADF";
        int resp = search(bigS1, bigS2);
        System.out.println(resp);
        System.out.println("Instrucoes: " + countIns + "; Iteracoes: " + countItr);
    }

    private static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        countIns += 2;

        long patHash = hash(pat, M); // hash do padrão
        countIns++;

        for (int i = 0; i <= N - M; i++) {
            countItr++; // iteração principal do Rabin-Karp
            countIns += 2; // comparação de limite e incremento do i

            String sub = txt.substring(i, i + M);
            countIns++; // substring
            long txtHash = hash(sub, M);
            countIns++; // atribuição do hash

            if (patHash == txtHash) {
                countIns++; // comparação
                countIns++; // retorno
                return i; // ocorrência encontrada
            }
        }

        countIns++; // retorno fora do loop
        return N; // nenhuma ocorrência
    }

    private static long hash(String s, int M) {
        long h = 0;
        countIns++; // inicialização de h

        for (int j = 0; j < M; j++) {
            countItr++; // iteração de hash
            countIns += 3; // comparação, incremento do j, charAt
            h = (h * R + s.charAt(j)) % Q;
            countIns += 4; // multiplicação, adição, módulo, atribuição
        }

        countIns++; // retorno
        return h;
    }
}

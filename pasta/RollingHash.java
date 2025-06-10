import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RollingHash {

    public static int Q = 2147483647;
    public static int R = 255;
    public static int countIns = 0;
    public static int countItr = 0;

    public static void main(String[] args) {
        String bigS1 = "";
        String bigS2 = "";

        try {
            bigS1 = new String(Files.readAllBytes(Paths.get("pat.txt")));
            bigS2 = new String(Files.readAllBytes(Paths.get("string600.txt")));
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
        //long hash1 = hash(txt,M);
        countIns++;

        String ini = txt.substring(0, M); 
        countIns++; //string
        long txtHash = hash(ini, M);
        countIns++; // atribuição do hash
        
        countIns++; //int i
        for (int i = 0; i <= N - M; i++) {
            countItr++; // iteração principal do Rabin-Karp
            countIns += 2; // comparação de limite e incremento do i

            String sub = txt.substring(i, i + M);
            countIns++; // substring

            countIns++;//check
            if(i != 0){
                txtHash = rolling(txt, i, txtHash, M);
                countIns++; //chamada
            }

            if (patHash == txtHash) {
                countIns++; // comparação
                boolean ok = true;
                countIns++; //definicao
                countIns++; //j
                for(int j = 0; j < M; j++){
                    countIns+=2; //compara e soma
                    if(sub.charAt(j) != pat.charAt(j)){
                        countIns++; //check
                        ok = false;
                        countIns++; //define
                    }
                }
                if(ok){
                    countIns++; //check
                    countIns++; // retorno
                    return i; // ocorrência encontrada
                }
            }

        }

        countIns++; // retorno fora do loop
        return N; // nenhuma ocorrência
    }

    private static long hash(String s, int M) {
        long h = 0;
        countIns++; // inicialização de h

        for (int j = 0; j < M; j++) {
            countItr++; 
            countIns += 3; 
            h = (h * R + s.charAt(j)) % Q;
            countIns += 5; 
        }

        countIns++; // retorno
        return h;
    }

    private static long rolling(String s, int idx, long hash, int patL){
        countIns+=13; //tripa
        return (hash - (s.charAt(idx-1) * ((int)Math.pow(R, patL - 1))) % Q) * R + s.charAt(idx + patL -1);
    }
}

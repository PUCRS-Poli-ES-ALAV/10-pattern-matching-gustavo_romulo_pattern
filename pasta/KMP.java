// KMP pattern searching algorithm 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KMP {

    static int countItr = 0;
    static int countIns = 0;
    
    public static void main(String[] args) {
        String bigS1 = "";
        String bigS2 = "";

        try {
            bigS1 = new String(Files.readAllBytes(Paths.get("string60M.txt")));
            bigS2 = new String(Files.readAllBytes(Paths.get("string60M2.txt")));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("exception");
        }
        String pat = "a123";
        String txt = "asdka12l123a123";
        KMPSearch(bigS1, bigS2);
        System.out.println("countIns: "+countIns);
        System.out.println("countItr: "+countItr);
        
    }

    static void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        countIns+=2;

        // cria lps[] que vai guardar o maior
        // valor prefixo sufixo para o padrão
        int lps[] = new int[M];
        int j = 0; // index for pat[]
        countIns+=2;

        // Calcula lps[]
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        countIns++;
        while (i < N) {
            countItr++;
            countIns+=3;
            if (pat.charAt(j) == txt.charAt(i)) {
                countIns+=2;
                j++;
                i++;
            }
            countIns++;
            if (j == M) {
                countIns+=3;
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                j = lps[j - 1];
            }

            // mismatch após j matches
            countIns+=5;
            if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Não faz match dos caracteres lps[0..lps[j-1]],
                // não é necesssário, eles combinarão
                countIns++;
                if (j != 0){
                    countIns+=2;
                    j = lps[j - 1];
                }
                else{
                    countIns+=2;
                    i = i + 1;
                }
            }
        }
    }

    static void computeLPSArray(String pat, int M, int lps[]) 
	{
        // tamanho do maior prefixo sufixo anterior 
		countIns+=3;
        int len = 0; 
		int i = 1; 
		lps[0] = 0; // lps[0] is always 0 

		// loop calcula lps[i] for i = 1 to M-1 
		countIns++;
        while (i < M) {
            countItr++;
            countIns+=3; 
			if (pat.charAt(i) == pat.charAt(len)) { 
				countIns+=3; 
                len++; 
				lps[i] = len; 
				i++; 
			} 
			else // (pat[i] != pat[len]) 
			{ 
				countIns++; 
                if (len != 0) { 
                    countIns+=2; 
					len = lps[len - 1]; 
				} 
				else // if (len == 0) 
				{ 
					countIns+=2;
                    lps[i] = len; 
					i++; 
				} 
			} 
		} 
        //teste do vetor lps
        // for (int index = 0; index < lps.length; index++) {
        //     System.out.println("pos: "+index+"; val: "+lps[index]);
        // }
    }
}
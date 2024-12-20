package dp29makingpallindrome;

public class MakePallindrome {

    public static int lcs(String s1,String s2){

        int n = s1.length();
        int m = s2.length();

        int prev[] = new int[m+1];
        int cur[] = new int[m+1];

        for (int i = 1;i<=n;i++){
            for (int j =1;j<=m;j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                }else{
                    cur[j] = Math.max(prev[j],cur[j-1]);
                }
            }
            prev = cur.clone();
        }

        return prev[m];
    }

    public static int Longest_Palindromic_SubsequenceCount(String s){

        String rev = new StringBuilder(s).reverse().toString();

        return lcs(s,rev);

    }

    static int minInsertion(String s) {
        int n = s.length();
        int k = Longest_Palindromic_SubsequenceCount(s);

        // The minimum insertions required is the difference between the string length and its
        // Longest Palindromic Subsequence length
        return n - k;
    }

}

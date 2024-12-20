package dp28LongestPalindromicSubsequence;

public class Longest_Palindromic_Subsequence {

    public static void main(String[] args) {
        String s = "bbabcbcab";

        System.out.print("The Length of Longest Palindromic Subsequence is ");
        System.out.println(Longest_Palindromic_SubsequenceCount(s));
    }

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

}

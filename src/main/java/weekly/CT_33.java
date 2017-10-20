package weekly;

import java.util.*;

/**
 * Created by xudabiao on 2017/5/22.
 */
public class CT_33 {
    public static void main(String[] args) {
//        System.out.println(new weekly.CT_33().validSquare(new int[]{1,0},
//        new int[]{0,1},
//        new int[]{0,-1},
//        new int[]{-1,0}));
        System.out.println(new CT_33().fractionAddition("-1/4-4/5-1/4"));
    }

    /**
     * 594. Longest Harmonious Subsequence
     * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
     * <p>
     * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
     * <p>
     * Example 1:
     * Input: [1,3,2,2,5,2,3,7]
     * Output: 5
     * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
     */
    public int findLHS(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int num : nums)
            if (map.get(num) == null)
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        int max = 0;
        for (Integer num : map.keySet()) {
            if(map.containsKey(num-1))
                max = Math.max(max,map.get(num)+map.get(num-1));
            if(map.containsKey(num+1))
                max = Math.max(max,map.get(num)+map.get(num+1));
        }
        return max;
    }

    /**
     * 593. Valid Square
     Given the coordinates of four points in 2D space, return whether the four points could construct a square.

     The coordinate (x,y) of a point is represented by an integer array with two integers.

     Example:
     Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
     Output: True
     Note:

     All the input integers are in the range [-10000, 10000].
     A valid square has four equal sides with positive length and four equal angles (90-degree angles).
     Input points have no order.
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] qq = new int[][]{p1,p2,p3,p4};
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < qq.length; i++)
            for (int j = i + 1; j < qq.length; j++)
                set.add((qq[i][0] - qq[j][0]) * (qq[i][0] - qq[j][0]) +
                        (qq[i][1] - qq[j][1]) * (qq[i][1] - qq[j][1]));
        if(set.contains(0))
            return false;
        return set.size()==2;
    }

    /**
     * 592. Fraction Addition and Subtraction
     *
     Given a string representing an expression of fraction addition and subtraction,
     you need to return the calculation result in string format.
     The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.

     Example 1:
     Input:"-1/2+1/2"
     Output: "0/1"
     Example 2:
     Input:"-1/2+1/2+1/3"
     Output: "1/3"
     Example 3:
     Input:"1/3-1/2"
     Output: "-1/6"
     Example 4:
     Input:"5/3+1/3"
     Output: "2/1"
     Note:
     The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
     Each fraction (input and output) has format ¡Ànumerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
     The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
     The number of given fractions will be in the range [1,10].
     The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
     */
    public String fractionAddition(String expression) {
        List<String> g = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if(i!=0&&(expression.charAt(i)=='+'||expression.charAt(i)=='-')) {
                g.add(sb.toString());
                sb.delete(0,sb.length());
                sb.append(expression.charAt(i));
            }
            else
                sb.append(expression.charAt(i));
        }
        g.add(sb.toString());
        while(g.size()>1){
            String p1 = g.remove(0);
            String p2 = g.remove(0);
            g.add(0,addOrSub(p1,p2));
        }
        if(g.get(0).startsWith("+"))
            return g.get(0).substring(1);
        return g.get(0);
    }

    private String addOrSub(String p1, String p2) {
        p1 = normalize(p1);
        p2 = normalize(p2);
        String[] decompose1 = resolveStr(p1);
        String[] decompose2 = resolveStr(p2);
        int denominator1 = Integer.valueOf(decompose1[2]);
        int denominator2 = Integer.valueOf(decompose2[2]);
        int numerator1 = Integer.valueOf(decompose1[1])*(decompose1[0].equals("+")?1:-1);
        int numerator2 = Integer.valueOf(decompose2[1])*(decompose2[0].equals("+")?1:-1);
        int newG = gz1700(denominator1,denominator2);
        int newNumer = numerator1*newG/denominator1+numerator2*newG/denominator2;
        if(newNumer == 0)
            return "+0/1";
        int q = maxP(newG,Math.abs(newNumer));
        newG = newG/q;newNumer = newNumer/q;
        StringBuilder sb = new StringBuilder();
        return sb.append(newNumer).append("/").append(newG).toString();
    }

    private int gz1700(int i, int j){
        int k = Math.min(i,j);
        int q = Math.max(i,j);
        if(q%k==0)
            return q;
        int dump = maxP(k,q);
        return k*q/dump;
    }

    private int maxP(int i,int j){
        int k = Math.min(i,j);
        int q = Math.max(i,j);
        if(q%k==0)
            return k;
        for (int index = k/2;index>=2;index--){
            if(k%index==0&&q%index==0)
                return index;
        }
        return 1;
    }

    private String[] resolveStr(String p1) {
        String[] qa = new String[3];
        qa[0] = p1.substring(0,1);
        qa[1] = p1.substring(1,p1.indexOf('/'));
        qa[2] = p1.substring(p1.indexOf('/')+1);
        return qa;
    }

    private String normalize(String p1) {
        if(!p1.startsWith("+")&&!p1.startsWith("-"))
            p1 = "+" + p1;
        if(!p1.contains("/"))
            p1 = p1 + "/1";
        return p1;
    }

    /**
     * 588. Design In-Memory File System
     * @link FileSystem
     *
     Design an in-memory file system to simulate the following functions:

     ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

     mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

     addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

     readContentFromFile: Given a file path, return its content in string format.

     Example:
     Input:
     ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
     [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
     Output:
     [null,[],null,null,["a"],"hello"]
     Explanation:
     filesystem
     Note:
     You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
     You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
     You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
     */
}

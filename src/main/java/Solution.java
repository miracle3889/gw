import java.util.*;

/**
 * Created by xudabiao on 2017/3/9.
 */
class Node{
    Node next;
    Object val;
}
class currency{
    public static void main(String[] args){
        char c = 0x3f;
        System.out.println(c);
    }
}
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] A= {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        System.out.println(new Solution().minDistance("eat","sea"));
    }

    public Node rev(Node head){
        Node temp = head.next;
        Node before = head;
        while (temp!=null){
            head = temp.next;
            temp.next = before;
            before = temp;
            temp = head;
        }
        return temp;
    }
    /**
     * Definition for a point.
     *
     */
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    /**
     * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

     Example 1:
     Input: "sea", "eat"
     Output: 2
     Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     Note:
     The length of given words won't exceed 500.
     Characters in given words can only be lower-case letters.
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int l1=word1.length(),l2=word2.length();
        int[][] dp = new int[l1+1][l2+1];
        for (int i = 1; i <= l1; i++)
            for (int j = 1; j <= l2; j++)
                dp[i][j] = word1.charAt(i-1) == word2.charAt(j-1) ? dp[i - 1][j - 1] + 1 :
                        Math.max(dp[i - 1][j], dp[i][j - 1]);
        return l1+l2-2*dp[l1][l2];
    }
    /**
     * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

     Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

     We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

     Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

     Example 1:
     Input:
     pid =  [1, 3, 10, 5]
     ppid = [3, 0, 5, 3]
     kill = 5
     Output: [5,10]
     Explanation:
     3
     /   \
     1     5
     /
     10
     Kill 5 will also kill 10.
     Note:
     The given kill id is guaranteed to be one of the given PIDs.
     n >= 1.
     * @param pid
     * @param ppid
     * @param kill
     * @return
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            List<Integer> temp = map.get(ppid.get(i));
            if(temp==null){
                temp = new ArrayList<>();
                map.put(ppid.get(i),temp);
            }
            temp.add(pid.get(i));
        }
        queue.add(kill);
        while (!queue.isEmpty()){
            int i = queue.poll();
            if(map.get(i)!=null)
                queue.addAll(map.get(i));
            answer.add(i);
        }
        return answer;
    }
    /**
     * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

     You need to find the shortest such subarray and output its length.

     Example 1:
     Input: [2, 6, 4, 8, 10, 9, 15]
     Output: 5
     Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
     Note:
     Then length of the input array is in range [1, 10,000].
     The input array may contain duplicates, so ascending order here means <=.
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        int l = Integer.MAX_VALUE,r = 0,max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if(num<max){
                for (int j = 0; j < i; j++)
                    if(nums[j]>num) {
                        l = Math.min(l,j);
                        break;
                    }
                r = i;
            }
            else
                max = num;
        }
        if(l == Integer.MAX_VALUE)
            return 0;
        return r-l+1;
    }

    public int findPaths(int m, int n, int N, int i, int j) {
        if(m*n==1)
            return 4;
        int[] p = new int[N];
        int min = Integer.MAX_VALUE;
        min = Math.min(Math.min(m-i,i),Math.min(n-j,j));
        //todo
        return -1;
    }
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0;
        int minN = 0,minT = 0,max = Integer.MIN_VALUE;
        for (int[] nut : nuts) {
            minT = Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
            sum += 2 * minT;
            minN = Math.abs(squirrel[0]-nut[0])+Math.abs(squirrel[1]-nut[1]);
            max = Math.max(minT-minN,max);
        }
        return sum-max;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> ls = new ArrayList<>();
        traverseS(s,t.val,ls);
        for (TreeNode treeNode : ls)
            if(isSubtreeHelper(treeNode,t))
                return true;
        return false;
    }

    private boolean isSubtreeHelper(TreeNode s, TreeNode t) {
        if(s==null&&t==null)
            return true;
        else if ((s==null&&t!=null)||(s!=null&&t==null))
            return false;
        if (s.val==t.val)
            return isSubtreeHelper(s.left,t.left)&&isSubtreeHelper(s.right,t.right);
        else
            return false;
    }

    private void traverseS(TreeNode s, int val, List<TreeNode> ls) {
        if(s==null)
            return;
        if(s.val == val)
            ls.add(s);
        traverseS(s.left,val,ls);
        traverseS(s.right,val,ls);
    }

    public int distributeCandies(int[] candies) {
        Arrays.sort(candies);
        if (candies.length==0)
            return 0;
        int tmp = candies[0];
        int ct = 1;
        for (int i = 1; i < candies.length; i++)
            if(candies[i]!=tmp){
                ct++;
                tmp = candies[i];
            }
        return Math.min(ct,candies.length/2);
    }
    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj-1]) { jj in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj-1] - prices[jj]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++)
            if (prices[i] > prices[i - 1])
                sum += (prices[i] - prices[i - 1]);
        return sum;
    }

    public int maxProfit1(int[] prices) {
        int max = 0;
        int stIndex = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i]<prices[stIndex])
                stIndex = i;
            else
                max = Math.max(max,prices[i]-prices[stIndex]);
        }
        return max;
    }
    /**
     * 568. Maximum Vacation Days
     LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules and restrictions you need to follow.

     Rules and restrictions:
     You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
     The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical), called flights representing the airline status from the city i to the city j. If there is no flight from the city i to the city j, flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
     You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and can only take flights on each week's Monday morning. Since flight time is so short, we don't consider the impact of flight time.
     For each city, you can only have restricted vacation days in different weeks, given an N*K matrix called days representing this relationship. For the value of days[i][j], it represents the maximum days you could take vacation in the city i in the week j.
     You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.

     Example 1:
     Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
     Output: 12
     Explanation:
     Ans = 6 + 3 + 3 = 12.

     One of the best strategies is:
     1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
     (Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
     2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
     3rd week : stay at city 2, and play 3 days and work 4 days.
     Example 2:
     Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
     Output: 3
     Explanation:
     Ans = 1 + 1 + 1 = 3.

     Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks.
     For each week, you only have one day to play and six days to work.
     So the maximum number of vacation days is 3.
     Example 3:
     Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
     Output: 21
     Explanation:
     Ans = 7 + 7 + 7 = 21

     One of the best strategies is:
     1st week : stay at city 0, and play 7 days.
     2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
     3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
     Note:
     N and K are positive integers, which are in the range of [1, 100].
     In the matrix flights, all the values are integers in the range of [0, 1].
     In the matrix days, all the values are integers in the range [0, 7].
     You could stay at a city beyond the number of vacation days, but you should work on the extra days, which won't be counted as vacation days.
     If you fly from the city A to the city B and take the vacation on that day, the deduction towards vacation days will count towards the vacation days of city B in that week.
     We don't consider the impact of flight hours towards the calculation of vacation days.
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        char[] spl = s1.toCharArray();
        Arrays.sort(spl);
        int index = 0;
        //O(m*n)
        while ((index+s1.length())<=s2.length()){
            String candidate = s2.substring(index,index+s1.length());
            char[] sp2 = candidate.toCharArray();
            Arrays.sort(sp2);//O(lgm)
            if(compare(spl,sp2))//O(m)
                return true;
            index++;
        }
        return false;
    }
    //O(m)
    private boolean compare(char[] sp1, char[] sp2) {
        for (int i = 0; i < sp1.length; i++)
            if(sp1[i]!=sp2[i])
                return false;
        return true;
    }

    public boolean checkInclusion2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;
        //O(n)
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }
        return false;
    }
    //O(1)
    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }

    public boolean checkInclusion3(String s1, String s2) {
        int[] count = new int[26];
        for(int i = 0; i < s1.length(); i++) count[s1.charAt(i)-'a']--;
        for(int l = 0, r = 0; r < s2.length(); r++) {
            if (++count[s2.charAt(r)-'a'] > 0)
                while(--count[s2.charAt(l++)-'a'] != 0) { /* do nothing */}
            else if ((r - l + 1) == s1.length()) return true;
        }
        return false;
    }
    /**
     * 560. Subarray Sum Equals K
     Difficulty: Medium
     Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

     Example 1:
     Input:nums = [1,1,1], k = 2
     Output: 2
     Note:
     The length of the array is in range [1, 20,000].
     The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     */
    public int subarraySum(int[] nums, int k) {
        int ct = 0;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        if (nums[0]==k)
            ct++;
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
            if(sums[i]==k)
                ct++;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                sums[j] = sums[j] - nums[i-1];
                if(sums[j]==k)
                    ct++;
            }
        }
        return ct;
    }
    public int subarraySum2(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
    /**
     * 566. Reshape the Matrix
     Difficulty: Easy
     In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

     You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

     The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

     If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

     Example 1:
     Input:
     nums =
     [[1,2],
     [3,4]]
     r = 1, c = 4
     Output:
     [[1,2,3,4]]
     Explanation:
     The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
     Example 2:
     Input:
     nums =
     [[1,2],
     [3,4]]
     r = 2, c = 4
     Output:
     [[1,2],
     [3,4]]
     Explanation:
     There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(r*c!=nums.length*nums[0].length)
            return nums;
        int[][] newNums = new int[r][c];
        int rIndex = 0,cIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                newNums[rIndex][cIndex] = nums[i][j];
                if(++cIndex==c) {
                    cIndex = 0;
                    rIndex++;
                }
            }
        }
        return newNums;
    }
//    public int strongPasswordChecker(String s)
//    {
//
//    }

    /**
     * 1-9      9
     * 10-99    90
     * 100-999  900
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        List<Long> l = new ArrayList<>();
        long z = 0;int count = 0;
        while (z<Integer.MAX_VALUE){
            z += 9*(long) Math.pow(10,count)*(count+1);
            l.add(z);
            count++;
        }
        for (int i = 0; i < l.size(); i++) {
            if(n<=l.get(i)) {
                int g = (int)Math.pow(10,i+1)-1-(int)((l.get(i)-n) / (i + 1));
                int position = (int)((l.get(i)-n)%(i + 1));
                String w = String.valueOf(g);
                char ww = w.charAt(i-position);
                return ww-'0';
            }
        }
        return 0;
    }

    public List<String> readBinaryWatch(int num) {
        List<String> ggwp = new ArrayList<>();
        int maxH = 3,maxM = 5;
        int h = 0,m = 0;
        for (; h <= maxH; h++) {
            m = num - h;
            if(m>=0&&m<=maxM){
                List<String> hh = hhDist(h,4);
                List<String> mm = hhDist(m,6);
                for (String s : hh)
                    for (String s1 : mm) ggwp.add(s + ":" + s1);
            }
        }
        return ggwp;
    }

    private List<String> hhDist(int light,int total) {
        List<String> l = new ArrayList<>();
        List<int[]> chosenB = new ArrayList<>();
        chosenB.add(new int[total]);
        hhff(chosenB,0);
        int count;
        int sum;
        for (int[] ints : chosenB) {
            count = 0;
            sum = 0;
            for (int i = 0; i < total; i++) {
                if(ints[i]==1) {
                    count++;
                    sum+=1<<i;
                }
            }
            if(count == light) {
                if(total==4&&sum<12)
                    l.add(String.valueOf(sum));
                else if(total==6&&sum<60)
                    l.add(String.format("%02d",sum));
            }
        }
        return l;
    }

    private void hhff(List<int[]> i,int st) {
        List<int[]> newList = new ArrayList<>();
        for (int[] ints : i) {
            int[] newg = new int[ints.length];
            System.arraycopy(ints,0,newg,0,ints.length);
            newg[st] = 1;
            newList.add(newg);
        }
        i.addAll(newList);
        if(st<i.get(0).length-1)
            hhff(i,st+1);
    }

    /**
     * a1a2a3...an
     * a1..ak-1ak+1..an
     * a1..ai-1ai+1..an
     */
    /**
     * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

     Note:
     The length of num is less than 10002 and will be ¡Ý k.
     The given num does not contain any leading zero.
     Example 1:

     Input: num = "1432219", k = 3
     Output: "1219"
     Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     Example 2:

     Input: num = "10200", k = 1
     Output: "200"
     Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
     Example 3:

     Input: num = "10", k = 2
     Output: "0"
     Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     */
    public String removeKdigits(String num, int k) {
        if(k>=num.length())
            return "0";
        List<Character> digits = new LinkedList<Character>();
        char[] cs = num.toCharArray();
        for (char c : cs)
            digits.add(c);
        int index = 0,count = 0;
        while (count<k&&digits.size()-1>index){
            if (digits.get(index)<=digits.get(index+1)) {
                index++;
                continue;
            }
            digits.remove(index);
            index = index == 0?0:index-1;
            count++;
            while (digits.size()>0&&digits.get(0)=='0'){
                digits.remove(0);
                index = index == 0?0:index-1;
            }
        }
        while (count<k){
            digits.remove(digits.size()-1);
            count++;
        }
        StringBuilder res = new StringBuilder();
        for (Character digit : digits)
            res.append(digit);
        if(res.length()==0)
            return "0";
        return res.toString();
    }

    private int sumOfLeftLeaves;
    public int sumOfLeftLeaves(TreeNode root) {
        calculLeftSum(root);
        return sumOfLeftLeaves;
    }

    private void calculLeftSum(TreeNode root) {
        if(root==null)
            return;
        if(root.left!=null&&root.left.left==null&&root.left.right==null)
            sumOfLeftLeaves+=root.left.val;
        else
            calculLeftSum(root.left);
        calculLeftSum(root.right);
    }

    /**
     * Given an integer n, find the closest integer (not including itself), which is a palindrome.

     The 'closest' is defined as absolute difference minimized between two integers.

     Example 1:
     Input: "123"
     Output: "121"
     Note:
     The input n is a positive integer represented by string, whose length will not exceed 18.
     If there is a tie, return the smaller one as answer.
     * @param M
     * @return
     */
    public String nearestPalindromic(String n) {
        if(n.length()==1)
            return String.valueOf(Integer.valueOf(n)-1);
        long aLong = Long.valueOf(n);
        int digits = n.length();
        List<Long> candidate = new ArrayList<>();
        candidate.add((long)Math.pow(10,digits)+1);
        candidate.add((long)Math.pow(10,digits-1)-1);
        String left = n.substring(0,digits/2);
        boolean even = (digits%2==0);
        char midChar = n.charAt(digits/2);
        if(even){
            String newNPlus1 =  String.valueOf(Long.valueOf(left)+1);
            String newNPlus0 =  String.valueOf(Long.valueOf(left));
            String newNPlusMinus1 =  String.valueOf(Long.valueOf(left)-1);
            if(newNPlus1.length()==left.length()){
                StringBuilder re = new StringBuilder(newNPlus1);
                newNPlus1 = newNPlus1+re.reverse().toString();
                candidate.add(Long.valueOf(newNPlus1));
            }
            if(newNPlus0.length()==left.length()){
                StringBuilder re = new StringBuilder(newNPlus0);
                newNPlus0 = newNPlus0+re.reverse().toString();
                candidate.add(Long.valueOf(newNPlus0));
            }
            if(newNPlusMinus1.length()==left.length()){
                StringBuilder re = new StringBuilder(newNPlusMinus1);
                newNPlusMinus1 = newNPlusMinus1+re.reverse().toString();
                candidate.add(Long.valueOf(newNPlusMinus1));
            }
        }
        else{
            String newNPlus1 =  String.valueOf(Long.valueOf(n.substring(0,digits/2+1))+1);
            String newNPlus0 =  String.valueOf(Long.valueOf(n.substring(0,digits/2+1)));
            String newNPlusMinus1 =  String.valueOf(Long.valueOf(n.substring(0,digits/2+1))-1);
            if(newNPlus1.length()==left.length()+1){
                StringBuilder re = new StringBuilder(newNPlus1);
                newNPlus1 = newNPlus1+re.reverse().toString().substring(1);
                candidate.add(Long.valueOf(newNPlus1));
            }
            if(newNPlus0.length()==left.length()+1){
                StringBuilder re = new StringBuilder(newNPlus0);
                newNPlus0 = newNPlus0+re.reverse().toString().substring(1);
                candidate.add(Long.valueOf(newNPlus0));
            }
            if(newNPlusMinus1.length()==left.length()+1){
                StringBuilder re = new StringBuilder(newNPlusMinus1);
                newNPlusMinus1 = newNPlusMinus1+re.reverse().toString().substring(1);
                candidate.add(Long.valueOf(newNPlusMinus1));
            }
        }


        long res = 0;
        long diff = Long.MAX_VALUE;
        for (Long aLong1 : candidate) {
            if(aLong==aLong1)
                continue;
            if(Math.abs(aLong1-aLong)<diff){
                res = aLong1;
                diff = Math.abs(aLong1-aLong);
            }
            else if(Math.abs(aLong1-aLong)==diff)
                res = Math.min(res,aLong1);
        }
        return String.valueOf(res);
    }


    public int longestLine2(int[][] M) {
        int n = M.length, max = 0;
        if (n == 0) return max;
        int m = M[0].length;
        int[][][] dp = new int[n][m][4];
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++) {
                if (M[i][j] == 0) continue;
                for (int k=0;k<4;k++) dp[i][j][k] = 1;
                if (j > 0 && M[i][j-1] == 1) dp[i][j][0] += dp[i][j-1][0]; // horizontal line
                if (j > 0 && i > 0 &&  M[i-1][j-1] == 1) dp[i][j][1] += dp[i-1][j-1][1]; // diagonal line
                if (i > 0 && M[i-1][j] == 1) dp[i][j][2] += dp[i-1][j][2]; // vertical line
                if (j < m-1 && i > 0 &&  M[i-1][j+1] == 1) dp[i][j][3] += dp[i-1][j+1][3]; // anti-diagonal line
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
                max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
            }
        return max;
    }

    public int longestLine(int[][] M) {
        if(M.length==0||M[0].length==0)
            return 0;
        int max = 0;
        int rol = M.length;
        int col = M[0].length;
        int MAX = Math.max(rol,col);
        for (int i = 0; i < rol; i++) {
            for (int j = 0; j < col; j++) {
                if(M[i][j]==0||Math.max(rol-i,col-j)<=max)
                    continue;
                max = Math.max(max,longestLine(M,i,j));
                if(max==MAX)
                    return max;
            }
        }
        return max;
    }

    private int longestLine(int[][] M, int i, int j) {
        int ii = i, jj = j;
        int rol = M.length;
        int col = M[0].length;
        int count = 1;
        int countDir = 1;
        //horizontal
        while(jj<col-1){
            if(M[i][++jj]==1)
                countDir++;
            else
                break;
        }
        count = Math.max(count,countDir);
        //vertical
        countDir = 1;
        ii = i;
        jj = j;
        while(ii<rol-1){
            if(M[++ii][jj]==1)
                countDir++;
            else
                break;
        }
        count = Math.max(count,countDir);
        //diagonal
        countDir = 1;
        ii = i;
        jj = j;
        while(ii<rol-1&&jj<col-1){
            if(M[++ii][++jj]==1)
                countDir++;
            else
                break;
        }
        count = Math.max(count,countDir);
        //anti-diagonal
        countDir = 1;
        ii = i;
        jj = j;
        while(ii<rol-1&&jj>0){
            if(M[++ii][--jj]==1)
                countDir++;
            else
                break;
        }
        count = Math.max(count,countDir);
        return count;
    }


    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i+=2)
            sum+=nums[i];
        return sum;
    }
    /**
     * Given a binary tree, return the tilt of the whole tree.

     The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

     The tilt of the whole tree is defined as the sum of all nodes' tilt.

     Example:
     Input:
         1
       /  \
     2     3
     Output: 1
     Explanation:
     Tilt of node 2 : 0
     Tilt of node 3 : 0
     Tilt of node 1 : |2-3| = 1
     Tilt of binary tree : 0 + 0 + 1 = 1
     Note:

     The sum of node values in any subtree won't exceed the range of 32-bit integer.
     All the tilt values won't exceed the range of 32-bit integer.
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    private int diff;

    public int findTilt(TreeNode root) {
        //replace val to sum
        calculSum(root);
        traverse(root);
        return diff;
    }

    private void traverse(TreeNode root) {
        if(root == null)
            return;
        int l = root.left==null?0:root.left.val
                ,r = root.right==null?0:root.right.val;
        diff+=Math.abs(l-r);
        traverse(root.left);
        traverse(root.right);
    }

    private void calculSum(TreeNode root) {
        if(root==null)
            return;
        if(root.left!=null){
            calculSum(root.left);
            root.val = root.val + root.left.val;
        }
        if(root.right!=null) {
            calculSum(root.right);
            root.val = root.val + root.right.val;
        }
    }
    /**
     *Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

     Note:
     The number of people is less than 1,100.

     Example

     Input:
     [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

     Output:
     [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

     g1:
     4->4 5->0,2 6->1 7->0,1
     g2
     5,7->0 6,7->1 5->2 4->4
     */
    /**
     * Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
     For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return o2[0]-o1[0];
                return o1[1]-o2[1];
            }
        });
        List<int[]> res = new ArrayList<>();
        for (int[] person : people)
            res.add(person[1],person);
        return res.toArray(new int[people.length][]);
    }

    class Tuple{
        int i,j;

        public Tuple(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    /**
     * The runtime of this solution is clearly O(n), using linear space (which can be easily optimized to O(1) though). Now, let's see how to further improve the runtime.

     In fact, if we treat f[i][][] and f[i-1][][] as two vectors, we can represent the recurrence of f[i][j][k] as follows:

     f[i][0][0]   | 0 0 1 0 0 0 |   f[i-1][0][0]
     f[i][0][1]   | 1 0 1 0 0 0 |   f[i-1][0][1]
     f[i][0][2] = | 0 1 1 0 0 0 | * f[i-1][0][2]
     f[i][1][0]   | 0 0 1 0 0 1 |   f[i-1][1][0]
     f[i][1][1]   | 0 0 1 1 0 1 |   f[i-1][1][1]
     f[i][1][2]   | 0 0 1 0 1 1 |   f[i-1][1][2]
     Let A be the matrix above, then f[n][][] = A^n * f[0][][], where f[0][][] = [1 1 1 1 1 1]. The point of this approach is that we can compute A^n using exponentiating by squaring (thanks to @StefanPochmann for the name correction), which will take O(6^3 * log n) = O(log n) time. Therefore, the runtime improves to O(log n), which suffices to handle the case for much larger n, say 10^18.
     Update: The final answer is f[n][1][2], which involves multiplying the last row of A^n and the column vector [1 1 1 1 1 1]. Interestingly, it is also equal to A^(n+1)[5][2] as the third column of A is just that vector. Credit to @StefanPochmann.
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        int[][] A = {
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 1},
        };
        int res[][] = new int[A.length][A[0].length];
        for (int i = 0;i<A.length;i++)
            res[i][i] = 1;
        n++;
        while(n>0){
            if(n%2==1)
                res = mul(res,A);
            A = mul(A,A);
            n/=2;
        }
        return res[5][2];
    }

    private int[][] mul(int[][] A,int[][] B)
    {
        if(A[0].length!=B.length)
            throw new RuntimeException();
        int row = A.length;
        int col = B[0].length;
        int M[][] = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                long sum = 0;
                for (int k = 0; k < B.length; k++) {
                   sum = (sum+(long) A[i][k]*B[k][j])%1000000007;
                }
                M[i][j] = (int) sum;
            }
        }
        return M;
    }

    /**
     *
     Input: "abc", "xyz"
     Output: "zyxcba"
     Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
     where '-' represents the looped status.
     The answer string came from the fourth looped one,
     where you could cut from the middle and get "zyxcba".

     * @param strs
     * @return
     */
    public String splitLoopedString(String[] strs) {
        char maxCharacher = 0;
        String strsMax[] = new String[strs.length];
        for (int i = 0; i < strsMax.length; i++) {
            boolean needReverse = false;
            char[] cs = strs[i].toCharArray();
            if(maxCharacher!='z')
                for (char c : cs)
                    maxCharacher = (char)Math.max(maxCharacher,c);
            for (int j = 0; j < cs.length/2; j++) {
                if(needReverse||cs[cs.length-1-j]>cs[j]) {
                    if(!needReverse)
                        needReverse = true;
                    char temp = cs[j];
                    cs[j] = cs[cs.length-1-j];
                    cs[cs.length-1-j]=temp;
                }
                else if(!needReverse&&cs[cs.length-1-j]<cs[j]){
                    break;
                }
            }
            strsMax[i] = needReverse?new String(cs):strs[i];
        }
        String maxConcat = null;
        StringBuilder temp = null;
        for (int i=0;i<strs.length;i++)
            if(strs[i].indexOf(maxCharacher)!=-1){
                maxConcat = max(i,maxCharacher,maxConcat,strs,strsMax);
                //reverse current
                strs[i] = new StringBuilder(strs[i]).reverse().toString();
                maxConcat = max(i,maxCharacher,maxConcat,strs,strsMax);
            }
        return maxConcat;
    }

    private String max(int i,char maxCharacher,String maxConcat, String[] strs, String[] strsMax) {
        StringBuilder temp = new StringBuilder();
        int begin = strs[i].indexOf(maxCharacher);
        while (begin!=-1) {
            for (int j = begin; j < strs[i].length(); j++)
                temp.append(strs[i].charAt(j));
            for (int j = i + 1; j < strs.length; j++)
                temp.append(strsMax[j]);
            for (int j = 0; j < i; j++)
                temp.append(strsMax[j]);
            for (int j = 0; j < begin; j++)
                temp.append(strs[i].charAt(j));
            maxConcat = compare(temp.toString(), maxConcat);
            temp = new StringBuilder();
            begin = strs[i].indexOf(maxCharacher,begin+1);
        }
        return maxConcat;
    }

    private String compare(String s, String maxConcat) {
        if(maxConcat == null)
            return s;
        for (int i = 0; i < s.length(); i++)
            if(s.charAt(i)>maxConcat.charAt(i))
                return s;
            else if(s.charAt(i)<maxConcat.charAt(i))
                return maxConcat;
        return maxConcat;
    }

    public String optimalDivision(int[] nums) {
        if(nums==null||nums.length==0)
            return "";
        StringBuilder sb = new StringBuilder();
        if(nums.length == 1)
            return sb.append(nums[0]).toString();
        if(nums.length == 2)
            return sb.append(nums[0]).append("/")
                    .append(nums[1]).toString();
        for (int i = 0; i < nums.length; i++) {
            if(i==1)
                sb.append("(");
            sb.append(nums[i]);
            if(i!=nums.length-1)
                sb.append("/");
            else
                sb.append(")");
        }
        return sb.toString();
    }
    public boolean checkRecord(String s) {
        int absentCount = 0;
        int continuousLate = 0;
        for (char c : s.toCharArray()) {
            if(c=='A'){
                if(absentCount>0)
                    return false;
                else {
                    absentCount = 1;
                    continuousLate = 0;
                }
            }
            else if(c=='L'){
                if(continuousLate==2)
                    return false;
                else
                    continuousLate++;
            }
            else
                continuousLate = 0;
        }
        return true;
    }

    private boolean lackDgigit(String s)
    {
        for (char c : s.toCharArray())
            if(Character.isDigit(c))
                return false;
        return true;
    }
    private boolean lackLowerCase(String s)
    {
        for (char c : s.toCharArray())
            if(Character.isLowerCase(c))
                return false;
        return true;
    }
    private boolean lackUpperCase(String s)
    {
        for (char c : s.toCharArray())
            if(Character.isUpperCase(c))
                return false;
        return true;
    }

    public int countBattleships(char[][] board) {
        char x= 'X';
        int sum = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++) {
                if ((i >= 1 && board[i - 1][j] == x) || (j >= 1 && board[i][j - 1] == x))
                    continue;
                if (board[i][j] == x)
                    sum++;
            }
        return sum;
    }


    public int calculateMinimumHP(int[][] dungeon) {
        int row =dungeon.length;
        int col =dungeon[0].length;
        for (int i = row-1; i >= 0 ; i--) {
            for (int j = col-1; j >= 0; j--) {
                if(i==row-1&&j==col-1)
                    dungeon[i][j] = Math.max(1,1-dungeon[i][j]);
                else if(i==row-1)
                    dungeon[i][j] = Math.max(1,dungeon[i][j+1]-dungeon[i][j]);
                else if(j==col-1)
                    dungeon[i][j] = Math.max(1,dungeon[i+1][j]-dungeon[i][j]);
                else {
                    dungeon[i][j] = Math.max(1,
                            Math.min(dungeon[i+1][j]-dungeon[i][j],dungeon[i][j+1]-dungeon[i][j]));
                }
            }
        }
        return dungeon[0][0];
    }

    /**
     * @param m typeNum
     * @param n pickNum
     * @return
     *
     * base case:
     * 1.ss[1][n] = 1
     * 2.ss[m][0] = 1
    ss[m+1][n] = ss[m][n]+ss[m][n-1]+..+ss[m][0] ;
     */
    public int Carl_sks(int m,int n){
        int[][] ss = new int[m+1][n+1];
        for (int i = 1; i < ss.length; i++) {
            for (int j = 0; j < ss[i].length; j++) {
                if(i==1||j==0) {
                    ss[i][j] = 1;
                    continue;
                }
                for (int k = 0; k <= j; k++) {
                    ss[i][j] += ss[i-1][k];
                }
            }
        }
        return ss[m][n];
    }
    /**
     * Given an array of integers A and let n to be its length.

     Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

     F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

     Calculate the maximum value of F(0), F(1), ..., F(n-1).

     Note:
     n is guaranteed to be less than 105.

     Example:

     A = [4, 3, 2, 6]

     F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

     So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
     n = A.length-1
     f0 -> 0 1 2 .. n-1 n
     f1 -> 1 2 3 ..  n  0
     f2 -> 2 3 .. n  0  1
     f[k]-> A[n-k]*n
     f[k+1]-f[k] = sum - (n+1)*A[n-k]
     * @param n
     * @return
     */
    public int maxRotateFunction(int[] A) {
        if(A.length==0)
            return 0;
        int n = A.length;
        int[] FK = new int[n];
        int i = 0;
        int sum = 0;
        int bonus = 0,max = 0;
        for (int ii=0;ii<n ; ii++) {
            sum += A[ii];
            bonus += ii*A[ii];
        }
        FK[0] = bonus;
        max = FK[0];
        for (int j = 0; j < FK.length-1; j++) {
            int diff = sum - n * A[n - 1 - j];
            FK[j + 1] = FK[j] + diff;
            max=Math.max(max,FK[j + 1]);
        }
        return max;
    }

    public int lastRemaining(int n) {
        int head = 1;
        int remaining = n;
        int diff = 1;
        while (remaining>1){
            if (remaining%2==0)
                head += diff * (remaining - 1);
            else
                head += diff * (remaining - 2);
            diff = -2*diff;
            remaining/=2;
        }
        return head;
    }
    /**
     *
     Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
     For example,
     Given
     [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
      l                     r
     [0,1,0,2,1,0,1,3,2,1,2,1]
        l                   r
     [0,1,1,2,1,0,1,3,2,1,2,1]  +1
          l                 r
     [0,1,1,2,1,0,1,3,2,1,2,1]
          l               r
     [0,1,1,2,1,0,1,3,2,1,2,1]
            l             r
     [0,1,1,2,2,0,1,3,2,1,2,1]  +1
              l           r
     [0,1,1,2,2,0,1,3,2,2,2,1]  +1
              l         r
     [0,1,1,2,2,2,1,3,2,2,2,1]  +2
                l       r
     [0,1,1,2,2,2,1,3,2,2,2,1]
                l     r
     [0,1,1,2,2,2,2,3,2,2,2,1]  +1
                  l   r
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height==null||height.length==0)
            return 0;
        int l = 0,r = height.length-1;
        int sum = 0;
        int temp = 0;
        int next = 0;
        while(l!=r){
            if(height[l]<=height[r]){
                temp = l;
                next = ++l;
            }else{
                temp = r;
                next = --r;
            }
            if(height[temp]-height[next]>0){
                sum+=height[temp]-height[next];
                height[next]=height[temp];
            }
        }
        return sum;
    }

    public int nextGreaterElement(int n) {
        int[] digits = generateDigits(n);
        int newNumber = -1;
        for (int j = 1; j < digits.length; j++)
            for (int k = 0; k < j; k++)
                if (digits[j] < digits[k]) {
                    int temp = digits[k];
                    digits[k] = digits[j];
                    digits[j] = temp;
                    Arrays.sort(digits, 0, j);
                    reverseArray(digits, 0, j);
                    if (testIsOverInt32(digits))
                        return newNumber;
                    newNumber = newNumber(digits);
                    return newNumber;
                }
        return newNumber;
    }

    private void reverseArray(int[] digits, int i, int j) {
        for(int index = i;index<(j-1+i)/2;index++){
            int temp = digits[index];
            digits[index] = digits[i+j-1-index];
            digits[i+j-1-index] = temp;
        }
    }

    private int newNumber(int[] digits) {
        int rs = 0;
        for (int i = digits.length-1;i>=0;i--) {
            rs = rs*10+digits[i];
        }
        return rs;
    }

    private boolean testIsOverInt32(int[] digits) {
        int int_32[] = generateDigits(Integer.MAX_VALUE);
        if(digits.length<int_32.length)
            return false;
        for (int i = 0; i < int_32.length; i++)
            if(digits[i]>int_32[i])
                return true;
        return false;
    }

    private int[] generateDigits(int n){
        int digits[] = new int[10];
        int i = 0;
        while(n!=0){
            digits[i++] = n%10;
            n/=10;
        }
        int[] res = new int[i];
        System.arraycopy(digits,0,res,0,i);
        return res;
    }

    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer,Integer> map = new HashMap();
        for (List<Integer> list : wall) {
            int sum = 0;
            for (int i = 0;i<list.size()-1;i++) {
                sum+=list.get(i);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max,entry.getValue());
        }
        return wall.size()-max;
    }

    public String reverseWords(String s) {
        String gs[] = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int index = 0;index<gs.length;index++) {
            char[] cs = gs[index].toCharArray();
            for (int i = cs.length-1; i >= 0; i--)
                sb.append(cs[i]);
            if(index!=gs.length-1)
                sb.append(" ");
        }
        return sb.toString();
    }

    public boolean validUtf8(int[] data) {
        int prefix = 2;
        int case1 = 0;
        int case2 = 6;
        int case3 = 14;
        int case4 = 30;
        for(int i=0;i<data.length;i++){
            if(data[i]>>7==case1)
                continue;
            else if(data[i]>>5==case2) {
                if (i+1>=data.length||data[++i] >> 6 != prefix)
                    return false;
            }
            else if(data[i]>>4==case3){
                if(i+2>=data.length||data[++i]>>6!=prefix||data[++i]>>6!=prefix)
                    return false;
            }
            else if(data[i]>>3==case4){
                if(i+3>=data.length||data[++i]>>6!=prefix||data[++i]>>6!=prefix||data[++i]>>6!=prefix)
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    private char[] hex={'0','1','2','3','4','5',
            '6','7','8','9','a','b','c','d','e','f'};

    public String toHex(int num) {
        if(num==0)
            return "0";
        Stack<Character> s = new Stack<>();
        while(num!=0){
            s.push(hex[num&15]);
            num>>>=4;
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()){
            char c = s.pop();
            if(c=='0'&&sb.length()==0)
                continue;
            sb.append(c);
        }
        return sb.toString();
    }

    public boolean splitArray(int[] nums) {
        int length = nums.length;
        if(length<7)
            return false;
        int[] sum = new int[length];
        sum[0] = nums[0];
        for (int i = 1; i < length; i++)
            sum[i] = sum[i-1]+nums[i];
        int head_quarter = 0;
        int l,m,r;
        HashSet<Integer> quarterSum = new HashSet<>();
        for (m = 3; m <= length-4; m++) {
            quarterSum.clear();
            for (l=1;l<=m-2;l++)
                if(sum[l-1]==sum[m-1]-sum[l])
                    quarterSum.add(sum[l-1]);
            for (r=m+2;r<=length-2;r++)
                if(sum[r-1]-sum[m]==sum[length-1]-sum[r]&&quarterSum.contains(sum[r-1]-sum[m]))
                    return true;
        }
        return false;
    }
    /**
     *[[1,0,0,1],
     * [0,1,1,0],
     * [0,1,1,1],
     * [1,0,1,1]]
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        HashSet<Integer> inCircle = new HashSet<>();
        int num = M.length;
        int cs  = 0;
        for (int i = 0; i < num; i++) {
            if(!inCircle.contains(i)) {
                cs++;
                inCircle.add(i);
                addAjacent(inCircle,i,M);
            }
        }
        return cs;
    }

    private void addAjacent(HashSet<Integer> inCircle, int i, int[][] M) {
        for (int j = 0; j < M.length; j++)
            if (M[i][j]==1&&!inCircle.contains(j)) {
                inCircle.add(j);
                addAjacent(inCircle,j,M);
            }
    }


    /**
     * ..
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (s1,s2)->s2.length()-s1.length());
        outer:
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length; j++)
                if(i!=j&&isSub(strs[i],strs[j]))
                    continue outer;
            return strs[i].length();
        }
        return -1;
    }

    /**
     * test if str1 is subsequence of str2
     * @param str1
     * @param str2
     * @return
     */
    private boolean isSub(String str1, String str2) {
        if(str1.length()>str2.length())
            return false;
        int i = 0;
        for (int j = 0;i<str1.length() && j < str2.length(); j++)
            if(str1.charAt(i)==str2.charAt(j))
                i++;
        if(i==str1.length())
            return true;
        return false;
    }

    /**
     * Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings.
     * The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

     A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

     The input will be two strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

     Example 1:
     Input: "aba", "cdc"
     Output: 3
     Explanation: The longest uncommon subsequence is "aba" (or "cdc"),
     because "aba" is a subsequence of "aba",
     but not a subsequence of any other strings in the group of two strings.
     * @param nums
     * @return
     */
    public int findLUSlength(String a, String b) {
        int la = a.length();
        int lb = b.length();
        if(la!=lb)
            return la>lb?la:lb;
        for (int i = 0; i < la; i++)
             if(a.charAt(i)!=b.charAt(i))
                 return la-i;
        return -1;
    }

    public int lengthOfLIS_v2(int[] nums) {
        Arrays.binarySearch(new int[]{1},0,1,1);
        return 0;
    }
    //**O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums.length==0)
            return 0;
        int size = nums.length;
        int[] dp = new int[size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < i; j++)
                if(nums[i]>nums[j])
                    dp[i]=Math.max(dp[i],dp[j]+1);
        int max = 0;
        for (int i : dp)
            max = Math.max(i,max);
        return max+1;
    }

    public int findMinStep(String board, String hand) {
        return 0;
    }

    public int findMinArrowShots(int[][] points) {
        if(points==null||points.length==0)
            return 0;
        Arrays.sort(points, Comparator.comparingInt(t -> t[0]));
        int arrow = 1;
        int left = Integer.MIN_VALUE,right = Integer.MAX_VALUE;
        for (int[] point : points) {
            if(point[0]<=right){
                left=Math.max(left,point[0]);
                right=Math.min(right,point[1]);
            }
            else{
                arrow++;
                left = point[0];
                right = point[1];
            }
        }
        return arrow;
    }

    private void show2DArray(int[][] points){
        for (int[] point : points) {
            for (int i : point) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    //#546

    /**
     Given several boxes with different colors represented by different positive numbers.
     You may experience several rounds to remove boxes until there is no box left.
     Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
     Find the maximum points you can get.

     [1, 3, 2, 2, 2, 3, 4, 3, 1]
     ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
     ----> [1, 3, 3, 3, 1] (1*1=1 points)
     ----> [1, 1] (3*3=9 points)
     ----> [] (2*2=4 points)

     dp[i][j][k] ==>
     */
    public int removeBoxes(int[] boxes) {
        return 1;
    }
    //#537
    public String complexNumberMultiply(String a, String b) {
        String[] ac = a.split("\\+");
        String[] bc = b.split("\\+");
        int aa = Integer.valueOf(ac[0]);
        int ab = Integer.valueOf(ac[1].substring(0,ac[1].length()-1));
        int ba = Integer.valueOf(bc[0]);
        int bb = Integer.valueOf(bc[1].substring(0,bc[1].length()-1));
        int real = aa*ba-ab*bb;
        int imagine = aa*bb+ab*ba;
        return String.valueOf(real)+"+"+String.valueOf(imagine)+"i";
    }

    public boolean checkPerfectNumber(int num) {
        if(num==1)
            return false;
        List<Integer> divisors = new LinkedList<>();
        for (int i = 2; i <= (int)Math.sqrt(num); i++)
            if(num%i==0){
                divisors.add(i);
                if(num/i>i)
                    divisors.add(num/i);
            }
        int sum = 1;
        for (Integer divisor : divisors)
            sum+=divisor;
        return sum == num;
    }

    public int findPairs(int[] nums, int k) {
        if(k<0)
            return 0;
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            int count = map.getOrDefault(num,0);
            map.put(num,++count);
        }
        int count = 0;
        if(k==0){
            for (Map.Entry<Integer, Integer> integerEntry : map.entrySet()) {
                if(integerEntry.getValue()>=2)
                    count++;
            }
        }
        else
            for (Map.Entry<Integer, Integer> integerEntry : map.entrySet()) {
                int need = integerEntry.getKey()+k;
                int need2 = integerEntry.getKey()-k;
                if(map.get(need)!=null){
                    count++;
                    integerEntry.setValue(null);
                }
                if(map.get(need2)!=null){
                    count++;
                    integerEntry.setValue(null);
                }
            }
        return count;
    }

    public int findRotateSteps(String ring, String key) {
        /**
         * current pointer in 12 clock
         * also mean index in ring
         */
        int cur_ptr = 0;
        HashMap<Character,List<Integer>> ringMap = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);
            List<Integer> list = ringMap.get(c);
            if(list==null) {
                list = new ArrayList<>();
                ringMap.put(c, list);
            }
            list.add(i);
        }
        int length = ring.length();
        int[][] memory = new int[key.length()][ring.length()];

        for (int i = 0; i < memory.length; i++)
            for (int j = 0; j < memory[0].length; j++)
                memory[i][j] = Integer.MAX_VALUE;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            List<Integer> list = ringMap.get(c);
            if(i==0)
                for (Integer integer : list)
                    memory[i][integer] = rotateCost(0,integer,length);
            else{
                for (Integer integer : list)
                    for (int j = 0; j < memory[i-1].length; j++) {
                        if(memory[i-1][j]!=Integer.MAX_VALUE)
                            memory[i][integer]=
                                    Math.min(memory[i-1][j]+rotateCost(j,integer,length),memory[i][integer]);
                    }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < memory[key.length()-1].length; i++)
            min = Math.min(min,memory[key.length()-1][i]);
        return min+key.length();
    }

    private int rotateCost(int ptr,int index,int length){
        return Math.min(Math.abs(index-ptr),length-Math.abs(index-ptr));
    }
    //
    public int getMinimumDifference_V2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        fillList2(root,list);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size()-1; i++) {
            min = Math.min(min,Math.abs(list.get(i)-list.get(i+1)));
        }
        return min;
    }

    //traverse O(n)
    private void fillList2(TreeNode root, List<Integer> list) {
        if(root.left!=null)
            fillList2(root.left,list);
        list.add(root.val);
        if(root.right!=null)
            fillList2(root.right,list);
    }

    //caclu MiniDiff in each Node
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        fillList(root,list);
        int min = Integer.MAX_VALUE;
        for (Integer integer : list)
            min = Math.min(min,integer);
        return min;
    }

    private void fillList(TreeNode root,List<Integer> list) {
        if(root==null)
            return;
        list.add(getMinimumDifferenceOfNode(root));
        fillList(root.left,list);
        fillList(root.right,list);
    }
    //O(lgn)
    private int getMinimumDifferenceOfNode(TreeNode root){
        TreeNode left = findLeft(root.left);
        TreeNode right = findRight(root.right);
        int leftDiff = Integer.MAX_VALUE;
        int rightDiff = Integer.MAX_VALUE;
        if(left!=null)
            leftDiff=Math.abs(left.val-root.val);
        if(right!=null)
            rightDiff=Math.abs(right.val-root.val);
        return Math.min(leftDiff,rightDiff);
    }

    private TreeNode findLeft(TreeNode root){
        while(root!=null){
            if(root.right!=null)
                root = root.right;
            else
                break;
        }
        return root;
    }

    private TreeNode findRight(TreeNode root){
        while(root!=null){
            if(root.left!=null)
                root = root.left;
            else
                break;
        }
        return root;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum+=nums[j];
                if(k==0&&sum==0)
                    return true;
                else if (k!=0&&sum%k==0)
                    return true;
            }
        }
        return false;
    }
    public String findLongestWord(String s, List<String> d) {
        d.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()!=o2.length())
                    return -(o1.length()-o2.length());
                for (int i = 0; i < o1.length(); i++)
                    if(o1.charAt(i)!=o2.charAt(i))
                        return (o1.charAt(i)-o2.charAt(i));
                return 0;
            }
        });
        for (String s1 : d)
            if(canDelForm(s,s1))
                return s1;
        return "";
    }

    private boolean canDelForm(String source,String dest) {
        int index = 0;
        boolean hasfind = false;
        for (int i = 0; i < dest.length(); i++) {
            hasfind = false;
            while (!hasfind && index < source.length()) {
                if (source.charAt(index) == dest.charAt(i))
                    hasfind = true;
                index++;
            }
        }
        return hasfind;
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
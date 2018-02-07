package traverse;
import java.util.*;
public class Solution {
    @Index(1)
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])){
                res[0] = i;
                res[1] = map.get(target-nums[i]);
                return res;
            }
            map.put(nums[i],i);
        }
        return null;
    }
    @Index(2)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null,node = null;
        int remainder = 0 , val;
        while (l1!=null||l2!=null||remainder!=0){
            ListNode next = new ListNode(0);
            if(head == null)
                head = next;
            else
                node.next = next;
            val = (l1!=null?l1.val:0)+(l2!=null?l2.val:0)+remainder;
            next.val = val%10;
            remainder = val/10;
            if(l1!=null)l1 = l1.next;
            if(l2!=null)l2 = l2.next;
            node = next;
        }
        return head;
    }
    @Index(3)
    public int lengthOfLongestSubstring(String s) {
        int l = 0,r = 0,res = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!set.contains(c)) {
                set.add(c);
                r = i;
                res = Math.max(res,r-l+1);
            }
            else{
                for (int j = l; j < i; j++) {
                    if(c==s.charAt(j)) {
                        l = j+1;
                        break;
                    }
                    set.remove(s.charAt(j));
                }
            }
        }
        return res;
    }
    @Index(4)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num[] = new int[nums1.length+nums2.length];
        int ln = num.length;
        int l1 = 0,l2 = 0;
        for (int i = 0; i < num.length; i++) {
            if(l1==nums1.length)
                num[i] = nums2[l2++];
            else if(l2==nums2.length)
                num[i] = nums1[l1++];
            else if(nums1[l1]<nums2[l2])
                num[i] = nums1[l1++];
            else
                num[i] = nums2[l2++];
        }
        if(ln%2==0)
            return (num[ln/2-1]+num[ln/2])/2.0;
        else
            return num[ln/2];
    }
    @Index(5)
    public String longestPalindrome(String s) {
        int max = 0;
        String res = null,tmp;
        for (int i = 0; i < s.length(); i++) {
            if(Math.min(i+1,s.length()-i)<=max/2)
                continue;
            tmp = findLst(s,i);
            if(res==null||res.length()<tmp.length()) {
                max = tmp.length();
                res = tmp;
            }
        }
        return res;
    }
    private String findLst(String s, int i) {
        String sg = "",sg2 = "";
        int j;
        for (j = 0; j >= 0 && i-j>=0 && i+j<s.length(); j++)
            if(s.charAt(i-j)!=s.charAt(i+j))
                break;
        sg = s.substring(i-j+1,i+j);
        for (j = 0; j >= 0 && i-j>=0 && i+1+j<s.length(); j++)
            if(s.charAt(i-j)!=s.charAt(i+1+j))
                break;
        sg2 = s.substring(i-j+1,i+1+j);
        return sg.length()>sg2.length()?sg:sg2;
    }
    public String longestPalindromeDP(String s) {
        String max = null;
        boolean[][] table = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = true;
            if(max==null||max.length()<1)
                max = s.substring(i,i+1);
        }
        for (int i = 0; i < s.length()-1; i++)
            if(s.charAt(i)==s.charAt(i+1)) {
                table[i][i + 1] = true;
                if(max.length()<2)
                    max = s.substring(i,i+2);
            }
        for (int k = 2; k < s.length(); k++)
            for (int i = 0; i+k < s.length(); i++)
                if(table[i+1][i+k-1]&&s.charAt(i)==s.charAt(i+k)) {
                    table[i][i + k] = true;
                    if(max.length()<k+1)
                        max = s.substring(i,i+k+1);
                }
        return max;
    }
    public String longestPalindromeManacherLinear(String s){
        String t = preProcess(s);
        int R = 0 , C = 0 ;
        int[] P = new int[t.length()];
        int i_mirror;
        for (int i = 0; i < P.length; i++) {
            i_mirror = 2*C-i;
            if(R>i) P[i] = Math.min(R-i,P[i_mirror]);
            while ((i+P[i]+1)<t.length()&&(i-P[i]-1)>=0&&t.charAt(i+P[i]+1)==t.charAt(i-P[i]-1))
                P[i]++;
            if(P[i]>R-i){
                C = i;
                R = i+P[i];
            }
        }
        int maxLen = 0;
        for (int i = 0; i < P.length; i++) {
            if(P[i]>maxLen){
                maxLen = P[i];
                C = i;
            }
        }
        return s.substring((C-maxLen)/2,(C+maxLen)/2);
    }
    private String preProcess(String s) {
        if(s==null||s.length()==0)
            return "##";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            sb.append("#").append(s.charAt(i));
        sb.append("#");
        return sb.toString();
    }
    @Index(6)
    public String convert(String s, int numRows) {
        List<Character>[] lists = new List[numRows];
        for (int i = 0; i < lists.length; i++)
            lists[i] = new LinkedList<>();
        int row = 0;
        boolean down = true;
        for (int i = 0; i < s.length(); i++) {
            if(row == 0)
                down = true;
            else if(row == numRows-1)
                down = false;
            lists[row].add(s.charAt(i));
            if(down&&row+1<numRows)
                row++;
            else if(!down&&row-1>=0)
                row--;
        }
        StringBuilder sb = new StringBuilder();
        for (List<Character> list : lists) {
            for (Character character : list) {
                sb.append(character);
            }
        }
        return sb.toString();
    }
    @Index(7)
    public int reverse(int x) {
        int result = 0;
        while(x!=0){
            int newResult = result*10+(x%10);
            if (newResult/10!=result)
                return 0;
            result = newResult;
            x/=10;
        }
        return result;
    }
    @Index(8)
    public int myAtoi(String str) {
        Character flag  = null;
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c=='\n'||c=='\r'||c==' ')&&list.size()==0&&flag==null)
                continue;
            if(list.size()==0&&flag==null&&(c=='+'||c=='-')) {
                flag = c;
                continue;
            }
            if(c<'0'||c>'9')
                if(list.size()==0)
                    return 0;
                else
                    break;
            list.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : list)
            sb.append(character);
        if(list.size()==0)
            return 0;
        else if(list.size()>String.valueOf(Integer.MAX_VALUE).length()){
            if(flag==null||flag=='+')
                return Integer.MAX_VALUE;
            else
                return Integer.MIN_VALUE;
        }
        else if(list.size()==String.valueOf(Integer.MAX_VALUE).length()){
            if(flag==null||flag=='+')
                if(sb.toString().compareTo(String.valueOf(Integer.MAX_VALUE))>0)
                    return Integer.MAX_VALUE;
                else
                    return Integer.valueOf(sb.toString());
            else
                if(sb.toString().compareTo(String.valueOf(Integer.MIN_VALUE).substring(1))>0)
                    return Integer.MIN_VALUE;
                else
                    return Integer.valueOf('-'+sb.toString());
        }
        else
            if(flag==null||flag=='+')
                return Integer.valueOf(sb.toString());
            else
                return -Integer.valueOf(sb.toString());
    }
    @Index(9)
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int nx = 0,ori = x;
        while (x>0){
            nx = nx*10+x%10;
            x/=10;
        }
        return nx==ori;
    }
    @Index(10)
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())return s.isEmpty();
        if(p.length()>=2&&p.charAt(1)=='*')
            return isMatch(s,p.substring(2))||!s.isEmpty()&&(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))&&
                    isMatch(s.substring(1),p);
        else
            return !s.isEmpty()&&(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))&&
                    isMatch(s.substring(1),p.substring(1));
    }
    public boolean isMatchDP(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] tp = new boolean[m+1][n+1];
        tp[0][0] = true;
        for (int i = 1; i < n+1; i++)
            tp[0][i] = p.charAt(i-1)=='*'&&tp[0][i-2];
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(p.charAt(j-1)!='*')
                    tp[i][j] = tp[i-1][j-1]&&(p.charAt(j-1)=='.'||p.charAt(j-1)==s.charAt(i-1));
                else
                    tp[i][j] = tp[i][j-2]||(tp[i-1][j]&&(p.charAt(j-2)=='.'||p.charAt(j-2)==s.charAt(i-1)));
            }
        }
        return tp[m][n];
    }
    @Index(11)
    public int maxArea(int[] height) {
        int max = 0,l = 0 ,r= height.length - 1;
        while (l<r){
            max = Math.max(max,(r-l)*Math.min(height[l],height[r]));
            if(height[l]<height[r])
                l++;
            else
                r--;
        }
        return max;
    }
    @Index(14)
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)
            return "";
        Arrays.sort(strs,(s1,s2)->s1.length()-s2.length());
        String first = strs[0];
        int min = first.length();
        for (int i = 1; i < strs.length; i++)
            for (int j = first.length(); j >= 0; j--) {
                min = Math.min(min,j);
                if(first.substring(0,j).equals(strs[i].substring(0,j)))
                    break;
            }
        return strs[0].substring(0,min);
    }
    @Index(15)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        int low,high;
        for (int i = 0; i < nums.length-2; i++)
            if(i==0||nums[i]!=nums[i-1]){
                low =i+1;
                high = nums.length-1;
                while (low<high){
                    if(nums[low]+nums[high]==-nums[i]){
                        res.add(Arrays.asList(nums[i],nums[low],nums[high]));
                        do {low++;}while (low<nums.length-1&&nums[low]==nums[low-1]);
                        do {high--;}while (high>=0&&nums[high]==nums[high+1]);
                    }
                    else if(nums[low]+nums[high]<-nums[i])
                        low++;
                    else
                        high--;
                }
            }
        return res;
    }
    @Index(16)
    public int threeSumClosest(int[] nums, int target) {
        if(nums==null||nums.length<3) return 0;
        Arrays.sort(nums);
        int low,high,sum_closest = nums[0] + nums[1] + nums[nums.length-1],sum;
        for (int i = 0; i < nums.length - 2; i++) {
            low = i+1;
            high = nums.length-1;
            while (low<high) {
                sum = nums[i] + nums[low] + nums[high];
                sum_closest = Math.abs(sum - target)<Math.abs(sum_closest - target)
                        ? sum:sum_closest;
                if (sum < target)
                    low++;
                else if (sum > target)
                    high--;
                else
                    return target;
            }
        }
        return sum_closest;
    }
    @Index(17)
    public List<String> letterCombinations(String digits) {
        String[] cs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        HashMap<Character,char[]> map = new HashMap();
        for (int i = 0; i < cs.length; i++)
            map.put((char)(i+'2'),cs[i].toCharArray());
        List<String> list = new LinkedList<>();
        full(list,map,digits,0);
        return list;
    }
    private void full(List<String> list, HashMap<Character, char[]> map, String digits, int i) {
        if(i>=digits.length())
            return;
        List<String> newlist = new ArrayList<>(list.size()*map.get(digits.charAt(i)).length);
        if(!list.isEmpty())
            for (String s : list)
                for (char c : map.get(digits.charAt(i)))
                    newlist.add(s+c);
        else
            for (char c : map.get(digits.charAt(i)))
                newlist.add(c+"");
        list.clear();
        list.addAll(newlist);
        full(list,map,digits,i+1);
    }
    @Index(18)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length-3; i++)
            if(i==0||nums[i]!=nums[i-1])
                for (int j = i+1; j < nums.length-2; j++)
                    if(j==i+1||nums[j]!=nums[j-1]){
                        int low = j+1,high = nums.length-1;
                        while (low<high) {
                            if (nums[i] + nums[j] + nums[low] + nums[high] == target){
                                result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                                while (low<high&&nums[low]==nums[low+1])
                                    low++;
                                while (low<high&&nums[high]==nums[high-1])
                                    high--;
                                low++;high--;
                            }
                            else if(nums[i] + nums[j] + nums[low] + nums[high] < target)
                                low++;
                            else
                                high--;
                        }
                    }
        return result;
    }
    @Index(19)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int nodeNum = 0;
        ListNode node = head;
        while (node!=null) {
            nodeNum++;
            node = node.next;
        }
        if(n<1||n>nodeNum)
            return head;
        n = nodeNum - n;
        ListNode sentinal = new ListNode(0);
        sentinal.next = head;
        node = sentinal;
        while (n>0){
            node = node.next;
            n--;
        }
        node.next = node.next.next;
        return sentinal.next;
    }
    @Index(20)
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character character : s.toCharArray()) {
            if(character=='}'||character==']'||character==')') {
                if(stack.isEmpty())
                    return false;
                Character g = stack.pop();
                if(character=='}'&&g!='{')
                    return false;
                if(character==']'&&g!='[')
                    return false;
                if(character==')'&&g!='(')
                    return false;
            }
            else
                stack.push(character);
        }
        return stack.isEmpty();
    }
    @Index(21)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentinal = new ListNode(0);
        ListNode node = sentinal;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val) {
                node.next = l1;
                l1 = l1.next;
            }
            else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1!=null)
            node.next = l1;
        else if(l2!=null)
            node.next = l2;
        return sentinal.next;
    }
    @Index(22)
    public List<String> generateParenthesis(int n) {
        Stack<String> tmp = new Stack<>();
        tmp.add("");
        for (int i = 0; i < n; i++) {
            Stack<String> tmpNext = new Stack<>();
            while (!tmp.isEmpty()) {
                String z = tmp.pop();
                if (z.length() < 2 * i)
                    tmp.push(z + ")");
                tmpNext.push(z + "(");
            }
            tmp = tmpNext;
        }
        List<String> list = new ArrayList<>(tmp.size());
        while (!tmp.isEmpty()){
            String s = tmp.pop();
            while (s.length()!=2*n)
                s += ")";
            list.add(s);
        }
        return list;
    }
    @Index(23)
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pr = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode dummy = new ListNode(0),node = dummy;
        for (ListNode list : lists)
            if(list!=null)
                pr.add(list);
        while (!pr.isEmpty()){
            ListNode ld = pr.poll();
            if(ld.next!=null)
                pr.add(ld.next);
            node.next = ld;
            node = node.next;
        }
        return dummy.next;
    }
    @Index(24)
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(666);
        dummy.next = head;
        ListNode node = dummy;
        ListNode pair1,pair2,tmp;
        while (node!=null){
            pair1 = node.next;
            if(pair1!=null&&pair1.next!=null)
                pair2 = pair1.next;
            else
                break;
            tmp = pair2.next;
            pair2.next = pair1;
            node.next = pair2;
            pair1.next = tmp;
            node = node.next.next;
        }
        return dummy.next;
    }
    @Index(25)
    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        ListNode node = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (node!=null) {
            len++;
            node = node.next;
        }
        node = dummy;
        int turns = len / k;
        for (int i = 0; i < turns; i++) {
            ListNode pre,cur,tmp = null;
            //do reverse
            cur = node.next;
            pre = node;
            for (int j = 0; j < k; j++) {
                tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            node.next.next = tmp;
            tmp = node.next;
            node.next = pre;
            node = tmp;
        }
        return dummy.next;
    }
    @Index(26)
    public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0)return 0;
        int ct = 1,index = 1,st = nums[0];
        while (index<nums.length){
            if(nums[index]==st)
                index++;
            else{
                st = nums[index];
                nums[ct++] = nums[index++];
            }
        }
        return ct;
    }
    @Index(27)
    public int removeElement(int[] nums, int val) {
        int i =-1;
        for (int j = 0; j < nums.length; j++)
            if(nums[j]!=val)
                nums[++i]=nums[j];
        return i+1;
    }
    @Index(28)
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty())return 0;
        for (int i = 0; i < haystack.length()-needle.length()+1; i++) {
            int ct = 0;
            while (ct < needle.length()) {
                if (haystack.charAt(i + ct) == needle.charAt(ct))
                    ct++;
                else
                    break;
            }
            if (ct == needle.length())
                return i;
        }
        return -1;
    }
    @Index(29)
    public int divide(int dividend, int divisor) {
        if(divisor==0||(dividend==Integer.MIN_VALUE&&divisor==-1))
            return Integer.MAX_VALUE;
        int flag = dividend<0^divisor<0?-1:1;
        long divd = dividend<0?dividend*-1l:dividend;
        long divs = divisor<0?divisor*-1l:divisor;
        int res = 0,shift_bit = 0;
        while (divs<=divd){
            while (divd - (divs<<shift_bit)>=0)
                shift_bit++;
            divd -= divs<<(--shift_bit);
            res += 1<<shift_bit;
            shift_bit = 0;
        }
        return res*flag;
    }
    @Index(30)
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> loopMap = new HashMap<>();
        for (String word : words) {
            Integer i = map.getOrDefault(word,0);
            map.put(word,i+1);
        }
        int len = words[0].length(),st,ed;
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i <= s.length()-words[0].length()*words.length; i++) {
            st = i;
            ed = i + len*words.length;
            while (st<ed) {
                String g = s.substring(st, len + st);
                if (map.containsKey(g)) {
                    loopMap.put(g, loopMap.getOrDefault(g,0)+1);
                    if(loopMap.get(g)>map.get(g))
                        break;
                    st += len;
                }
                else
                    break;
            }
            loopMap.clear();
            if(st==ed)
                res.add(i);
        }
        return res;
    }
    // Sliding Window    360ms
    // ask interviewer if words is empty, should I return empty list
    public List<Integer> findSubstringGS(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
        int N = S.length(), M = L.length, K = L[0].length();
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        for (String s : L) {
            if (map.containsKey(s))   map.put(s, map.get(s) + 1);
            else                      map.put(s, 1);
        }
        String str = null, tmp = null;
        for (int i = 0; i < K; i++) {
            int count = 0;  // remark: reset count
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                if (map.containsKey(str)) {
                    if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
                    else                           curMap.put(str, 1);

                    if (curMap.get(str) <= map.get(str))    count++;
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        if(curMap.get(tmp)<map.get(tmp))count--;
                    }
                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                }else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();
        }
        return res;
    }
    @Index(31)
    public void nextPermutation(int[] nums) {
        int min = 0,index = 0;
        boolean find = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] < nums[j] && (!find || min > nums[j])) {
                    min = nums[j];
                    index = j;
                    find = true;
                }
            if (find) {
                nums[index] = nums[i];
                nums[i] = min;
                for (int j = i + 1; j < nums.length; j++)
                    for (int k = j + 1; k < nums.length; k++)
                        if (nums[j] > nums[k]) {
                            min = nums[j];
                            nums[j] = nums[k];
                            nums[k] = min;
                        }
                return;
            }
        }
        for (int i = 0; i < nums.length/2; i++) {
            min = nums[i];
            nums[i] = nums[nums.length-1-i];
            nums[nums.length-1-i] = min;
        }
    }
    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

     For "(()", the longest valid parentheses substring is "()", which has length = 2.

     Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     * "(()"
     * @param s
     * @return
     */
    @Index(32)
    public int longestValidParentheses(String s) {
        if(s==null||s.length()==0)
            return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        char[] z = s.toCharArray();
        for (int i = 0; i < z.length; i++) {
            if(z[i]=='(')
                stack.push(i);
            if(z[i]==')'){
                if(!stack.isEmpty()&&z[stack.peek()]=='(') {
                    stack.pop();
                }
                else
                    stack.push(i);
            }
        }
        if(stack.isEmpty())return z.length;
        else{
            int st;
            int ed = z.length;
            while (!stack.isEmpty()){
                st = stack.pop();
                max = Math.max(max,ed-st-1);
                ed = st;
            }
            max = Math.max(max,ed);
        }
        return max;
    }
    /**
     *  4 5 6 7 0 1 2
     * @param nums
     * @param target
     * @return
     */
    @Index(33)
    public int search(int[] nums, int target) {
        int low = 0 , high = nums.length - 1 , mid;
        while ( low < high ){
            mid = ( low + high ) / 2;
            if( nums[mid] < nums[high] )
                high = mid;
            else
                low = mid + 1;
        }
        int offset = low,rm;
        while ( low <= high ){
            mid = (low+high)/2;
            rm = (mid+offset)%(nums.length);
            if(target>nums[rm])
                low = mid + 1;
            else if((target<nums[rm]))
                high = mid - 1;
            else
                return rm;
        }
        return -1;
    }
    public int binarySearch(int[] nums, int target) {
        int low = 0,high = nums.length-1,mid;
        while (low <= high){
            mid = ( low + high) / 2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
    @Index(34)
    public int[] searchRange(int[] nums, int target) {
        int low = 0,high = nums.length - 1;
        int mid;
        int id = -1;
        while (low<=high){
            mid = (low+high)/2;
            if(target==nums[mid]) {
                id = mid;
                break;
            }
            else if(target>nums[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        if(id==-1)
            return new int[]{-1,-1};
        int l1 = low,h1 = id;
        while (l1<=h1){
            mid = (l1+h1)/2;
            if(target>nums[mid])
                l1 = mid + 1;
            else
                h1 = mid - 1;
        }
        int l2 = id,h2 = high;
        while (l2<=h2){
            mid = (l2+h2)/2;
            if(target<nums[mid])
                h2 = mid - 1;
            else
                l2 = mid + 1;
        }
        return new int[]{h1+1,l2-1};
    }
    @Index(35)
    public int searchInsert(int[] nums, int target) {
        int l = 0,h = nums.length-1,mid;
        while (l<=h){
            mid = (l+h)>>1;
            if(nums[mid] == target)
                return mid;
            else if( nums[mid] < target)
                l = mid+1;
            else
                h = mid-1;
        }
        if(h>=0&&nums[h]>target) return h;
        else return h+1;
    }
    @Index(36)
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] subs = new boolean[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if(board[i][j]!='.'){
                    int num = board[i][j] - '0' - 1;
                    if (rows[i][num]) return false;
                    else rows[i][num] = true;
                    if (cols[j][num] == true) return false;
                    else cols[j][num] = true;
                    if (subs[i / 3 * 3 + j / 3][num] == true) return false;
                    else subs[i / 3 * 3 + j / 3][num] = true;
                }
        return true;
    }
    @Index(37)
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j ,c)) {
                            board[i][j] = c;
                            if (solve(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
        return true;
    }

    private boolean isValid(char[][] board, int i, int j,char c) {
        int rowI = i/3*3,colI = j/3*3;
        for (int k = 0; k < 9; k++) {
            if(board[i][k]==c)
                return false;
            if(board[k][j]==c)
                return false;
            if(board[rowI+k/3][colI+k%3]==c)
                return false;
            if(board[rowI+k%3][colI+k/3]==c)
                return false;
        }
        return true;
    }

    @Index(38)
    public String countAndSay(int n) {
        String startTerm = "1";
        StringBuilder newTerm = new StringBuilder();
        Character z;
        int count;
        while (n>=2){
            count = 0;
            z = null;
            for (char c : startTerm.toCharArray()) {
                if(z==null){
                    z = c;
                    count++;
                }
                else{
                    if(z==c)
                        count++;
                    else {
                        newTerm.append(count).append(z);
                        z = c;
                        count = 1;
                    }
                }
            }
            newTerm.append(count).append(z);
            startTerm = newTerm.toString();
            newTerm = new StringBuilder();
            n--;
        }
        return startTerm;
    }

    @Index(39)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates,0,target,new ArrayList<>(),res);
        return res;
    }

    private void combinationSum(int[] candidates, int leftIndex, int target,List<Integer> list,List<List<Integer>> result) {
        if(target==0) {
            result.add(list);
            return;
        }
        if(candidates[leftIndex]>target)
            return;
        for (int i = leftIndex; i < candidates.length; i++) {
            List<Integer> copy = new ArrayList<>(list);
            copy.add(candidates[i]);
            combinationSum(candidates,i,target-candidates[i],copy,result);
        }
    }

    @Index(40)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        combinationSum2(candidates,0,target,new ArrayList<>(),res);
        return res;
    }

    private void combinationSum2(int[] candidates, int leftIndex, int target,List<Integer> list,List<List<Integer>> result) {
        if(target==0) {
            result.add(list);
            return;
        }
        if(leftIndex>=candidates.length||candidates[leftIndex]>target)
            return;
        Set<Integer> chosenItems = new HashSet<>();
        for (int i = leftIndex; i < candidates.length; i++) {
            if(chosenItems.contains(candidates[i]))
                continue;
            else
                chosenItems.add(candidates[i]);
            List<Integer> copy = new ArrayList<>(list);
            copy.add(candidates[i]);
            combinationSum2(candidates,i+1,target-candidates[i],copy,result);
        }
    }

    @Index(41)
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]>0&&nums[i]<=nums.length&&nums[nums[i]-1]!=nums[i]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=i+1)
                return i+1;
        }
        return nums.length+1;
    }

    @Index(42)
    public int trap(int[] height) {
        if(height==null||height.length==0)
            return 0;
        int length = height.length;
        int[] l = new int[length];
        int[] r = new int[length];
        l[0] = height[0];
        r[length-1] = height[length-1];
        for (int i = 1; i < length; i++)
            l[i] = Math.max(height[i],l[i-1]);
        for (int i = length-2; i >= 0; i--)
            r[i] = Math.max(height[i],r[i+1]);
        int ans = 0;
        for (int i = 0; i < r.length; i++) {
            ans+=Math.min(l[i],r[i])-height[i];
        }
        return ans;
    }

    @Index(43)
    public String multiply(String num1, String num2) {
        int l1 = num1.length(),l2= num2.length();
        StringBuilder rs = new StringBuilder();
        int[] pos = new int[l1+l2];
        for (int i = l1-1; i >=0; i--) {
            for (int j = l2-1; j >=0 ; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }
        for (int po : pos) {
            if(!(rs.length()==0&&po==0))
                rs.append(po);
        }
        return rs.length()==0?"0":rs.toString();
    }

    /**
     * '?' Matches any single character.
     '*' Matches any sequence of characters (including the empty sequence).

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") ¡ú false
     isMatch("aa","aa") ¡ú true
     isMatch("aaa","aa") ¡ú false
     isMatch("aa", "*") ¡ú true
     isMatch("aa", "a*") ¡ú true
     isMatch("ab", "?*") ¡ú true
     isMatch("aab", "c*a*b") ¡ú false
     * @param s
     * @param p
     * @return
     */
    @Index(44)
    public boolean isMatch2(String ss, String pp) {
        int s = 0,p = 0,star = -1,backS = 0;
        int length = ss.length();
        while (s<length){
            if(p < pp.length()&&(pp.charAt(p)=='?'||ss.charAt(s)==pp.charAt(p))){
                s++;
                p++;
            }
            else if(p < pp.length()&&pp.charAt(p)=='*'){
                star = p;
                backS = s;
                p++;
            }
            else if(star!=-1){
                s = ++backS;
                p = star + 1;
            }
            else
                return false;
        }
        while (p<pp.length()&&pp.charAt(p)=='*')
            p++;
        return p == pp.length();
    }

    @Index(45)
    public int jump(int[] nums) {
        if(nums==null||nums.length<=1)
            return 0;
        int turn = 0,i = 0,curMax = 0,nextMax = 0;
        while (i<=curMax){
            turn++;
            for (; i <= curMax; i++) {
                nextMax = Math.max(nextMax,i+nums[i]);
                if(nextMax>=nums.length-1)
                    return turn;
            }
            curMax = nextMax;
        }
        return 0;
    }

    @Index(46)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> bak = new ArrayList<>();
        for (int num : nums) {
            if(result.size()==0){
                result.add(Arrays.asList(num));
            }
            else{
                for (List<Integer> list : result) {
                    for (int i = 0; i <= list.size(); i++) {
                        List<Integer> newList = new ArrayList<>(list);
                        newList.add(i,num);
                        bak.add(newList);
                    }
                }
                result.clear();
                result.addAll(bak);
                bak.clear();
            }
        }
        return result;
    }

    @Index(47)
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result,new ArrayList<>(),nums);
        return result;
    }

    private void backTrack(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if(list.size()==nums.length) {
            List<Integer> list1 = new ArrayList<>();
            for (Integer integer : list) {
                list1.add(nums[integer]);
            }
            result.add(list1);
            return;
        }
        else{
            for (int i = 0; i < nums.length; i++) {
                if(list.contains(i))
                    continue;
                if(i-1>=0&&!list.contains(i-1)&&nums[i-1]==nums[i])
                    continue;
                list.add(i);
                backTrack(result,list,nums);
                list.remove(list.size()-1);
            }
        }
    }

    @Index(48)
    public void rotate(int[][] matrix) {
        int row = matrix.length,col = matrix[0].length;
        int tmp;
        for (int i = 0; i < row/2; i++) {
            for (int j = 0; j < col; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[row-1-i][j];
                matrix[row-1-i][j] = tmp;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = i+1; j < col; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    @Index(49)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char c[] = str.toCharArray();
            Arrays.sort(c);
            String g = new String(c);
            if(!map.containsKey(g)){
                map.put(g,new ArrayList<>());
            }
            map.get(g).add(str);
        }
        return new ArrayList<>(map.values());
    }

    @Index(50)
    public double myPow(double x, int n) {
        if(n==1)
            return x;
        else if(n==-1)
            return 1/x;
        else if(n ==0 )
            return 1;
        double g = myPow(x,n/2);
        return g*g*(n%2==0?1:n%2==1?x:1/x);
    }

    @Index(51)
    public List<List<String>> solveNQueens(int n) {
        boolean[][] ck = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        tracking(result,ck,0);
        return result;
    }

    private void tracking(List<List<String>> result, boolean[][] ck, int i) {
        if(i==ck.length){
            List<String> tmp = new ArrayList<>();
            StringBuilder sb = null;
            for (int j = 0; j < ck.length; j++) {
                sb = new StringBuilder();
                for (int k = 0; k < ck.length; k++) {
                    sb.append(ck[j][k]?'Q':'.');
                }
                tmp.add(sb.toString());
            }
            result.add(tmp);
        }
        for (int j = 0; j < ck.length; j++) {
            if(canBeQueen(ck,i,j)) {
                ck[i][j] = true;
                tracking(result,ck,i+1);
                ck[i][j] = false;
            }
        }
    }

    private boolean canBeQueen(boolean[][] ck, int i, int j) {
        int len = ck.length;
        for (int k = 0; k < ck.length; k++)
            if(ck[k][j])
                return false;
        int steps =Math.max(Math.max(i,len-i),Math.max(j,len-j));
        for (int step = 1; step <= steps; step++) {
            if (i + step < len && j + step < len && ck[i + step][j + step])
                return false;
            if (i + step < len && j - step >= 0 && ck[i + step][j - step])
                return false;
            if (i - step >= 0 && j + step < len && ck[i - step][j + step])
                return false;
            if (i - step >= 0 && j - step >= 0 && ck[i - step][j - step])
                return false;
        }
        return true;
    }


    int count = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \
        boolean[] d2 = new boolean[2 * n];   // diagonals /
        backtracking(0, cols, d1, d2, n);
        return count;
    }
    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if(row == n) count++;

        for(int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) continue;

            cols[col] = true; d1[id1] = true; d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }

    @Index(53)
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE,sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max,sum);
            if(sum<0) sum=0;
        }
        return max;
    }

    @Index(54)
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length==0||matrix[0].length==0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length,cols = matrix[0].length;
        int r = 0,l = 0;
        int upr = 0,downr = rows-1;
        int ll = 0,rl = cols-1;
        while (upr<=downr&&ll<=rl){
            if(r==upr){
                if(l==ll) {
                    for (; l <= rl; l++)
                        result.add(matrix[r][l]);
                    upr++;
                    r++;
                    l--;
                }
                else {
                    for (; r <= downr; r++)
                        result.add(matrix[r][l]);
                    rl--;
                    l--;
                    r--;
                }
            }
            if(r==downr){
                if(l==rl) {
                    for (; l >= ll; l--)
                        result.add(matrix[r][l]);
                    downr--;
                    r--;
                    l++;
                }
                else{
                    for (;r>=upr; r--)
                        result.add(matrix[r][l]);
                    ll++;
                    l++;
                    r++;
                }
            }
        }
        return result;
    }

    @Index(55)
    public boolean canJump(int[] nums) {
        int cur = 0,nx = nums[0],len = nums.length,tmp;
        while (nx<len-1){
            tmp = nx;
            for (int i = cur + 1; i <= tmp; i++)
                nx = Math.max(nx,nums[i]+i);
            if(nx==tmp)
                return false;
            cur = tmp;
        }
        return true;
    }

    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    @Index(56)
    public List<Interval> merge(List<Interval> intervals) {
        Interval[] gg = intervals.toArray(new Interval[intervals.size()]);
        Arrays.sort(gg, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        LinkedList<Interval> result = new LinkedList<>();
        for (int i = 0; i < gg.length; i++) {
            if (result.isEmpty())
                result.add(gg[i]);
            else{
                Interval ii = result.removeLast();
                if(ii.end<gg[i].start){
                    result.add(ii);
                    result.add(gg[i]);
                }
                else {
                    result.add(new Interval(ii.start, Math.max(gg[i].end,ii.end)));
                }
            }
        }
        return result;
    }

    @Index(57)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>(intervals.size());
        int ct = 0;
        for (;ct<intervals.size()&&intervals.get(ct).end<newInterval.start;ct++)
            res.add(intervals.get(ct));
        for (;ct<intervals.size()&&intervals.get(ct).start<=newInterval.end;ct++) {
            newInterval.start = Math.min(newInterval.start,intervals.get(ct).start);
            newInterval.end = Math.max(newInterval.end,intervals.get(ct).end);
        }
        res.add(newInterval);
        for (;ct<intervals.size();ct++)
            res.add(intervals.get(ct));
        return res;
    }




    @Index(60)
    public String getPermutation(int n, int k) {
        List<Integer> opts = new ArrayList<>();
        int sw = 1;
        for (int i = 1; i <= n; i++) {
            opts.add(i);
            sw*=i;
        }
        StringBuilder rs = new StringBuilder();
        int bs;
        for (int i = n; i > 0 ; i--) {
            sw/=i;
            bs = k%sw==0?k/sw-1:k/sw;
            rs.append(opts.remove(bs));
            k -= bs*sw;
        }
        return rs.toString();
    }








    @Index(66)
    public int[] plusOne(int[] digits) {
        boolean ups = false;
        for (int i = digits.length-1; i >= 0; i--) {
            if(digits[i]+1<10) {
                digits[i]++;
                return digits;
            }
            else{
                digits[i] = 0;
            }
        }
        int[] nx = new int[digits.length+1];
        nx[0] = 1;
        System.arraycopy(digits,0,nx,1,digits.length);
        return nx;
    }
    @Index(67)
    public String addBinary(String a, String b) {
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int remain = 0;
        for (int i = 1; i <= Math.min(a.length(),b.length()); i++) {
            char c1 = ac[a.length()-i];
            char c2 = bc[b.length()-i];
        }
    }


    @Index(205)
    private ListNode reverseList(ListNode head){
        ListNode node = head,pre = null ,tmp;
        while (node!=null){
            tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
        }
        return pre;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        Solution s =  new Solution();
        for (int i = 1; i <= 24; i++) {
            System.out.println(s.getPermutation(4,i));
        }
    }
}

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
char c[][] = {  {'.','.','.','9','7','4','8','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'},
            };
        System.out.println(new Solution().solve(c));
    }
}

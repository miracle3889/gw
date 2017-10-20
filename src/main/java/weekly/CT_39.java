package weekly;

import java.util.*;

/**
 * Created by xudabiao on 2017/7/11.
 */
public class CT_39 {

    public List<Integer> cheapestJump(int[] A, int B) {
        List[] i = new List[A.length];
        i[0] = Arrays.asList(1);
        for (int j = 1; j < i.length; j++) {
            if(A[j]==-1) {
                i[j] = new ArrayList();
                continue;
            }
            List<List<Integer>> listList = new ArrayList<>();
            for (int k=j-B>=0?j-B:0;k<j;k++)
                if(i[k].size()!=0)
                    listList.add(i[k]);
            i[j] = findCheapest(listList,A,j);
        }
        return i[i.length-1];
    }

    private List findCheapest(List<List<Integer>> listList, int[] a, int j) {
        if (listList.size()==0)
            return new ArrayList();
        int minCost = Integer.MAX_VALUE , cost;
        Map<Integer,List<List<Integer>>> map = new HashMap<>();
        for (List<Integer> list : listList) {
            cost = 0;
            for (Integer integer : list)
                cost+=a[integer-1];
            if(cost<=minCost){
                List<List<Integer>> ll = map.get(cost);
                if(ll==null){
                    ll = new ArrayList<>();
                    map.put(cost,ll);
                }
                ll.add(list);
                minCost = cost;
            }
        }
        List<List<Integer>> lm = map.get(minCost);
        int index = 1,min;
        while (lm.size()!=1){
            min = Integer.MAX_VALUE;
            List<List<Integer>> nlm = new ArrayList<>();
            for (List<Integer> list : lm)
                if(list.size()>index)
                    min = Math.min(min,list.get(index));
            for (List<Integer> list : lm)
                if(list.size()>index&&list.get(index)==min)
                    nlm.add(list);
            lm = nlm;
            index++;
        }
        List<Integer> list = new ArrayList<>(lm.get(0));
        list.add(j+1);
        return list;
    }

    public List<List<String>> printTree(TreeNode root) {
        return null;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums,0,nums.length-1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums,int l,int r) {
        if(l>r)
            return null;
        int maxIndex = findMaxIndex(nums,l,r);
        TreeNode treeNode = new TreeNode(nums[maxIndex]);
        treeNode.left = constructMaximumBinaryTree(nums,l,maxIndex-1);
        treeNode.right = constructMaximumBinaryTree(nums,maxIndex+1,r);
        return treeNode;
    }

    private int findMaxIndex(int[] nums,int l,int r){
        int maxIndex = l;
        for (int i = l + 1; i <= r; i++)
            if(nums[i]>nums[maxIndex])
                maxIndex = i;
        return maxIndex;
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        traverse(list,root);
        if(list.size()<2)
            return false;
        int min = list.get(0)+list.get(1);
        int max = list.get(list.size()-2)+list.get(list.size()-1);
        if(k<min||k>max)
            return false;
        for (Integer integer : list) {
            if (integer * 2 == k)
                continue;
            if (canFind(root, k - integer))
                return true;
        }
        /**
         * [334,277,507,null,285,null,678]
            1014
         */
        return false;
    }

    private boolean canFind(TreeNode root, int i) {
        if(root==null)
            return false;
        if(i==root.val)
            return true;
        else if(i<root.val)
            return canFind(root.left,i);
        else
            return canFind(root.right,i);
    }


    private void traverse(List<Integer> list, TreeNode root) {
        if(root==null)
            return;
        traverse(list,root.left);
        list.add(root.val);
        traverse(list,root.right);
    }

    public String predictPartyVictory(String senate) {
        int length = senate.length();
        boolean[] k = new boolean[length];
        while (true){
            for (int i = 0; i < length; i++) {
                if(k[i])
                    continue;
                char c = senate.charAt(i);
                int ki = findOpposite(senate,i,k);
                if(ki==-1){
                    if (c=='R')
                        return "Radiant";
                    else
                        return "Dire";
                }else
                    k[ki] = true;
            }
        }
    }

    private int findOpposite(String senate, int i, boolean[] k) {
        char c = senate.charAt(i);
        int st = i + 1 == senate.length()?0:i+1;
        if(c=='R') {
            for (int j = st; j != i; j++) {
                if (!k[j] && senate.charAt(j) == 'D')
                    return j;
                if (j == senate.length() - 1)
                    j = -1;
            }
            return -1;
        }
        if(c=='D') {
            for (int j = st; j != i; j++) {
                if (!k[j] && senate.charAt(j) == 'R')
                    return j;
                if (j == senate.length() - 1)
                    j = -1;
            }
            return -1;
        }
        return -1;
    }

    public int minSteps(int n) {
        List<Integer> k = new LinkedList<>();
        while (n>1)
            for(int i = 2;i<=n;i++)
                if(n%i==0){
                    k.add(i);
                    n/=i;
                    break;
                }
        int sum = 0;
        for (Integer integer : k)
            sum += integer;
        return sum;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> rs = new LinkedList<>();
        Map<String,Integer> map = new HashMap<>();
        postOrder(root,rs,map);
        return rs;
    }

    private String postOrder(TreeNode root, List<TreeNode> rs, Map<String, Integer> map) {
        if(root==null) return"#";
        String val = root.val+","+postOrder(root.left,rs,map)+","+postOrder(root.right,rs,map);
        if(map.getOrDefault(val,0)==1) rs.add(root);
        map.put(val,map.getOrDefault(val,0)+1);
        return val;
    }

    public String replaceWords(List<String> dict, String sentence) {
        Map<Character,List<String>> map = new HashMap<>();
        String[] strs = new String[dict.size()];
        dict.toArray(strs);
        Arrays.sort(strs,(a,b)->a.length()-b.length());
        outer:
        for (String s : dict) {
            List<String> list = map.get(s.charAt(0));
            if(list == null){
                list = new LinkedList<>();
                map.put(s.charAt(0),list);
            }
            for (String s1 : list)
                if(s.startsWith(s1))
                    continue outer;
            list.add(s);
        }
        String[] seg = sentence.split(" ");
        for (int i = 0;i<seg.length;i++) {
            String s = seg[i];
            List<String> list = map.get(s.charAt(0));
            if (list!=null)
                for (String s1 : list)
                    if(s.startsWith(s1)) {
                        seg[i] = s1;
                        break;
                    }
        }
        return String.join(" ",seg);
    }
    /**
     * [i..j]
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int ct = 0;
        for (int i = 0; i < s.length(); i++) {
            int dif;
            for(dif = 0 ; i - dif >= 0 && i + dif < s.length() ; dif++){
                if(s.charAt(i-dif)==s.charAt(i+dif))
                    continue;
                break;
            }
            ct += dif;
            for(dif = 0 ; i - dif >= 0 && i + 1 + dif < s.length() ; dif++){
                if(s.charAt(i - dif)==s.charAt(i + 1 + dif))
                    continue;
                break;
            }
            ct += dif;
        }
        return ct;
    }
    public int findLongestChain(int[][] pairs) {
        if(pairs.length==0)
            return 0;
        Arrays.sort(pairs,(a,b)->a[1]-b[1]);
        int start = pairs[0][1];
        int ct = 1;
        for (int i = 0; i < pairs.length; i++)
            if (pairs[i][0] > start) {
                ct++;
                start = pairs[i][1];
            }
        return ct;
    }
    public int[] findErrorNums(int[] nums) {
        int[] z = new int[nums.length];
        int[] re = new int[2];
        for (int i = 0; i < z.length; i++)
            z[nums[i]-1]++;
        for (int i = 0; i < z.length; i++) {
            if (z[i]==0)
                re[1] = i+1;
            else if(z[i]==2)
                re[0] = i+1;
            else
                continue;
        }
        return re;
    }
    /**
     *
     * [a1..ai-1,ai,ai+1..n]
     * (i-1)->a
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        double max_avg = Integer.MIN_VALUE;
        while (k<=nums.length){
            int sum[] = new int[nums.length-k+1];
            for (int i = 0; i < k; i++) sum[0]+=nums[i];
            int max = sum[0];
            for (int i = 1; i < nums.length-k+1; i++) {
                sum[i] = sum[i-1]-nums[i-1]+nums[i-1+k];
                max = Math.max(max,sum[i]);
            }
            max_avg = Math.max(max_avg,max*1.0/k);
            k++;
        }
        return max_avg;
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] rs = new int[n];
        Arrays.fill(rs,0);
        String couple;
        Stack<String> stringStack = new Stack<>();
        for (String s : logs) {
            if(s.contains("start"))
                stringStack.push(s);
            else if(s.contains("end")) {
                couple = stringStack.pop();
                int mid = 0;
                if(!couple.contains(":")) {
                    mid = Integer.valueOf(couple);
                    couple = stringStack.pop();
                }
                String before = couple.split(":")[0];
                int cost = Integer.valueOf(s.split(":")[2])
                        -Integer.valueOf(couple.split(":")[2])+1;
                rs[Integer.valueOf(before)] =
                        rs[Integer.valueOf(before)] + cost -mid;
                if(!stringStack.isEmpty()&&!stringStack.peek().contains(":"))
                    cost = Integer.valueOf(stringStack.pop())+cost;
                stringStack.push(String.valueOf(cost));
            }
        }
        return rs;
    }

    public boolean judgeSquareSum(int c) {
        for(int i =0;i<=(int)Math.sqrt(c/2);i++){
            int jl = (int)Math.sqrt(c-i*i);
            int jr = jl+1;
            if(jl*jl+i*i==c||jr*jr+i*i==c)
                return true;
        }
        return false;
    }

    /**
     * ["LogSystem","put","put","put","retrieve","retrieve"]
     [[],[1,"2017:01:01:23:59:59"],[2,"2017:01:01:22:59:59"],[3,"2016:01:01:00:00:00"],["2016:01:01:01:01:01","2017:01:01:23:00:00","Year"],["2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"]]

     * @param args
     */
    public static void main(String[] args) {
        /**
         * n = 2
         logs =
         ["0:start:0",
         "1:start:2",
         "1:end:5",
         "0:end:6"]
         */
//        List<String> logs = new ArrayList<String>(Arrays.asList("0:start:0",
//                "1:start:2",
//                "1:end:5",
//                "0:end:6"));
        System.out.println(new CT_39().cheapestJump(new int[]{1,0,1}
        ,2));
    }
}
class LogSystem {
    LinkedList<Node> list;
    public LogSystem() {
        list = new LinkedList();
    }

    public void put(int id, String timestamp) {
        Node node = new Node(id,timestamp);
        int l = 0,r = list.size()-1,mid;
        while (r>=l){
            mid = (l+r)/2;
            if(list.get(mid).time.compareTo(timestamp)<0)
                l = mid + 1;
            else if(list.get(mid).time.compareTo(timestamp)==0)
                break;
            else
                r = mid - 1;
        }
        list.add(l,node);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        String s_resovle = null,e_resolve=null;
        switch (gra){
            //Year:Month:Day:Hour:Minute:Second
            //"2017:01:01:23:59:59"
            case "Year":s_resovle = s.substring(0,4);e_resolve = e.substring(0,4);break;
            case "Month":s_resovle = s.substring(0,7);e_resolve = e.substring(0,7);break;
            case "Day":s_resovle = s.substring(0,10);e_resolve = e.substring(0,10);break;
            case "Hour":s_resovle = s.substring(0,13);e_resolve = e.substring(0,13);break;
            case "Minute":s_resovle = s.substring(0,16);e_resolve = e.substring(0,16);break;
            case "Second":s_resovle = s;e_resolve = e;
        }
        List<Integer> results = new LinkedList<>();
        int st = -1,ed = -1;
        for (int i = 0;i<list.size();i++)
            if(list.get(i).time.substring(0,s_resovle.length()).compareTo(s_resovle)>=0) {
                st = i;
                break;
            }
        for (int i = list.size()-1;i>=0;i--)
            if(list.get(i).time.substring(0,e_resolve.length()).compareTo(e_resolve)<=0) {
                ed = i;
                break;
            }

        if(st>=0&&ed>=st)
            for (int i = st; i <= ed; i++) results.add(list.get(i).id);
        return results;
    }
}
class Node{
    int id;
    String time;
    public Node(int id,String time){
        this.id = id;
        this.time = time;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */

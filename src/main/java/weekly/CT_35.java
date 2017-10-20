package weekly;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xudabiao on 2017/6/12.
 */
public class CT_35 {
    /**
     *  Merge Two Binary Trees My SubmissionsBack To Contest
     Given two binary trees and imagine that when you put one of them to cover the other,
     some nodes of the two trees are overlapped while the others are not.

     You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
     then sum node values up as the new value of the merged node.
     Otherwise, the NOT null node will be used as the node of new tree.

     Example 1:
     Input:
     Tree 1                     Tree 2
     1                         2
     / \                       / \
     3   2                     1   3
     /                           \   \
     5                             4   7
     Output:
     Merged tree:
     3
     / \
     4   5
     / \   \
     5   4   7
     Note: The merging process must start from the root nodes of both trees
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null)
            return t2;
        mergeHelper(t1,t2,t1);
        return t1;
    }

    private void mergeHelper(TreeNode t1, TreeNode t2, TreeNode parent) {
        if(t1!=null&&t2!=null) {
            t1.val += t2.val;
            mergeHelper(t1.left,t2.left,t1);
            mergeHelper(t1.right,t2.right,t1);
        }
        else if(t1==null&&t2!=null)
            parent.left = t2;
    }

    /**
     * 611. Valid Triangle Number My SubmissionsBack To Contest
     User Accepted: 406
     User Tried: 852
     Total Accepted: 413
     Total Submissions: 2454
     Difficulty: Medium
     Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

     Example 1:
     Input: [2,2,3,4]
     Output: 3
     Explanation:
     Valid combinations are:
     2,3,4 (using the first 2)
     2,3,4 (using the second 2)
     2,2,3
     Note:
     The length of the given array won't exceed 1000.
     The integers in the given array are in the range of [0, 1000].
     */
    public int triangleNumber(int[] nums) {
        if(nums==null||nums.length<3)
            return 0;
        Arrays.sort(nums);
        int t = 0;
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k])
                        t++;
                    else
                        break;
                }
        return t;
    }
    /**
     * 624. Maximum Distance in Arrays My SubmissionsBack To Contest
     User Accepted: 926
     User Tried: 1287
     Total Accepted: 940
     Total Submissions: 3732
     Difficulty: Easy
     Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

     Example 1:
     Input:
     [[1,2,3],
     [4,5],
     [1,2,3]]
     Output: 4
     Explanation:
     One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
     Note:
     Each given array will have at least 1 number. There will be at least two non-empty arrays.
     The total number of the integers in all the m arrays will be in the range of [2, 10000].
     The integers in the m arrays will be in the range of [-10000, 10000].
     */
    public int maxDistance(List<List<Integer>> arrays) {
        int min = arrays.get(0).get(0),max = arrays.get(0).get(arrays.get(0).size()-1);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < arrays.size(); i++) {
            res = Math.max(res,Math.abs(max-arrays.get(i).get(0)));
            res = Math.max(res,Math.abs(min-arrays.get(i).get(arrays.get(i).size()-1)));
            max = Math.max(max,arrays.get(i).get(arrays.get(i).size()-1));
            min = Math.min(min,arrays.get(i).get(0));
        }
        return res;
    }

    /**
     * 623. Add One Row to Tree
     *
     Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

     The adding rule is: given a positive integer depth d,
     for each NOT null tree nodes N in depth d-1, create two tree nodes with value v
     as N's left subtree root and right subtree root.
     And N's original left subtree should be the left subtree of the new left subtree root,
     its original right subtree should be the right subtree of the new right subtree root.
     If depth d is 1 that means there is no depth d-1 at all,
     then create a tree node with value v as the new root of the whole original tree,
     and the original tree is the new root's left subtree.

     Example 1:
     Input:
     A binary tree as following:
     4
     /   \
     2     6
     / \   /
     3   1 5

     v = 1

     d = 2

     Output:
     4
     / \
     1   1
     /     \
     2       6
     / \     /
     3   1   5

     Example 2:
     Input:
     A binary tree as following:
     4
     /
     2
     / \
     3   1

     v = 1

     d = 3

     Output:
     4
     /
     2
     / \
     1   1
     /     \
     3       1
     Note:
     The given d is in range [1, maximum depth of the given tree + 1].
     The given binary tree has at least one tree node.
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d==1){
            TreeNode rt = new TreeNode(v);
            rt.left = root;
            return rt;
        }
        int cd = 1;
        addOneRow(root,v,d,cd);
        return root;
    }

    private void addOneRow(TreeNode root, int v, int d, int cd) {
        if(root==null)
            return;
        if(cd==d-1){
            TreeNode nl = new TreeNode(v);
            TreeNode nr = new TreeNode(v);
            nl.left = root.left;
            nr.right = root.right;
            root.left = nl;
            root.right = nr;
            return;
        }
        else {
            addOneRow(root.left, v, d, cd + 1);
            addOneRow(root.right, v, d, cd + 1);
        }
    }

    public int smallestFactorization(int a) {
        if(a==1)
            return 1;
        List<Integer> factors = new ArrayList<>();
        boolean cz = false;
        while (true){
            for (int i = 9; i > 1; i--)
                if (a % i == 0) {
                    a /= i;
                    factors.add(i);
                    cz = true;
                    break;
                }
            if(cz){
                cz = false;
                continue;
            }
            else
                break;
        }
        if(a!=1)
            return 0;
        int[] qa = new int[factors.size()];
        for (int i = 0; i < qa.length; i++) qa[i] = factors.get(i);
        Arrays.sort(qa);
        if(qa.length>10)
            return 0;
        long res = 0;
        for (int i= 0 ;i<qa.length;i++) res = res * 10 + qa[i];
        if(res>Integer.MAX_VALUE)
            return 0;
        else
            return (int)res;
    }

    /**
     * 621. Task Scheduler
     Given a char array representing tasks CPU need to do.
     It contains capital letters A to Z where different letters represent different tasks.
     Tasks could be done without original order. Each task could be done in one interval.
     For each interval, CPU could finish one task or just be idle.

     However, there is a non-negative cooling interval n that means between two same tasks,
     there must be at least n intervals that CPU are doing different tasks or just be idle.

     You need to return the least number of intervals the CPU will take to finish all the given tasks.

     Example 1:
     Input: tasks = ['A','A','A','B','B','B'], n = 2
     Output: 8
     Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
     Note:
     The number of tasks is in the range [1, 10000].
     The integer n is in the range [0, 100].
     */
    /**
     * ['A'*3,'B'*2,'C'*3]  3
     * A>C>B>_>A>C>B>_>A>C
     A>
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] ct = new int[26];
        int[] intervals = new int[26];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) ct[tasks[i] - 'A']++;
        int max = 0,index=-1,loops = 0,total = tasks.length;
        outer:
        while (total>0){
            for (int i = 0; i < intervals.length; i++)
                if(intervals[i]==0&&ct[i]>max){
                    index = i;
                    max = ct[i];
                }
            if(index!=-1){
                ct[index]--;
                intervals[index] = n;
                total--;
            }
            for (int i = 0; i < intervals.length; i++)
                if(i!=index&&intervals[i]>0)
                    intervals[i]--;
            //reset for next loop
            {
                index = -1;
                max = 0;
            }
            loops++;
        }
        return loops;
    }
    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(Comparable.class.isAssignableFrom(Integer.class));
    }
}

package weekly;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.PriorityQueue;

/**
 * Created by xudabiao on 2017/6/26.
 */
public class CT_38 {
    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(numberFormat.format(123456.55));
    }
    public int maimumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int zz = nums[len-1]*nums[len-2]*nums[len-3];
        int negct = 0;
        for (int num : nums) if(num<0) negct++;
        if(negct<=1) return zz;
        return Math.max(nums[0]*nums[1]*nums[len-1],zz);
    }
    /**
     * 630. Course Schedule III My SubmissionsBack To Contest
     User Accepted: 25
     User Tried: 659
     Total Accepted: 25
     Total Submissions: 2084
     Difficulty: Medium
     There are n different online courses numbered from 1 to n.
     Each course has some duration(course length) t and closed on dth day.
     A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

     Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

     Example:
     Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]

     answer rules:
     [a0,b0]£¬[a1,b1],[a2,b2]..etc
     for i = 0->n
     a0+..+ai<=bi
     -------
     if ai>= aj and bi>=bj
     [ai,bi]...[aj,bj]


     0=100=200=250=================1100==1200=1250==1300=======================3200
     Output: 3
     Explanation:
     There're totally 4 courses, but you can take 3 courses at most:
     First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
     Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
     Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
     The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1,o2)->o1[1]-o2[1]);
        int cur = 0;
        PriorityQueue<Integer> xx = new PriorityQueue<>((a,b)->b-a);
        for (int[] course : courses) {
            xx.offer(course[0]);
            cur += course[0];
            if(cur>course[1])
                cur-=xx.poll();
        }
        return xx.size();
    }
    //__|____|___________|_____|
    /**
     */

}

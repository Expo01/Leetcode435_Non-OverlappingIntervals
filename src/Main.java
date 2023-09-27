import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

//[[1,7][1,2],[2,3],[3,4],[1,3]] --> sort by end
// [1,2] [1,3] [2,3] [3,4] [1,7]
// i = 0 , k = 2
// i = 1, k = 2, will never want to keep later end time, more limiting. ans = 1
// i = 2, k = 3
// i = 3, k = 4
// i = 4, k = 4, ans = 2
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); // sort by index 1 of each interval
        int ans = 0;
        int k = Integer.MIN_VALUE; // start with value guaranteed not to overlap

        for (int i = 0; i < intervals.length; i++) {
            int x = intervals[i][0];
            int y = intervals[i][1];

            if (x >= k) { // if start int of interval > than end int of last, then update k to end int of interval
                k = y;
            } else { // else k remains the same and inc removal
                ans++;
            }
        }
        return ans;
    }
}


// ATTEMPT
//class Solution {
//    public int eraseOverlapIntervals(int[][] intervals) {
//        int removeCount = 0;
//        Arrays.sort(intervals);
//        for(int i = 1; i < intervals.length; i++){
//            if(intervals[i][0] < intervals[i-1][1]){
//                removeCount++;
//            }
//        }
//        return removeCount;
//    }
//}

// [[1,7][1,2],[2,3],[3,4],[1,3]]
// itv[0] overlaps with 1 but can say if itv[0][1] > itv[1][1] then remove itv[0]?

// [1,2],[2,3],[2,7][3,4],[1,3]]
// here a little different perhaps say if span is greater than delete that such as
// for [2,3] = span 2. [2,7] span 6. delete the most limiting element and proceed.
// then again when encountering [1,3]. however, we would want to order by first
// index before.  [1,2],[1,3][2,3],[2,7][3,4],
// [1,3] overlapping, delete. since span 3 > 2. then delete [2,7] since span >

// won't actually delete so just say removeCount++ if span >
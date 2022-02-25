/*
프로그래머스 정렬 lv2 h-index
*/

import java.util.*;
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        int len = citations.length;
        for(int i=0; i<len; i++){
            int num = Math.min(citations[i], len-i);
            if(max <=num) {
                max = num;
            }
        }
        return max;
    }
}

/**
https://programmers.co.kr/learn/courses/30/lessons/4262
백준 코딩테스트 연습 더 맵게
minHeap PQ에서 상위 2개를 계산해서 다시 넣어주고 상위값이 K이상이 될때까지 반복해준다.
**/
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int scov: scoville) {
            pq.add(scov);
        }
        
        int answer = 0;
        while(pq.peek()<K && pq.size()>1) {
            answer++;
            int first = pq.poll();
            int second = pq.poll();
            int mix = first + (second*2);
            pq.add(mix);

        }
        if(pq.peek()<K) return -1;
        
        return answer;
    }
}

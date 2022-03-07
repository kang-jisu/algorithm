/*
프로그래머스 힙 이중우선순위큐
min, max 는 o(1)로 빼고 나머지도 remove함수로 찾아서 빼줌
https://programmers.co.kr/learn/courses/30/lessons/42628#
*/
import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        int size = 0;
        for(String op : operations) {
            String[] operation = op.split(" ");
            int num = Integer.parseInt(operation[1]);
            if(operation[0].equals("I")){
                minQ.add(num);
                maxQ.add(num);
                size++;
            }
            else {
                if(size==0) continue;
                boolean max = num>=0?true : false;
                num = Math.abs(num);
                size--;
                if(max){
                    int ma = maxQ.poll();
                    minQ.remove(ma);  // 다른 큐에도 그 값을 빼줌
                }
                else {
                    int mi = minQ.poll();
                    maxQ.remove(mi); // 다른 큐에도 그 값을 빼줌
                     }
            }
        }
        int[] answer = {};
        if(size==0) answer = new int[]{0,0};
        else {
            answer = new int[]{maxQ.peek(), minQ.peek()};
        }
        return answer;
    }
}

/*
프로그래머스 스택/큐 LV2 기능개발
https://programmers.co.kr/learn/courses/30/lessons/42586
계산하면서 큐에 넣는 방법이랑, 다 계산 하고 큐에 넣는 방법을 해봤는데 둘은 시간차이가 생각보다 안났다. 
*/
import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // 걸리는 날짜 계산 
        Queue<Integer> dayQ = new LinkedList<>();
        
        // 결과 ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<progresses.length; i++){
            int days = (int)Math.ceil((100-progresses[i])/(double)speeds[i]);
            
            if(!dayQ.isEmpty() && dayQ.peek() < days) {
                result.add(dayQ.size());
                dayQ.clear();
            }
            dayQ.add(days);
        }
        
        if(!dayQ.isEmpty()){
            result.add(dayQ.size());
        }
//         int prevPoll = dayQ.poll();
//         int cnt = 1;
//         while(!dayQ.isEmpty()){
//             int poll = dayQ.poll();
//             if(prevPoll >= poll) cnt++;
//             else {
//                 result.add(cnt);
//                 cnt=1;
//                 prevPoll = poll;
//             }
            
//             if(dayQ.isEmpty()){
//                 result.add(cnt);
//             }
//         }
        
        
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}

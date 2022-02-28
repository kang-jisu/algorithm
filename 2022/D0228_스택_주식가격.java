/*
프로그래머스 코딩테스트 연습 큐 주식가격
스택큐 쓰는법은 모르겠어서 O(n^2) for문으로만 풀어봤다.
해설보고 풀었다. ㅠㅠ
*/
import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 가격이 떨어지지 않은 인덱스만 저장 
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<prices.length; i++){
            // i번째와 가장 직전 가격이 떨어지지 않은 index(stack.peek)의 prices값을 비교해서
            // 만약 떨어졌다면 answer[stack.peek()]은 i와의 거리(?)간격만큼 저장
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                answer[stack.peek()] = i - stack.pop();
            }
            // 떨어지지 않았다면 스택에 넣는다.
            stack.push(i);
        }
        
        // 스택을 비우며 자기이후에는 모두 떨어지지않은거니까 전체길이(0~length-1)-(현재인덱스)
        while(!stack.isEmpty()){
            answer[stack.peek()] = prices.length - stack.pop() -1;
        }
        return answer;
    }

}

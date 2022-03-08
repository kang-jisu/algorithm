/**
프로그래머스 코딩테스트 연습 큰 수 만들기
https://programmers.co.kr/learn/courses/30/lessons/42883#
처음에는 순열 돌려서 모든 수 값 비교해줫는데 시간초과, 런타임에러가 났다.
이거는 스택을 이용해서 현재 수보다 다음에 넣을 숫자가 크면 현재 수를 스택에서 다 빼주면서 스택을 채워나가면된다.
만약에 k를 못채웠을 경우는 다 내림차순,혹은 같은 숫자로 되어있는 거니까 남은 k만큼 스택에서 빼고 글자를 만들어주면된다.
*/
import java.util.*;
class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for(int i=0; i<number.length(); i++){
            int now = number.charAt(i)-'0';
            if(stack.isEmpty()) stack.push(now);
            else {
                while(count<k && !stack.isEmpty() && stack.peek() <now){
                    count++;
                    stack.pop();
                }
                stack.push(now);
            }
        }

        while(count<k){
            count++;
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        StringBuilder reverseSb = new StringBuilder();
        for(int i=sb.length()-1; i>=0; i--){
            reverseSb.append(sb.charAt(i));
        }
        
        return reverseSb.toString();
    }

}

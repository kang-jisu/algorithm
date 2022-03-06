/**
dfs 탐색으로 +인경우 -인경우 모두 탐색해서 마지막 위치에서 값이 target 이랑 같은지 확인해줬다. 
프로그래머스 코딩테스트연습 타겟넘버 https://programmers.co.kr/learn/courses/30/lessons/43165
*/
class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        return dfs(numbers, 0, 0, target);
    }
    
    public int dfs(int[] numbers, int index, int sum, int target) {
        if(index == numbers.length) {
            if(sum == target) return 1;
            return 0;
        }
        return dfs(numbers, index+1, sum+numbers[index], target) 
                    +dfs(numbers,index+1, sum-numbers[index], target);
    }
}

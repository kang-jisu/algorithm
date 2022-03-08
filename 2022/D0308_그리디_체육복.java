
/**
프로그래머스 그리디알고리즘 체육복
바로 전 ,다음 친구한테만 체육복을 빌릴 수 있다
왼쪽한테 빌리면 가능한데 오른쪽한테 빌려버려서 다른 학생이 못빌리는 경우를 고려해야될까 생각했는데
그리디니까 그냥 해보자 했더니 맞긴했다. 근데뭔가찝찝
https://programmers.co.kr/learn/courses/30/lessons/42862
*/class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        int[] student = new int[n+1];
      // 기본적으로 학생들은 체육복을 가지고있다.
        for(int i=1; i<=n; i++){
            student[i]=1;
        }
      
      // 잃어버리면 체육복 수가 0이된다
        for(int i=0; i<lost.length; i++){
            student[lost[i]]--;
        }
      
      // 여분을 가져온 학생은 추가해준다 -> 잃어버렸다가 가져왔으면 1,이미있는데 가져왔으면 2
        for(int i=0; i<reserve.length; i++){
            student[reserve[i]]++;
        }
        int answer = 0;
        for(int i=1; i<=n; i++){
            if(student[i]==1) {
                answer++;
            }
          // 왼쪽 오른쪽중에 빌릴 수 있으면 빌린다.
            else if(student[i]==0){
                if(i>1 && student[i-1]>1) {
                    student[i-1]--;
                    student[i]++;
                    answer++;
                }
                else if(i<n && student[i+1]>1){
                    student[i+1]--;
                    student[i]++;
                    answer++;
                }
            }
            else {answer++;
                 }
        }
        return answer;
    }
}

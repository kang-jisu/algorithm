/*
카카오 22년 신입공채
양궁대회 https://programmers.co.kr/learn/courses/30/lessons/92342
완탐, 구현 
score==max인 경우 예외처리, max==0(이길수없는경우) 예외처리 조심해야함 
score==max인경우 answer[j]==result[j]인경우에는 continue해주어야함 
문제잘읽기. 남은 화살은 0번쏘는곳에 넣어야하는것도 주의 
*/

class Solution {
    
    int max=0;
    int answer[];
    
    // 더 낮은 점수에서 쏜 화살 수가 더 큰지 확인 
    public boolean isLow(int[] result ){
        boolean isLow = false;
        for(int j=10; j>=0; j--) {
            if(answer[j]==result[j])continue;
            if(answer[j]<result[j]) isLow = true;
            else break;
        }
        return isLow;
    }
    
    public void setMax( int[] result) {
        for(int i=0; i<=10; i++){
            answer[i] = result[i];
        }     
    }
    
    public void cal(int now, int score, int arrowNum,int[] info, int n, int[] result) {
        
        if(now==10 ) { 
            result[10]=arrowNum;
             if(score>max) {
                max = score;
                setMax(result);
              }
             if(score==max){
                  if(isLow(result)) {
                      setMax(result);
                  }
             }
             return;
      }
        
        // 이기는경우
        // 어피치 과녁보다 남은 arrowNum이 더 커야 가능
        if( info[now] < arrowNum) {
            result[now] = info[now]+1;
            cal(now+1, score+(10-now), arrowNum-(info[now]+1), info, n, result);
            result[now]= 0;
        }
        // 지는 경우
        // arrowNum 0개 쓸것
        // 현재 스코어에서 10-now만큼 어피치가 이길것{
        int apeachPoint = 0;
        if(info[now]!=0) apeachPoint = 10-now;
        cal(now+1, score - apeachPoint , arrowNum, info, n, result );
    }
    
    public int[] solution(int n, int[] info) {
        
        int[] result = new int[11];
        answer = new int[11];
        cal(0,0, n, info, n, result);
        return max==0? new int[]{-1}: answer;
    }
}

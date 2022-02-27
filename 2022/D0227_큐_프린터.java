/**
프로그래머스 큐 LV2 프린터
https://programmers.co.kr/learn/courses/30/lessons/42587#
counting 배열을 만들어서 max값에 대해서 갱신해주고 환형배열을 돌면서 location에 해당하는지 찾아주었다. 
*/

import java.util.*;
class Solution {
    static final int MAX_SIZE = 10;
    int[] importance = new int[MAX_SIZE];
    int max = -1;
    public int solution(int[] priorities, int location) {
        
        int answer = 0;
        int it = 0;
        
        // 초기 배열 돌면서 중요도 값 설정
        for(int i=0; i<priorities.length; i++){
            int idx = (i+location)%priorities.length;
            int num = priorities[idx];
            importance[num]++;
            if(max<num) max =num;
        }
        while(answer<priorities.length && max>=1){
            if(it==priorities.length) it=0;
            int idx = it%priorities.length;
            if(priorities[idx]==-1) {
                it++;
                continue; //이미 인쇄됨
            }
            int num = priorities[idx];
            if(num<max){
                it++;
                continue;
            }
            else {
                // 현재 문서 출력
                answer++;
                priorities[idx]=-1;
                if(idx==location) {
                    break;
                }
                
                // 중요도 , max 갱신
                importance[num]--;
                if(importance[num]==0){
                    for(; max>=1; max--){
                        if(importance[max]!=0) break;
                    }
                }
            
                it++;
            }
        }

        return answer;
    }
    //초기화
    public void init(){
        for(int i=0; i<MAX_SIZE; i++){
            importance[i] = -1;
        }
    }
}

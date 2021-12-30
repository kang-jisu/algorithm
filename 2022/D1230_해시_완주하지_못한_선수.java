import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        HashMap<String, Integer> hash = new HashMap<String,Integer>();
        
        for( String parti : participant ) {
            hash.put(parti, hash.getOrDefault(parti,0)+1);
            // if(hash.get(parti)==null ) {
            //     hash.put(parti,1);
            // }
            // else {
            // int count = hash.get(parti);
            // hash.replace(parti, count+1);
            // }
        }
        for( String compl : completion ){
            hash.put(compl, hash.get(compl)-1);
            // int count = hash.get(compl);
            // hash.remove(compl);
            // if(count>1) {
            //     hash.put(compl, count-1);
            // }
        }
        for( Map.Entry<String, Integer> entry : hash.entrySet()) {
            if(entry.getValue()>0){
            answer = entry.getKey();
            break;
            }
        }
        return answer;
    }
}

    /* 
    프로그래머스 코딩테스트 연습 - 해시 Level 1 
    완주하지 못한 선수
    
    선수 수 최대 100,000
    #competition = #participant + 1(완주하지 못한 선수)
    참가자 이름 20개 이하 알파벳 소문자, 동명이인 가능 
   
    
    알고리즘 : 해시
    정확성  테스트
    테스트 1 〉	통과 (0.04ms, 78.3MB)
    테스트 2 〉	통과 (0.07ms, 67.1MB)
    테스트 3 〉	통과 (0.74ms, 79MB)
    테스트 4 〉	통과 (0.70ms, 76.7MB)
    테스트 5 〉	통과 (0.60ms, 78.7MB)
    효율성  테스트
    테스트 1 〉	통과 (42.38ms, 83.1MB)
    테스트 2 〉	통과 (72.33ms, 87.8MB)
    테스트 3 〉	통과 (85.51ms, 95.2MB)
    테스트 4 〉	통과 (88.50ms, 95.7MB)
    테스트 5 〉	통과 (71.28ms, 96.4MB)
    */

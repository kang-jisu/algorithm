/*
2022 카카오 신입공채
LV1 신고결과받기 
https://programmers.co.kr/learn/courses/30/lessons/92334
해시 

hashMap, hashSet쓴 코드 있어서 따라 해봤는데 원래 푼 코드가 더 빨라서 그냥 주석처리했다..
*/

import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
//         HashMap<String, Set<String> > reportedArr = new HashMap<>();
//         HashMap<String, Integer> reportedCnt = new HashMap<>();
        
//         for(String user : id_list) {
//             reportedArr.put(user, new HashSet<>());
//             reportedCnt.put(user, 0);
//         }
//         for(String str : report) {
//             String[] reporting = str.split(" ");
//             if(reportedArr.get(reporting[0]).add(reporting[1]))
//                 reportedCnt.put(reporting[1], reportedCnt.get(reporting[1])+1);
//         }
        
        
//         int [] answer = new int[id_list.length];
//         for(String str : report) {
//             String[] reporting = str.split(" ");
//             if( reportedCnt.get(reporting[1])<k) {
//                 reportedArr.get(reporting[0]).remove(reporting[1]);
//             } 
//         }
        
//         for(int i=0; i<id_list.length; i++) {
//             answer[i] = reportedArr.get(id_list[i]).size();
//         }
        
        // 유저 -> 번호 매핑 
        HashMap<String, Integer> map = new HashMap<>();
        int len = id_list.length;
        for(int i=0; i<len; i++) {
            map.put(id_list[i], i); 
        }
        
        // 신고한유저->신고당한유저 관계 나타내는 배열 
        int [][] reportArr = new int[len][len];
        
        // i번 유저가 몇번 신고당했는지 
        int [] cnt = new int [len];
        
        for(int i=0; i<report.length; i++){
            String[] tmp = report[i].split(" ");
            int reporter = map.get(tmp[0]);
            int reported = map.get(tmp[1]);
            
            // 이미 신고당했으면 제외 
            if(reportArr[reporter][reported] ==1) continue;
            
            // 신고당했으면 배열에 체크, cnt 증가
            reportArr[reporter][reported] =1;
            cnt[reported]++;
        }
        
        
        int[] answer = new int[len];
        for(int l=0; l<len; l++){
            // 신고당한 횟수가 k이상이면 
            if(cnt[l]>=k) {
                
                for(int i=0; i<len; i++){
                    // 신고한 유저에게 메일을 보내야하므로 answer에 추가시켜줌 
                    if(reportArr[i][l]==1){
                        answer[i]++;
                    }
                }
            }
        }
        return answer;
    }
}

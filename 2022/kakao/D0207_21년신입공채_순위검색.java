/*
카카오 2021 신입공채 순위검색
https://programmers.co.kr/learn/courses/30/lessons/72412
이번에도 못풀었다.. ㅠ_ㅠ 
binarysearch 다시 알아보기 ,, 
getOrDefault,computeIfAbsent
*/
import java.util.*;
class Solution {
    public int[] solution(String[] infos, String[] querys) {
        
        HashMap<String, ArrayList<Integer> > map = new HashMap<>();
        
        for( String info : infos) {
            String[] str = info.split(" ");
            for(int i =0; i<16; i++){
                StringBuilder sb = new StringBuilder();
                //언어
                if(i<8) sb.append(str[0]);
                //언어 - 
                else sb.append("-");
                
                //직군
                if( (i/4)%2==0 ) sb.append(str[1]);
                //직군 -
                else sb.append("-");
                
                //경력
                if( (i/2)%2==0) sb.append(str[2]);
                //경력 -
                else sb.append("-");
                
                //소울푸드
                if( i%2==0) sb.append(str[3]);
                //소울푸드 -
                else sb.append("-");
                
                String infoStr = sb.toString();
                map.computeIfAbsent(infoStr, s-> 
                                   new ArrayList<>()).add(Integer.parseInt(str[4]));
            }
        }
        
        //여기서 정렬을 한번에 해줘야함 
        for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            entry.getValue().sort(Comparator.naturalOrder());
        }
        
        int[] answer = new int[querys.length];
        for( int i=0; i<querys.length; i++) {
            String[] qstr = querys[i].split(" ");
            String key = qstr[0]+qstr[2]+qstr[4]+qstr[6];
            int num = Integer.parseInt(qstr[7]);
            
            ArrayList<Integer> list = map.getOrDefault(key, new ArrayList<>());

            // binarySearch로 찾아주어야함
            int left = 0;
            int right = list.size()-1;
            while(left<=right) {
                int mid = (left+right)/2;
                if(list.get(mid)<num) {
                    left = mid +1;
                }
                else right = mid-1;
            }
            answer[i] = list.size()-left;
        }
        
        return answer;
    }
}

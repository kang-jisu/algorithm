/*
프로그래머스 코딩테스트 연습 해시 - 위장
getOrDefault, keySet()

안선택하는 경우도 있으니 (n+1)*(m+1) 해준 다음 모두 안선택하는 경우는 제외해야해서 -1을 해준다. 
*/
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer > map = new HashMap<>();
        for(String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1],0)+1);
            // if(map.containsKey(cloth[1])) {
            //     map.get(cloth[1]).add(cloth[0]);
            // }
            // else {
            //    ArrayList<String> newList = new ArrayList<>();
            //     newList.add(cloth[1]);
            //     map.put(cloth[1], newList);
            // }
        }
        
        int answer = 1;

        ArrayList<Integer> list = new ArrayList<>();
        for( String key : map.keySet()){
            list.add(map.get(key));
            answer *= (map.get(key)+1);
            
        }
//         for(Map.Entry<String, ArrayList<String>> entry : map.entrySet() ){
//             list.add(entry.getValue().size());
            
//         }

        // int mul = 1;
        // for(int i=0; i<list.size(); i++){
        //     mul *=(list.get(i)+1);
        // }
        // return mul-1;
        return answer-1;
    }
}


/*
프로그래머스 정렬 Lv2 가장 큰 수 
30보다 3이 더 큰걸 어떻게 나타내야할까 했는데
303 이랑 330을  return (b+a).compareTo(a+b); 이렇게 비교하면 되는거였다..
대박 ..
*/
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] str = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, new Comparator<String>(){
            @Override
            public int compare(String a, String b){
                return (b+a).compareTo(a+b);
            }
        });
        StringBuilder sb = new StringBuilder();
        if(str[0].equals("0")) return "0";
        for(String s: str){
            
            sb.append(s);
        }
        return sb.toString();
    }
}

/*
21년 채용연계형 인턴십 문제 Lv1
https://programmers.co.kr/learn/courses/30/lessons/81301
replaceAll로 풀면 되는 문제였다.
*/

import java.util.*;
import java.util.regex.Pattern;
class Solution {
    public int solution(String s) {
        String numreg = "[0-9]";
        String str = "zero|one|two|three|four|five|six|seven|eight|nine";
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zero",0);
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);
        map.put("five",5);
        map.put("six",6);
        map.put("seven",7);
        map.put("eight",8);
        map.put("nine",9);
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        for(int i=0; i<s.length(); i++){ 
            sb.append(s.charAt(i));
            if(Pattern.matches(numreg, String.valueOf(sb))) {
                System.out.println(sb.toString());
                answer = answer*10 + Integer.parseInt(sb.toString());
                sb = new StringBuilder();
            }
            else             if(Pattern.matches(str, String.valueOf(sb))) {
                System.out.println(sb.toString());
                answer = answer*10 + map.get(sb.toString());
                sb = new StringBuilder();
            }
        }
        return answer;
        
//         String[] strArray = {"zero","one","two","three","four","five","six","seven","eight","nine"};
//         String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
//         for(int i=0; i<10; i++){
//         s = s.replaceAll(strArray[i], digits[i]);
//         }
//         return Integer.parseInt(s);
        
    }
}

/*
카카오 22년 신입공채 
주차요금계산 https://programmers.co.kr/learn/courses/30/lessons/92341
문자열 처리 능력, 시간->분, 요금표 구현 
*/

import java.util.*;
class Solution {
    
    public class Result implements Comparable<Result> {
        int carNum;
        int fee;
        Result(int carNum, int fee) {
            this.carNum =carNum;
            this.fee =fee;
        }
        @Override
        public int compareTo(Result o1) {
            return this.carNum - o1.carNum;
        }
    }
    public int[] solution(int[] fees, String[] records) {
        
        // 입차 
        HashMap<String, Integer> in = new HashMap<>();
        
        // 출차완료
        HashMap<String, Integer> out = new HashMap<>();
        
        // 결과 배열
        ArrayList<Result> result = new ArrayList<>();
        
        for(String parkRecords : records ) {
            String[] record = parkRecords.split(" ");
            int hour = Integer.parseInt(record[0].split(":")[0]);
            int minute = Integer.parseInt(record[0].split(":")[1]);
            int time = hour*60 + minute;
            
            String carNum = record[1];
            String state = record[2];
            if(state.equals("IN")){
                in.put(carNum, time);
            }
            else {
                int inTime = in.remove(carNum);
                int parkingMinutes = time - inTime;
                if(out.containsKey(carNum)) {
                    out.put(carNum, out.get(carNum)+parkingMinutes);
                }
                else out.put(carNum, parkingMinutes);
            }
        }
        for( Map.Entry<String,Integer> entry: in.entrySet()) {
            String carNum = entry.getKey();
            int inTime = entry.getValue();
            int parkingMinutes = 1439-inTime;
            if(out.containsKey(carNum)) {
                    out.put(carNum, out.get(carNum)+parkingMinutes);
                }
                else out.put(carNum, parkingMinutes);
        }
        for( Map.Entry<String,Integer> entry: out.entrySet()) {
            String carNum = entry.getKey();
            int inTime = entry.getValue();
            
        //   fees[0] // 기본 시간
        // fees[1] 기본요금
        // fees[2] 단위시간
        // fess[3] 단위요금
            int fee = fees[1];
            int extraTime = inTime - fees[0];
            fee += Math.ceil((double) extraTime/fees[2]) * fees[3];
            result.add(new Result(Integer.parseInt(carNum), inTime>fees[0]?fee : fees[1]));
        }        
        
        Collections.sort(result);
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i).fee;
        }
        
        return answer;
    }
}

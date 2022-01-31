/*
백준 2457 공주님의 정원
링크 https://www.acmicpc.net/problem/2457
그리디 알고리즘

이전 end보다 start가 작은것들 중 가장 end가 큰 것을 골라주면 된다. -> 이 원리를 쓰면서 더 복잡하게풀었는데 그냥 이거가지고 그리디 돌리면 코드 간단하게 짤 수 있었음.
처음엔 월,달을 일로 변환해서 했는데 월 * 100 + 일로 하면 더 쉽게 쓸 수 있다.


 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PrincessGarden {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N;
        N = Integer.parseInt(st.nextToken());
        class Period {
            int start;
            int end;

            Period(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        ArrayList<Period> periods = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            int start = startMonth * 100 + startDay;
            int end = endMonth * 100 + endDay;
            periods.add(new Period(start, end));
        }

        int flag = 301; // 이전 end보다 start가 작은것들 중 가장 end가 큰 것을 골라주면 된다. 의 이전 end 역할
        int ans = 0;
        while (flag < 1201) {
            boolean check = false;
            int maxEnd = flag;
            for (Period period : periods) {
                if (period.start <= flag) {
                    if (maxEnd < period.end) {
                        check = true;
                        maxEnd = period.end;
                    }
                }
            }
            if (check) {
                flag = maxEnd;
                ans++;
            } else {
              // 3월1일부터11월30일까지 
                ans = 0;
                break;
            }
        }
        System.out.println(ans);
    }
}
//public class PrincessGardenOld {
// 이전 코드
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int [] mon = {0,31,59,90,120,151,181,212,243,273,304,334,365};
//        int N;
//        N = Integer.parseInt(st.nextToken());
//        class Period implements Comparable<Period>{
//            int start;
//            int end;
//
//            Period(int start, int end){
//                this.start= start;
//                this.end = end;
//            }
//
//            @Override
//            public int compareTo(Period o1) {
//                if(this.start == o1.start)
//                    return this.end - o1.end;
//                else return this.start - o1.start;
//            }
//        }
//        ArrayList<Period> periods = new ArrayList<>();
//        for(int i=0; i<N; i++){
//            st = new StringTokenizer(br.readLine());
//            int startMonth = Integer.parseInt(st.nextToken());
//            int startDay = Integer.parseInt(st.nextToken());
//            int endMonth = Integer.parseInt(st.nextToken());
//            int endDay = Integer.parseInt(st.nextToken());
//            periods.add(new Period(mon[startMonth-1]+startDay, mon[endMonth-1]+endDay));
//        }
//
//        Collections.sort(periods);
//
//        int start = mon[2]+1;
//        int end = mon[11];
//
//        if(periods.get(0).start> start) System.out.println(0);
//        else {
//            ArrayList<Period> result = new ArrayList<>();
//            Period tmp = new Period(0,0);
//            int i = 0;
//            while(i<periods.size() && periods.get(i).start<=start) {
//                if(tmp.start==0) {
//                    tmp = periods.get(i);
//                }
//                else if(tmp.end < periods.get(i).end){
//                    tmp = periods.get(i);
//                }
//                i++;
//            }
//
//            result.add(tmp);
//            for(; i<periods.size(); i++){
//                Period last = result.get(result.size()-1);
//                if(last.end > end) break;
//                if(periods.get(i).end <= last.end) continue;
//                if(last.start == periods.get(i).start) {
//                    result.remove(result.size()-1);
//                    result.add(periods.get(i));
//                }
//                else if(last.end >= periods.get(i).start) {
//                    tmp = periods.get(i);
//                    int j;
// //이전 end보다 start가 작은것들 중 가장 end가 큰 것을 골라주는부분 
//                    for(j=i+1; j<periods.size(); j++){
//                        if(periods.get(j).start>last.end) {
//                            j--;
//                            break;
//                        }
//                        if(periods.get(j).end>tmp.end) {
//                            tmp = periods.get(j);
//                        }
//                    }
//                    if(j==periods.size())j--;
//                    i=j;
//                    result.add(tmp);
//                }
//
//            }
//            if(result.get(result.size()-1).end <=end) System.out.println(0);
//            else System.out.println(result.size());
//        }
//
//    }
//}

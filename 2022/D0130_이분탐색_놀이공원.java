/*
백준 1561번 놀이공원
문제링크 https://www.acmicpc.net/problem/1561
유형 : 이분 탐색 
메모리: 15024KB	시간: 168ms
처음엔 현재 타는 시간에 대해서 우선순위큐로 poll하면서 다음 타음시간 다시 넣어주고.. 뽑는 방식으로 풀어봤는데 메모리초과가났다.
표를 그려서 문제를 풀다보면 T분에 놀이기구를 탄 인원이 M + T/time[1]+T/time[2]+T/time[3] + .. + T/Time[M] 인 것을 알 수 있다.

N명의 인원을 모두 채우기 위해서 몇분이 걸리는지 이분탐색으로 알아낸 뒤, 그중에 몇번째 놀이기구가 마지막으로 탄건지 알기 위해 T-1분까지의 놀이기구를 탄 인원을 미리 계산하고, 1~M번 놀이기구를 확인하며 사람을 태울수 있는지 카운트를 세어 N이 되는 순간의 놀이기구 번호를 알아내면 된다.
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Play {
    static int N, M;
    static int[] time;
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    time = new int[M + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
        time[i] = Integer.parseInt(st.nextToken());
    }
    if(N<=M) System.out.println(N);
    else {
        long searchTime = binarySearch() - 1;
        long child = M;
        for (int i = 1; i <= M; i++) {
            child += searchTime / time[i];
        }

        for(int i=1; i<=M; i++){
            if( (searchTime+1)%time[i]==0) child++;
            if( child==N){
                System.out.println(i);
                return;
            }
        }

    }

}
static long binarySearch() {
    long left = 0;
    long right = (N/M) * 30L;
    long result = -1;

    while (left <= right) {
        long mid = (left + right) / 2;
        long child = M;
        for (int i = 1; i <= M; i++) {
            child += mid / time[i];
        }

        if (child < N) {
            left = mid + 1;
        } else {
            right = mid-1;
        }
    }
    return left;
}

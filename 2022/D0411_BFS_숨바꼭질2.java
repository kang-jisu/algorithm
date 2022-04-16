/*
백준 숨바꼭질2 12851번 
참고 블로그
https://maetdori.tistory.com/entry/BOJ-12851-%EC%88%A8%EB%B0%94%EA%BC%AD%EC%A7%88-2-JAVA?category=852190

다시풀어보기
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TnaqkRHrwlf2 {
    public static final int MAX = 100000;
    public static int min =  Integer.MAX_VALUE;
    public static int route = 0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if( N >= K) {
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        int[] visit = new int[MAX + 1];
        visit[N] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if ( min < visit[cur]) {
                break;
            }
            int[] move = new int[]{cur-1, cur+1, cur*2};
            for(int i=0; i<3; i++) {
                int next = move[i];

                if(next<0 || next>MAX) continue;

                if(next==K) {
                    min = visit[cur];
                    route++;
                }
                if(visit[next]==0 || visit[next] == visit[cur]+1) {
                    q.add(next);
                    visit[next] = visit[cur]+1;
                }
            }
        }
        System.out.println(visit[K] - 1);
        System.out.println(route);
    }
}

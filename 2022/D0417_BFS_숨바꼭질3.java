/*
백준 숨바꼭질 3
순간이동이 0초이므로 먼저넣어주어야하낟.
https://www.acmicpc.net/problem/13549
BFS 
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TnaqkRHrwlf3 {
    public static final int MAX = 100000;
    public static int min =  Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if( N > K) {
            System.out.println(N-K);
            return;
        }
        int[] visit = new int[MAX+1];
        LinkedList<Integer> q = new LinkedList<>();
        visit[N] = 1;
        q.add(N);
        while(!q.isEmpty()) {
            int cur = q.poll();
            // 찾으면 끝
            if(cur == K) break;

            for(int i=0; i<3; i++){
                int next = 0;
                if(i==0) next = cur-1;
                else if(i==1) next = cur+1;
                else if(i==2) next = cur*2;

                if(next<0 || next>MAX) continue;

                if( i<=1 && (visit[next]==0 || visit[next] > visit[cur]+1)) {
                    visit[next] = visit[cur]+1;
                    q.add(next);
                }
                if(i==2 &&(visit[next]==0 || visit[next] > visit[cur]) ) {
                    visit[next] = visit[cur];
                    // 먼저 넣어주어야함
                    q.addFirst(next);
                }
            }
        }
        System.out.println(visit[K]-1);
    }
}

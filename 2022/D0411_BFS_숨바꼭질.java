/*
백준 1697 숨바꼭질
N에서 K로 가는 단계 3개를 BFS로 탐색하고 초를 배열로 저장해서 최소값만 저장..
https://www.acmicpc.net/problem/1697
참고한 풀이 https://smartpro.tistory.com/18
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TnaqkRhrwlf1 {

    public static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visit = new int[MAX + 1];
        visit[N] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) {
                break;
            }
            if (cur - 1 >= 0 && visit[cur - 1] == 0) {
                visit[cur - 1] = visit[cur] + 1;
                q.add(cur - 1);
            }
            if (cur + 1 <= MAX && visit[cur + 1] == 0) {
                visit[cur + 1] = visit[cur] + 1;
                q.add(cur + 1);
            }
            if (cur * 2 <= MAX && visit[cur * 2] == 0) {
                visit[cur * 2] = visit[cur] + 1;
                q.add(cur * 2);
            }
        }
        System.out.println(visit[K] - 1);
    }
}

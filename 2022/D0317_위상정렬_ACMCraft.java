/*
백준 골드 3 위상정렬 1005번 ACM Craft
위상정렬 indegree가 0일때만 빌딩을 지을 수 있게 queue에 넣음
queue에 넣을 때 끝나는 시간을 기준으로 넣고 그게 최소인값먼저 pq로 뽑아냈는데
그냥 result에 max로 갱신하면서,, 풀기도 하는듯 ..? 
https://www.acmicpc.net/problem/1005
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ACMCraft {
    static class Building {
        int num;
        int time;
        Building(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    static int N;
    static int[] indeg;
    static int[] times;
    static ArrayList<Integer>[] graph;
    static PriorityQueue<Building> pq = new PriorityQueue<>(new Comparator<Building>() {
        @Override
        public int compare(Building o1, Building o2) {
            return o1.time - o2.time;
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            indeg = new int[N + 1];
            times = new int[N + 1];
            graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                graph[i] = new ArrayList<>();
                indeg[i] = 0;
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                indeg[b]++;
            }

            pq.clear();
            int target = Integer.parseInt(br.readLine());
            for (int i = 1; i <= N; i++) {
                if (indeg[i] == 0) pq.add(new Building(i, times[i]));
            }

            while (!pq.isEmpty()) {
                Building cur = pq.poll();
                if (cur.num == target) {
                    System.out.println(cur.time);
                    break;
                }
                for( int next : graph[cur.num]) {
                    indeg[next]--;
                    if(indeg[next]==0) {
                        pq.add(new Building(next, cur.time+times[next]));
                    }
                }
            }
        }
    }
}

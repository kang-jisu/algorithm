/*
백준 골드4 다익스트라 특정한최단경로 
https://www.acmicpc.net/problem/1504
연결되어있찌 않을때도 생각해주어야함
다익스트라 3번해서 구하기 
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SpecificSP {
    static final int MAX = 200_000_000; // 200000*1000
    static int N, E;
    static int A, B; // 반드시 거쳐야하는 정점 번호
    static class Edge {
        int e;
        int dis;
        Edge(int e, int dis){
            this.e = e;
            this.dis =dis;
        }
    }
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b,c));
            graph[b].add(new Edge(a,c));
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int[] dis1 = new int[N+1];
        int[] dis2 = new int[N+1];
        int[] dis3 = new int[N+1];
        dijkstra(1, dis1);
        dijkstra(A, dis2);
        dijkstra(B, dis3);
        // 둘이 연겨되어있지 않은 경우는 -1
        int sum1 = dis1[A] + dis2[B] + dis3[N];
        int sum2 = dis1[B] + dis3[A] + dis2[N];
        if(sum1 >= MAX || sum2 >= MAX) System.out.println(-1);
        else {
            System.out.println(Math.min(sum1, sum2));
        }
    }
    public static void dijkstra(int start, int[] dp) {
        for(int i=0; i<=N; i++){
            dp[i] = MAX;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.dis - o2.dis;
            }
        });
        dp[start] = 0;
        pq.add(new Edge(start, 0));
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(dp[cur.e] < cur.dis) continue;
            for( Edge next : graph[cur.e]) {
                if( dp[next.e] > cur.dis + next.dis) {
                    dp[next.e] = cur.dis+next.dis;
                    pq.add(new Edge(next.e, dp[next.e]));
                }
            }
        }
    }
}

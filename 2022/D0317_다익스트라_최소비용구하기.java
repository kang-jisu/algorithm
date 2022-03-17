/*
백준 최소비용구하기 1916 다익스트라

*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Shortest {
    static class Node implements Comparable<Node> {
        int end;
        int dis;

        Node(int end, int dis) {
            this.end = end;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node a) {
            return this.dis - a.dis;
        }
    }

    static int N, M;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        System.out.println(dijsktra(start, end));
    }

    public static int dijsktra(int start, int end) {
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dp[start] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.end == end) return cur.dis;
            if (dp[cur.end] < cur.dis) continue;
            for (Node next : graph[cur.end]) {
                if (dp[next.end] > cur.dis + next.dis) {
                    dp[next.end] = cur.dis + next.dis;
                    pq.add(new Node(next.end, dp[next.end]));
                }
            }
        }
        return -1;
    }
}

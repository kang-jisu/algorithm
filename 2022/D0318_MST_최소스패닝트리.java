/*
백준 최소스패닝트리 https://www.acmicpc.net/problem/1197
union find 
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MST {
    static int V, E;
    static class Edge {
        int u;
        int v;
        int w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    static int[] parent;
    static int[] sum;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.w - o2.w;
        }
    });
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        sum = new int[V+1];
        for(int i=0; i<=V; i++){
            parent[i] = i;
            sum[i]=1;
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u,v,e));
        }

        int weights= 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int pu = find(cur.u);
            int pv = find(cur.v);
            if(pu==pv) continue;
            else {
                union(pu, pv);
                weights += cur.w;
            }
        }
        System.out.println(weights);
    }
    public static int find(int node){
        if(parent[node]==node) return node;
        else return parent[node] = find(parent[node]);
    }
    public static void union(int a, int b){
        if(sum[a]<=sum[b]) {
            parent[a] = b;
            sum[b] += sum[a];
            sum[a] = 1;
        }
        else if(sum[a]>sum[b]) {
            parent[b] = a;
            sum[a] += sum[b];
            sum[b]= 1;
        }
    }

}

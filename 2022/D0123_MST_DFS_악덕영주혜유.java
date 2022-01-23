package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HyeU {
    static int N, M;
    static class Node{
        int dis;
        int cost;

        Node(int dis, int cost){
            this.dis = dis;
            this.cost = cost;
        }
    }
    static class Edge{
        int s;
        int e;
        int c;
        Edge(int s, int e, int c){
            this.s = s;
            this.e = e;
            this.c = c;
        }
    }
    static PriorityQueue<Edge> pq;
    static ArrayList<Node>[] city;
    static int[] visit;
    static int[] par;
    static int maxNode;
    static long maxCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.c - o2.c;
            }
        });
        visit = new int[N];
        city = new ArrayList[N];
        par = new int[N];
        for(int i=0; i<N; i++){
            city[i]= new ArrayList<>();
            par[i]=i;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(s,e,c));
        }

        long sum=0;
        int cnt=0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();

            int ps = find(now.s);
            int pe = find(now.e);
            if(ps!=pe) {
                cnt++;
                sum += now.c;
                par[ps] = pe; // 부모끼리 업데이트해주어야한다.
                // 여기서 찾은 애들만 리스트에넣어주어야함
                city[now.s].add(new Node(now.e, now.c));
                city[now.e].add(new Node(now.s, now.c));
                if(cnt==N-1) break; // 좀더 빨리 탈출할수있음 
            }
        }

        System.out.println(sum);
        maxNode=0;
        maxCost=0;
        dfs(0,0);
        for(int i=0; i<N; i++){
            visit[i]=0;
        }
        maxCost=0;
        dfs(maxNode,0);
        System.out.println(maxCost);
    }

    static int find(int n){
        if(par[n]==n) return n;
        else return par[n] = find(par[n]);
    }

    static void dfs(int node, int cost) {
        visit[node]=1;
        if(cost > maxCost) {
            maxCost= cost;
            maxNode = node;
        }
        for(Node n: city[node]){
            if(visit[n.dis]==0) {
                visit[n.dis]=1;
                dfs(n.dis, cost+ n.cost);
            }
        }
    }
}

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CriticalPath {

    static int N, M;
    static class Edge {
        int start;
        int end;
        int weight;
        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    static ArrayList<Edge>[] edges;
    static ArrayList<Edge>[] reverse_edges;
    static int[] indeg;
    static int[] maxWegiht;
    static int start, end;
    static Queue<Integer> q;
    static Queue<Integer> rq;
    static int[] visit;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        indeg = new int[N+1];
        maxWegiht = new int[N+1];
        visit = new int[N+1];
        q = new LinkedList<>();
        rq = new LinkedList<>();

        edges = new ArrayList[N+1];
        reverse_edges = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            edges[i] = new ArrayList<Edge>();
            reverse_edges[i] = new ArrayList<Edge>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[u].add(new Edge(u,v,w));
            reverse_edges[v].add(new Edge(v,u,w));
            indeg[v]++;
        }
        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        q.add(start);
        while(!q.isEmpty()) {
            int node = q.poll();
            for(Edge edge : edges[node]){
                maxWegiht[edge.end] = Math.max(maxWegiht[edge.end], maxWegiht[edge.start]+edge.weight );
                indeg[edge.end]--;
                if(indeg[edge.end]==0) {
                    q.add(edge.end);
                }
            }
        }
        rq.add(end);
        visit[end]=1;
        int road_cnt=0;
        while(!rq.isEmpty()) {
            int node = rq.poll();
            if(node == start) break;

            for(Edge edge : reverse_edges[node]){
                if(maxWegiht[node]- maxWegiht[edge.end] == edge.weight) {
                    road_cnt++;
                    if(visit[edge.end]==0) {
                        visit[edge.end]=1;
                        rq.add(edge.end);
                    }
                }
            }
        }

        System.out.println(maxWegiht[end]);
        System.out.println(road_cnt);
    }


}
/*

위상정렬, 임계경로 알고리즘 
그냥 평범하게 확인하니까 시간초과가 나왔다.
그래프를 이차원배열로 만들어 탐색하는방법 말고 다른걸 생각해봐야한다.
위상정렬 알고리즘 푸는법을 모르는것가타서 찾아봐야겠따
위상정렬은 O(N+E)

해당 도시로 들어오는 모든 도로들 중 가장 소요시간이 큰 것이 해당 도시에서 출발할 수 있는 시간
indegree[0]인 도시들에 대해서 탐색 -> 큐에 넣고..
각 노드별로 maxcost를 구하고
카운트를 세는거는  if(maxWegiht[node]- maxWegiht[edge.end] == edge.weight) 이거로 해당 경로에 포함되는지를 확인해준다.
어려웡 
*/

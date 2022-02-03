/*
백준 달빛여우 16118번
https://www.acmicpc.net/problem/16118

다익스트라

wolf에서 현재 느리고,빠른걸 dp[2][n]으로 잡ㅇ야하고 초기값은 현재가 느린(시작하자마자 빨리 달려야하는)상태인 dp[0][1]만 0으로초기화해줘야한다.
dp[1][1]은 언젠가 싸이클로 더 작은 값이 들어올 수 있으므로 INF 로 놔줘야한다.
int값범위를 넘을수있으니
다익스트라어렵당 ㅠ

*/

package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LunaticFox {
    static class Edge implements Comparable<Edge>{
        int s;
        int e;
        long w;
        int prev;

        Edge( int e, long w) {
            this.e = e;
            this.w = w;
        }
        Edge(int s, int e, long w, int prev) {
            this.s  = s;
            this.e = e;
            this.w = w;
            this.prev= prev;
        }
        Edge( int e, long w, int prev) {
            this.e = e;
            this.w = w;
            this.prev= prev;
        }
        @Override
        public int compareTo(Edge o) {
            return (int)(this.w - o.w);
        }
    }
    static int N, M;
    static ArrayList<Edge>[] graph;
    static long[] fox ;
    static long[][] wolf ;

    public static void dijkstra(){
        for(int i=0; i<=N; i++){
            fox[i] = Long.MAX_VALUE;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));
        fox[1]=0;
        while(!pq.isEmpty()){
            Edge poll = pq.poll();
            int node = poll.e;
            long distance = poll.w;

            if(fox[node]<distance) continue;

            for(Edge nextEdge: graph[node]) {
                long cost = distance + nextEdge.w;
                int nextNode = nextEdge.e;
                if( fox[nextNode] > cost) {
                    fox[nextNode]=  cost;
                    pq.add(new Edge(nextNode, cost));
                }
            }
        }
    }
    public static void dijkstraWolf(){

        /*
        0: 현재 느리게, 다음 빠르게
        1: 현재 빠르게, 다음 느리게
         */
        for(int i=0; i<=N; i++){
            wolf[0][i] = Long.MAX_VALUE;
            wolf[1][i] = Long.MAX_VALUE;

        }
        wolf[0][1]=0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0,0));
        while(!pq.isEmpty()){
            Edge poll = pq.poll();
            int node = poll.e;
            long distance = poll.w;
            int state = poll.prev;

            if(wolf[state][node]<distance) continue;
            for( Edge nextEdge : graph[node]){
                int nextState = 1-state;
                int nextNode = nextEdge.e;
                long cost = distance +( nextState==0? nextEdge.w*2 : nextEdge.w/2);

                if( wolf[nextState][nextNode] > cost) {
                    wolf[nextState][nextNode] = cost;
                    pq.add(new Edge( nextNode, cost, nextState));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        fox = new long[N+1];
        wolf = new long[2][N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(s,e,v*2,0));
            graph[e].add(new Edge(e,s,v*2,0));
        }
        dijkstra();
        dijkstraWolf();

        int cnt=0;
        for(int i=1; i<=N; i++){
            if(fox[i] < Math.min(wolf[0][i], wolf[1][i])) cnt++;
        }
        System.out.println(cnt);
    }
}

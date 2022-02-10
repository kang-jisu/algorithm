/*
백준 1854 K번째 최단경로
다익스트라 

https://www.acmicpc.net/problem/1854

다익스트라 돌면서 비교하는 값들을 모두 저장해놓고 sorting하기 생각 -> 메모리초과
visit으로 확인했는데 그게 아니고 K미만으로만 저장하면서 PQ의 제일 큰 값을 갱신해줄 수 없을때까지 돌면
계속 돌면서 방문 체크를 안해줘도 되고 while문을 적절하게 통과하게된다..
ㅠ_ㅠ
시작값은 미리 넣어줘야하고, pq가 k보다 작은상태거나 지금 넣으려는 값 비교하는걸 for문안에 해줘야 while문을 끝낼 수 있음.
 */

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KthShortestPath {

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int w;
        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
    static int N;
    static int M;
    static int K;
    static ArrayList<Edge>[] list;
    static Queue<Integer>[] dis ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        dis = new PriorityQueue[N+1];
        for(int i=0; i<=N; i++){
            list[i]=new ArrayList<>();
            // 내림차순으로 정렬하면 이미 k개 일 때 현재 넣으려는것과 peek을 비교해서 큰 수를 빼낼 수 있다. 
            dis[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(a, b, c));
        }
        dijkstra();
        for(int i=1; i<=N; i++){
            if(dis[i].size() < K) System.out.println(-1);
            else System.out.println(dis[i].poll());
        }
    }

    public static void dijkstra() {
        Edge start = new Edge(0,1,0);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(start);
        dis[start.e].add(start.w); // 시작 값 처리 필요
        while(!pq.isEmpty()) {
            Edge poll = pq.poll();
            for(Edge next : list[poll.e]){
                int cost = next.w + poll.w;
                // 안에서 비교해서 continue 해줘야 더이상 들어갈 값 없으면 나가게됨
                if(dis[next.e].size() >= K) {
                    if( cost >= dis[next.e].peek()) continue;
                    else dis[next.e].poll();
                }
                dis[next.e].add(cost);
                pq.add(new Edge(next.s, next.e, cost));

            }
        }
    }

}

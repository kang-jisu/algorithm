package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Elevator {

    static int N,M;
    static int start, goal;
    static ArrayList<Integer> elevator[];
    static ArrayList<Integer> floor[];

    static int [] cnt;
    static int [] parent;
    static class Pair implements Comparable<Pair>{
        int a;
        int b;
        Pair(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return this.a - o.a;
        }
    }
    static PriorityQueue<Pair> pq;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        cnt = new int[M+1];
        parent = new int[M+1];

        elevator = new ArrayList[N+1];
        floor = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            elevator[i] = new ArrayList<>();
            floor[i] = new ArrayList<>();
        }


        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            for(int j=X; j<=N; j+=Y){
                elevator[j].add(i);
                floor[i].add(j);
            }
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        solve();
    }

    public static void solve(){
        int ans = Integer.MAX_VALUE;
        int last = 0;
        dijkstra();

        for(int i=0; i<elevator[goal].size(); i++){
            if(cnt[elevator[goal].get(i)] < ans ) {
                ans = cnt[elevator[goal].get(i)];
                last = elevator[goal].get(i);
            }
        }

        if(ans>=Integer.MAX_VALUE || ans ==0) System.out.println(-1);
        else {
            System.out.println(ans);

            printP(last);
        }
    }

    public static void printP(int last){
        if(last==0) return;
        printP( parent[last]);
        System.out.println(last);
    }
    public static void dijkstra(){
        for(int i=0; i<elevator[start].size(); i++){
            cnt[elevator[start].get(i)]=1;
            pq.add(new Pair(-1, elevator[start].get(i)));
        }
        while(!pq.isEmpty()) {
            Pair poll = pq.poll();
            int ele = poll.b;
            int cost = poll.a * -1;

            if( cost <= cnt[ele]) {
                for(int i=0; i< floor[ele].size(); i++ ){
                    int other = floor[ele].get(i);
                    for(int j=0; j<elevator[other].size(); j++){
                        int next= elevator[other].get(j);
                        if( cnt[next] == 0 || cost+1 < cnt[next]) {
                            cnt[next] = cost+1;
                            parent[next] = ele;
                            pq.add(new Pair( (cost+1)*-1, next));
                        }
                    }
                }
            }
        }
    }
}

/*
백준 2593 아직 이해 못했따.. 모르겠어서 답보면서 이해중 ㅠ 
*/

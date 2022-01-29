package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gerrymandering {
    static int[] pop;
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] visit;
    static int min;
    static int[] checked;
    static int[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        pop = new int[N+1];
        graph = new ArrayList[N+1];
        visit = new int[N+1];
        min = Integer.MAX_VALUE;
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            pop[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j=0; j<n; j++){
                int a = Integer.parseInt(st.nextToken());
                graph[i].add(a);
            }
        }

        for(int i=1; i<=(N/2)+1; i++){
            permutation(1,0,i);
        }
        if(min==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);

    }
    static void permutation(int start, int cnt, int end) {
        if (cnt == end) {
//            for(int i=1; i<=N; i++){
//                if(visit[i]==1) System.out.print(i+" " );
//            }
//            System.out.println("");
            check();
            return;
        }
        for (int i = start; i <= N; i++) {
            visit[i] = 1;
            permutation(i + 1, cnt + 1, end);
            visit[i] = 0;
        }
    }
    static void check(){
        checked = new int[N+1];
        sum = new int[2];
        for(int i=1; i<=N; i++){
            if(visit[i]==0 && checked[i]==0) {
                gary(i,0);
                break;
            }
        }
        for(int i=1; i<=N; i++){
            if(visit[i]==1 && checked[i]==0) {
                gary(i,1);
                break;
            }
        }
        for(int i=1; i<=N; i++){
            if(checked[i]==0) return;
        }

        min = Math.min(min, Math.abs(sum[0]-sum[1]));
    }
    static void gary(int x, int s){
        checked[x]=1;
        sum[s]+= pop[x];
        for(int i: graph[x]){
            if(visit[i] == visit[x] && checked[i]==0) gary(i,s);
        }
    }


}

/*
백준 17471 게리맨더링

조합으로 전체를 두 구역으로 나눈 후 그 구역에 속한 마을이 모두 이어지는지 확인 
*/

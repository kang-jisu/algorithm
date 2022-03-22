/*
처음에 문제이해하는데 좀 걸렸다
구현 문제 ~
백준 나무재테크 https://www.acmicpc.net/problem/16235 
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tree {
    static int N, M, K;
    static int[][] A;
    static int[][] garden;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    static class Trees implements Comparable<Trees> {
        int x;
        int y;
        int age;
        Trees(int x, int y, int age){
            this.x =x ;
            this.y= y;
            this.age=age;
        }

        @Override
        public int compareTo(Trees o1){
            return this.age - o1.age;
        }
    }

    static PriorityQueue<Trees> pq;
    static Queue<Trees> alive;
    static Queue<Trees> dead;

    public static void print(){
        System.out.println(pq.size());
    }

    public static void go(){
        //봄
        while(!pq.isEmpty()) {
            Trees t = pq.poll();
            if( garden[t.x][t.y] >= t.age) {
                garden[t.x][t.y]-= t.age;
                alive.add(new Trees(t.x, t.y, t.age+1));
            }
            else {
                dead.add(t);
            }
        }

        // 여름
        while(!dead.isEmpty()){
            Trees t = dead.poll();
            garden[t.x][t.y] += t.age/2;
        }

        //가을
        while(!alive.isEmpty()) {
            Trees t = alive.poll();
            pq.add(new Trees(t.x, t.y, t.age));

            if( t.age>=5 && t.age%5==0) {
                for(int d=0; d<8; d++){
                    int A = t.x + dx[d];
                    int B = t.y + dy[d];
                    if(A>=1 && B>=1&& A<=N && B<=N) {
                        pq.add(new Trees(A,B,1));
                    }
                }
            }
        }

        //겨울
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                garden[i][j] += A[i][j];
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1][N+1];
        garden = new int[N+1][N+1];
        pq = new PriorityQueue<>();
        alive = new LinkedList<>();
        dead = new LinkedList<>();

        // 처음에는 5
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                garden[i][j]=5;
            }
        }
        for(int i=1;i<=N; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            pq.add(new Trees(x,y,z));
        }

        for(int i=0; i<K; i++){
            go();
        }
        print();
    }
}

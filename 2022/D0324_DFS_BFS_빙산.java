/*
백준 빙산 DFS+BFS 다 써서 1년지날때마다 높이 줄여주고, 그래프가 모두 이어졌는지 탐색확인하기
다녹아버렸을때 0으로 출력하는거랑 map 갱신을 높이 줄이고나서 마지막에야 해야하는것 주의
https://www.acmicpc.net/problem/2573
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IceBerg {

    static int N, M;
    static class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x =x;
            this.y =y;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Pos> alive;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        init();
        int result = 0;
        int isfinish = 1;
        while(isfinish>0) {
            result++;
            isfinish = overYear();
        }
        if(isfinish==0) System.out.println(0);
        else System.out.println(result);
    }

    public static int overYear(){

        boolean[][] unvisited = new boolean[N][M];
        Queue<Pos> tmp = new LinkedList<>();
        Queue<Pos> dead = new LinkedList<>();
        int aliveCnt=0;

        // 빙산 녹임
        while(!alive.isEmpty()) {
            Pos cur = alive.poll();
            int zeroCnt = 0;
            for(int d=0; d<4; d++){
                int A = cur.x + dx[d];
                int B = cur.y + dy[d];
                if(A>=0 && B>=0&& A<N&& B<M && map[A][B]==0) {
                    zeroCnt++;
                }
            }
            int height = map[cur.x][cur.y];
            if(height > zeroCnt) {
                map[cur.x][cur.y] = height-=zeroCnt;
                aliveCnt++;
                tmp.add(new Pos(cur.x, cur.y));
                unvisited[cur.x][cur.y]=true;
            }
            else {
                // 미리 map에서 0으로 바꾸면 계산이 잘못되니깐 dead에 넣었다가 일괄처리해주어야함
                dead.add(new Pos(cur.x, cur.y));
            }
        }
        while(!dead.isEmpty()){
            Pos cur = dead.poll();
            map[cur.x][cur.y]=0;
        }

        // 남은거 없으면 끝 -> 이때는 0출력
        if(tmp.isEmpty()) return 0;

        // 하나 골라서 dfs돌리면 모두 탐색되어야함
        dfs(unvisited, tmp.poll());

        // 빙하 덩이 갈라진것. while문 종료 
        if(alive.size() != aliveCnt )return -1;

        // 계속 진행
        return 1;
    }

    public static void dfs(boolean[][] unvisited, Pos cur) {
        unvisited[cur.x][cur.y]=false;
        alive.add(cur);
        for(int d=0; d<4; d++){
            int A = cur.x + dx[d];
            int B = cur.y + dy[d];
            if(A>=0 && B>=0 && A<N && B<M && unvisited[A][B]==true) {
                dfs(unvisited, new Pos(A, B));
            }
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        alive = new LinkedList<>();
        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) {
                    alive.add(new Pos(i, j));
                }
            }
        }
    }
}

/*
백준 2206 벽부수고 이동하기 bfs 골드 4
https://www.acmicpc.net/problem/2206
최단거리는 무조건 BFS로 해야한다. 그리고 값을 찾으면 바로 return해도된다 제일 처음에 찾은게 최단거리
처음에 모든 벽에 대해서 0으로 만들고 해봤는데 생각해보면 시간복잡도가 1조가 나와서 절대 안된다.
다음에 가능한지를 이전에 벽을 부신적이 있는지에 대한 플래그를 만들어서 확인.. 3차원배열로 설정해주었다.

3차원 배열로 설정하지 않아도, visit배열을 int로 설정하고 초기화를 MAX로 해주면 
벽을 안부시고 방문했을때는 0 , 벽을 부시고 방문했을 때는 1 -> Max일때 벽을 부시거나, 안부시고 방문 or 벽을 부신(1)상태에서 벽을 안부시고도 방문 하다면 queue에 넣어주는 방식으로 풀 수 있따. 
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWall {
    static int N;
    static int M;
    static int[][] map;

    static class Pos {
        int x;
        int y;
        int cnt;
        boolean breakWall;

        Pos(int x, int y, int cnt, boolean breakWall) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.breakWall = breakWall;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 1; j <= M; j++) {
                map[i][j] = (int) line.charAt(j - 1) - '0';
            }
        }
        System.out.println(bfs());
    }
    public static int bfs() {
        Queue<Pos> q = new LinkedList<>();
        // 벽을 부신적이 있는지, 없는지를 저장
        int[][]visit = new int[N + 1][M + 1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                visit[i][j]= Integer.MAX_VALUE;
            }
        }
        q.add(new Pos(1, 1, 1, false));
        visit[1][1]=0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            int status = cur.breakWall ? 1 : 0;

            // 목적지에 도달하면 return
            if (cur.x == N && cur.y == M) {
                return cur.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int A = cur.x + dx[i];
                int B = cur.y + dy[i];
                if (A >= 1 && B >= 1 && A <= N && B <= M) {
                    // visit이 MAX_VALUE여서 한번도 방문하지 않았었거나, 벽을 부시고 방문했었는데 벽을 안부시고 방문할 수 있을때만 탐색
                    if(visit[A][B] > status) {
                        // 다음 갈곳이 벽이 아니면 추가
                        if (map[A][B] == 0 ) {
                            visit[A][B]= status;
                            q.add(new Pos(A, B, cur.cnt + 1, cur.breakWall));
                        }
                        else {
                            // 다음갈 곳이 벽이지만 벽을 부신적이 없고, 현재 상태로 방문한적이 없으면 추가
                            if(cur.breakWall==false) {
                                visit[A][B] = 1;
                                q.add(new Pos(A, B, cur.cnt + 1, true));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

//    public static int bfs() {
//        Queue<Pos> q = new LinkedList<>();
//        // 벽을 부신적이 있는지, 없는지를 저장
//        boolean[][][] visit = new boolean[N + 1][M + 1][2];
//        q.add(new Pos(1, 1, 1, false));
//        visit[1][1][0] = true;
//
//        int[] dx = {-1, 1, 0, 0};
//        int[] dy = {0, 0, -1, 1};
//        while (!q.isEmpty()) {
//            Pos cur = q.poll();
//            int status = cur.breakWall ? 1 : 0;
//
//            // 목적지에 도달하면 return
//            if (cur.x == N && cur.y == M) {
//                return cur.cnt;
//            }
//            for (int i = 0; i < 4; i++) {
//                int A = cur.x + dx[i];
//                int B = cur.y + dy[i];
//                if (A >= 1 && B >= 1 && A <= N && B <= M) {
//                    // 다음 갈곳이 벽이 아니면 추가
//                    if (map[A][B] == 0 && visit[A][B][status] == false) {
//                        visit[A][B][status] = true;
//                        q.add(new Pos(A, B, cur.cnt + 1, cur.breakWall));
//                    }
//                    // 다음갈 곳이 벽이지만 벽을 부신적이 없고, 현재 상태로 방문한적이 없으면 추가
//                    else if (map[A][B] == 1 && cur.breakWall == false && visit[A][B][status] == false) {
//                        visit[A][B][1] = true;
//                        q.add(new Pos(A, B, cur.cnt + 1, true));
//                    }
//                }
//            }
//        }
//        return -1;
//    }
}


/**
 * 시간초과 ->
 * (N*M)^2 이므로 당연히 시간초과가 날 수 밖에 없다.
 */

//public class BreakWall {
//    static int N;
//    static int M;
//    static int[][] map;
//
//    static class Pos {
//        int x;
//        int y;
//        int cnt;
//        Pos(int x, int y, int cnt){
//            this.x = x;
//            this.y = y;
//            this.cnt = cnt;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        ArrayList<Pos> walls = new ArrayList<>();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N+1][M+1];
//        for(int i=1; i<=N; i++){
//            st = new StringTokenizer(br.readLine());
//            String line = st.nextToken();
//            for(int j=1; j<=M; j++){
//                map[i][j] = (int) line.charAt(j-1)-'0';
//                if(map[i][j]==1) walls.add(new Pos(i,j,0));
//            }
//        }
//        int min = Integer.MAX_VALUE;
//        for(int i=0; i<walls.size(); i++){
//            Pos wall = walls.get(i);
//            map[wall.x][wall.y] = 0;
//            int result = bfs();
//            if(result!=-1 && result < min) min = result;
//            map[wall.x][wall.y] = 1;
//        }
//        if(min == Integer.MAX_VALUE) System.out.println(-1);
//        else System.out.println(min);
//    }
//    public static int bfs(){
//        if(N==1 && M==1)return 1;
//        Queue<Pos> q = new LinkedList<>();
//        boolean[][] visit = new boolean[N+1][M+1];
//
//        q.add(new Pos(1,1,1));
//        visit[1][1]=true;
//        int[] dx = {-1,1,0,0};
//        int[] dy = {0,0,-1,1};
//        while(!q.isEmpty()) {
//            Pos cur = q.poll();
//            if(cur.x==N && cur.y == M) {
//                return cur.cnt;
//            }
//            for(int i=0; i<4; i++){
//                int A = cur.x + dx[i];
//                int B = cur.y + dy[i];
//                if(A>=1 && B>=1 && A<=N && B<=M){
//                    if( map[A][B]==0 && visit[A][B]==false) {
//                        visit[A][B]=true;
//                        q.add(new Pos(A,B, cur.cnt+1));
//                    }
//                }
//            }
//        }
//        return -1;
//    }
//}

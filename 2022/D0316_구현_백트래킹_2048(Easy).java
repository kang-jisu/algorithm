/*
백준12100  2048 Easy  https://www.acmicpc.net/problem/12100
구현, 백트래킹 문제
간만에 한번에풀었다 ㅎㅎ 
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Easy2048 {
    // 상, 하, 좌, 우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    // 옮기는 방향과 읽는방향은 반대
    static int[] next = {1,0,3,2};
    static int[][] board;
    static int N;
    static int Max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Max = 0;
        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]>Max) Max = board[i][j];
            }
        }
        dfs(-1, 0, 5);
        System.out.println(Max);
    }
    public static void dfs(int dir, int now, int max) {
        // 5회 이상 되면 return 
        if(now>max){
            return;
        }
        if(dir!=-1)round(dir);
        int[][] store = new int[N][N];
        for(int d=0; d<4; d++){
            //현재 값 저장
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    store[i][j] = board[i][j];
                }
            }
            // round
            dfs(d, now+1, max);
            //값  복귀
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    board[i][j] = store[i][j];
                }
            }
        }
    }
    public static void round(int dir){
        for(int i=0; i<N; i++){
            int first = -1;
            int fx, fy; // 갱신해줄위치 -> 현재 읽는 위치가 0이 아닐때, 합쳐지지 않을 때만 칸 이동 
            int x = -1; // 읽는 위치 
            int y = -1;
            if(dir<=1) {
                y=i;
                fy=i;
                x= dir==0? -1 : N;
                fx=x;
            }
            else {
                x=i;
                fx=i;
                y= dir==2? -1 : N;
                fy=y;
            }
            for(int j=0; j<N; j++){
                // 읽는 위치 이동 
                x += dx[next[dir]];
                y += dy[next[dir]];
                //빈 블록은 넘김 
                if(board[x][y]==0)continue;
                
                // 현재 앞에 이미 합쳐진상태거나 블록이 없었을 경우 first=-1
                if(first == -1) {
                    // 지금 블록값으로 갱신
                    fx += dx[next[dir]];
                    fy += dy[next[dir]];
                    first = board[x][y];
                    board[fx][fy] = first;
                    if(fx!=x || fy!=y) board[x][y]=0;
                    continue;
                }
                // 합침
                else if(first==board[x][y] ){
                    first= -1;
                    board[fx][fy] = board[x][y]*2;
                    if(board[fx][fy]>Max) Max = board[fx][fy];
                    if(fx!=x || fy!=y) board[x][y]=0;
                }
                // 합치지 않고 그냥 이동만 시킴
                else {
                    fx += dx[next[dir]];
                    fy += dy[next[dir]];
                    first = board[x][y];
                    board[fx][fy] = board[x][y];
                    if(fx!=x || fy!=y) board[x][y]=0;
                }
            }
        }
    }
}

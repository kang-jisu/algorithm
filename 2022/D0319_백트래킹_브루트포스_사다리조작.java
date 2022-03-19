/*
백준 15684 사다리조작
https://www.acmicpc.net/problem/15684

사다리 3개 미만만 주어졌으니 모든 경우에서 사다리 3개 이하로 설치하는 경우 백트래킹으로 만들고
그때 i->i가 되는지를 브루트포스로 확인하면 됐다.
사용하는 개념은 브루트포스,백트래킹으로 뭔가 어느정도 해본건데 사다리를 2차원배열에서 가로줄이 있을때 0,1,2로 체크하는 개념이 바로 안와닿았따..
거의 답보면서 풀음 ㅠㅠ 그리고 행은 이전 행 볼 필요 없는데 열은 1열부터 다시 찾아줘야해서 마지막 테케 계속 틀렸었다..

*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ladder {
    static int N,M,H;
    static int[][] map;
    static boolean isFinish;
    static final int MAX = 3;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+1][N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b]=1; // 오른쪽으로가는 사다리
            map[a][b+1]=2; // 왼쪽으로 가는 사다리 존재 
        }

        for(int i=0; i<=MAX; i++){
            answer = i;
            dfs(1,0); // 더 적은 개수로 호출할 수 있으면 break시키기위해서 
            if(isFinish) break;
        }
        System.out.println(isFinish ? answer : -1);
    }

    public static void dfs(int y , int cnt){
        if(isFinish) return;
        if( answer == cnt) {
            if(check()) isFinish = true;
            return;
        }
        for(int i=y; i<=H; i++){
            for(int j=1; j<N; j++){ // 여기를 1부터 탐색해줘야함 
                if(map[i][j]==0 && map[i][j+1]==0) {
                    map[i][j]=1;
                    map[i][j+1]=2;
                    dfs(i, cnt+1);
                    map[i][j]=0;
                    map[i][j+1]=0;
                }
            }
        }
    }

    public static boolean check(){
        for(int i=1; i<=N; i++){
            int x = 1;
            int y = i;
            while(x<=H) {
                if(map[x][y]==1) y++;
                else if(map[x][y]==2) y--;
                x++;
            }
            if(y!=i) return false;
        }

        return true;
    }
}


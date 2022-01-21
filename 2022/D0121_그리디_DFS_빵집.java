package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bakery {

    static int R, C;
    static int []flag;
    static int [][]visit;
    static int cnt;

    static int d[]={-1,0,1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        flag = new int[R+1];
        visit = new int[R+1][C+1];
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j=0; j<C; j++){
                if(s.charAt(j)=='.') visit[i][j]=0;
                else visit[i][j]=1;
            }
        }

        cnt = 0;
        for(int i=0; i<R; i++){
            dfs(i,0, i);
        }
        System.out.println(cnt);
    }
    static void dfs(int x, int y, int start) {
        visit[x][y]=1;

        if(y==C-1) {
            cnt ++;
            flag[start]=1;
            return;
        }
        for(int i=0; i<3; i++){
            int A = d[i]+x;
            int B = y+1;
            if( A>=0 && A<R && visit[A][B]==0) {
                if (flag[start] == 1) return;
                dfs(A, B, start);
            }
        }
    }
}

/*
백준 3109
방향이 위대각선, 가운데, 아래대각선이고 위에부터 채우는게 최선이므로 (greedy)
그 순서대로 탐색해서 마지막지점에 도달하면 return 
마지막 지점에 도달했는지 여부를 판단해서 dfs탐색중에도 return을 시켜줌
global 변수로 했는데 dfs return type을 boolean으로해서 탈출하는법을 알아봐야게다.
*/

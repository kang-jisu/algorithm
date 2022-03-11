/**
방문 배열이 그냥 알파벳 26개짜리면된다. 한번 방문한 알파벳은 못가므로!!
dfs+ 백트래킹을 이용해서 풀었다

백준 1987 알파벳 dfs 백트래킹 
https://www.acmicpc.net/problem/1987
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {
    static int N;
    static int M;
    static char[][] map;
    static boolean[] alphabets;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        alphabets = new boolean[26];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j);
            }
        }
        alphabets[map[0][0]-'A']=true;
        max = 1;
        dfs(0,0,1);
        System.out.println(max);
    }
    public static void dfs(int x, int y,int cnt) {
        if(max < cnt) max= cnt;
        for(int i=0; i<4; i++){
            int A = x + dx[i];
            int B = y + dy[i];
            if(A>=0 && B>=0 && A<N && B<M ){
                if(alphabets[map[A][B]-'A']==false) {
                    alphabets[map[A][B]-'A'] = true;
                    dfs(A,B,cnt+1);
                    alphabets[map[A][B]-'A'] = false;
                }
            }
        }
    }
}

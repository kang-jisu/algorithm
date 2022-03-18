/*
백준 토마토 7569
https://www.acmicpc.net/problem/7569
결과 출력하는거 if 출력하고 return 안해서 80몇퍼에서 계속 틀렸다 ㅠㅠ 
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {
    static int M, N, H;
    static int[][][] box;
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {0,0,0,0,-1,1};
    static class Pos {
        int x;
        int y;
        int z;
        int day;
        Pos(int x, int y, int z, int day){
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }
    static Queue<Pos> q = new LinkedList<>();
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[N][M][H];
        cnt = 0;
        for(int h=0; h<H; h++){
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    box[i][j][h] = Integer.parseInt(st.nextToken());
                    if(box[i][j][h]==1){
                        q.add(new Pos(i, j, h,1));
                    }
                    else if(box[i][j][h]==0){
                        cnt++; //토마토 후보
                    }
                }
            }
        }
        if(q.isEmpty() && cnt!=0) System.out.println(-1);
        else { // else안해줘서 틀렸었음 
            int result = bfs();
            if(q.isEmpty() && cnt!=0) System.out.println(-1);
            else System.out.println(result-1);
        }
    }
    public static int bfs(){
        int max = 1;
        while(!q.isEmpty()) {
            Pos pos = q.poll();
            if(pos.day > max) max = pos.day;
            for(int d=0; d<6; d++){
                int A = pos.x + dx[d];
                int B = pos.y + dy[d];
                int C = pos.z + dz[d];
                if(A>=0 && B>=0&& C>=0 && A<N && B<M && C<H) {
                    if(box[A][B][C]==0 ){
                        cnt--;
                        q.add(new Pos(A,B,C,pos.day+1));
                        box[A][B][C] = pos.day+1;
                    }
                }
            }
        }
        return max;
    }
}

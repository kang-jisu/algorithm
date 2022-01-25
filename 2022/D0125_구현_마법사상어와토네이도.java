package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tornado {

    static int N;
    static int[][] map;
    static int dx[] ={0,1,0,-1};
    static int dy[]= {-1, 0,1,0 };
    static int over;
    static double[] p = new double[]{0.05, 0.1, 0.1, 0.02, 0.02, 0.07, 0.07, 0.01, 0.01};

    static int[][] px = new int[][] {
            {0,-1,1, -2,2,-1,1,-1,1,0},
            {2,1,1,0,0,0,0,-1,-1,1},
            {0,-1,1, -2,2,-1,1,-1,1,0},
            {-2,-1,-1,0,0,0,0,1,1,-1}
    };
    static int[][] py = new int[][] {
            {-2,-1,-1,0,0,0,0,1,1,-1},
            {0,-1,1, -2,2,-1,1,-1,1,0},
            {2,1,1,0,0,0,0,-1,-1,1},
            {0,-1,1, -2,2,-1,1,-1,1,0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int x = N/2;
        int y= N/2;
        int len = 1;
        int d=0;
        over= 0;
        while(true) {
            for(int i=0; i<2; i++) {
                for(int j=0; j<len; j++) {
                    x = x + dx[d];
                    y = y + dy[d];
                    move(x,y,d);
                    if(x==0 && y==0) break;
                }
                d = (d+1)%4;
                if(x==0 && y==0) break;
            }
            len++;
            if(x==0 && y==0) break;
        }
        System.out.println(over);
    }

    static void move(int x, int y, int d){
        int A = x;
        int B = y;
        int amount = map[A][B];
        int remain = amount;
        for(int i=0; i<9; i++){
            int Px = A + px[d][i];
            int Py = B + py[d][i];

            int sand = (int) (map[A][B]*p[i]);
            remain -= sand;
            if( Px>=0 && Px<N && Py>=0 && Py<N) {
                map[Px][Py] += sand;
            }
            else over += sand;
        }
        int Px = A + px[d][9];
        int Py = B + py[d][9];
        if( Px>=0 && Px<N && Py>=0 && Py<N) {
            map[Px][Py] += remain;
        }
        else over += remain;

    }

}
/*
백준  20057 마법사상어와토네이도
이거풀어서 입사한건데 . . . 틀려버렸다 . . 
미리 Px, Py, P 이용해서 곱할 곳에 대한 배열 만들어주고 계산하니까 코드 복잡하게 안하고 풀 수 있었다.
*/

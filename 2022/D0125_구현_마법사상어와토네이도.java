package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tornado {

    static int N;
    static int[][] map;
    static int dx[] ={0,1, 0, -1};
    static int dy[]= {-1,0,1,0};
    static int over;

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
        int cnt = 0;
        int d=0;
        over= 0;
        while(true) {
            for(int i=0; i<2; i++) {
                for(int j=0; j<len; j++) {
                    move(x,y,d);
                    map[x][y]=0;

                    x = x + dx[d];
                    y = y + dy[d];
                    if(x==0 && y==-1) break;
                }
                d = (d+1)%4;
                if(x==0 && y==-1) break;
            }
            len++;
            if(x==0 && y==-1) break;
        }
        System.out.println(over);
    }

    static void move(int x, int y, int d){
        int A = x;
        int B = y;
        int amount = map[A][B];
        int remain = amount;
        // 5
        A = x+dx[d]*3;
        B = y+dy[d]*3;
        remain -= (amount*5)/100;
        if(A>=0 && A<N && B>=0 && B<N) {
            map[A][B] += (amount*5)/100;
        }
        else over += (amount*5)/100;

        A = x+dx[d]*2;
        B = y+dy[d]*2;
        // 10
        int A1 =  A+dx[(d+1)%4];
        int B1 = B+dy[(d+1)%4];
        remain -= (amount*10)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] += (amount*10)/100;
        }
        else over += (amount*10)/100;

        A1 =  A+dx[(d+3)%4];
        B1 = B+dy[(d+3)%4];
        remain -= (amount*10)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] += (amount*10)/100;
        }
        else over += (amount*10)/100;
        // 7
        A = x+dx[d];
        B = y+dy[d];
        A1 =  A+dx[(d+1)%4];
        B1 = B+dy[(d+1)%4];
        remain -= (amount*7)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] +=  (amount*7)/100;
        }
        else over +=(amount*7)/100;
        A1 =  A+dx[(d+1)%4]*2;
        B1 = B+dy[(d+1)%4]*2;

        remain -= (amount*2)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] +=(amount*2)/100;
        }
        else over +=  (amount*2)/100;
        A1 =  A+dx[(d+3)%4];
        B1 = B+dy[(d+3)%4];

        remain -= (amount*7)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] += (amount*7)/100;
        }
        else over += (amount*7)/100;
        A1 =  A+dx[(d+3)%4]*2;
        B1 = B+dy[(d+3)%4]*2;

        remain -= (amount*2)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] +=(amount*2)/100;
        }
        else over += (amount*2)/100;
        // 1
        A1 =  x+dx[(d+1)%4];
        B1 = y+dy[(d+1)%4];
        remain -= (amount*1)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] += (amount*1)/100;
        }
        else over += (amount*1)/100;

        A1 =  x+dx[(d+3)%4];
        B1 = y+dy[(d+3)%4];
        remain -= (amount*1)/100;
        if(A1>=0 && A1<N && B1>=0 && B1<N) {
            map[A1][B1] +=  (amount*1)/100;
        }
        else over +=  (amount*1)/100;

        // a
        A = x+dx[d]*2;
        B = y+dy[d]*2;
        if(A>=0 && A<N && B>=0 && B<N) {
            map[A][B] += remain;
        }
        else over += remain;


    }

}


/* 푸는중 */

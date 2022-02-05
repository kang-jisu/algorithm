/*
백준 폴짝게임 
문제링크 : https://www.acmicpc.net/problem/17498
알고리즘 : DP

일단 현재 위치에서 D만큼의 이동범위중에 가장 최대가되는 곳을 고르는 작업을 반복적으로 하고있으므로 그부분을 DP로 저장해서 아예 다 계산한뒤에 N-1행까지 왔을때 최대값을 구해주면된다.
처음에는 0~M-1로 시작해서 모든 계산을 다 해줬는데 틀렸습니다가 떠서 힌트가 DP인걸보고 다시 생각해봤다.

0행은 dp[0][j]=0으로 초기화해준다.
dp[i][j]까지 왔을때 여러 경로들 중에 어쨌든 최댓값만 저장해두면 됨 
max의 초기값이 0 이아니라 음수가 나올 수 있으므로 MIN_VALUE로 설정해주었다.

*/

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpGame {

    static int N, M, D;
    static int [][] array;
    static int [][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        array = new int[N+1][M+1];
        dp = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                dp[i][j]= Integer.MIN_VALUE;
            }
        }
        for( int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++){
            dp[0][i]=0;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
              // 현재 위치에서 D만큼 이동할 수 있는 곳중에 최대값을 계산 
              // 지금 위치까지 온 dp[i][j] + 현재위치에서 이동하는 array[i][j]*array[A][B] 
                for(int x=1; x<=D; x++){
                    for(int y= -(D-x); y<=(D-x); y++){
                        int A = i+x;
                        int B = j+y;
                        if(A<N && B<M && B>=0 && dp[A][B]< dp[i][j] + array[i][j]* array[A][B]) {
                            dp[A][B] = dp[i][j] + array[i][j]*array[A][B];
                        }
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int j=0; j<M; j++){
            if( dp[N-1][j]>max) max = dp[N-1][j];
        }
        System.out.println(max);
    }

}

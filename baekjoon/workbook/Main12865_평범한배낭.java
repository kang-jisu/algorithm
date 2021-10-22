package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12865_평범한배낭 {

    /*
    N개의 물건 1<=N<=100
    무게 W와 가치 V 1<=W<=100,000 | 0<=V<=1000
    조건 : K만큼의 무게만을 넣을 수 있는 배낭만 들고다닐 수 있음 1<=K<=100,000
    구현 : 배낭에 넣을 수 있는 물건들의 가치의 최댓값

    분류 : 다이나믹 프로그래밍, 배낭

    배낭을 쪼갤 수 없는 경우 -> DP (0/1 knapsack문제)
    쪼갤 수 있는경우 -> greedy로 품

    배낭 수 x 총 무게 dp로 해서. -> 2차원배열에서 1차원배열로 줄일 수 있음
    참고블로그 : https://st-lab.tistory.com/141
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N,K;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        int [][] dp = new int[N+1][K+1];
        int [] dp = new int[K+1];
        int [] W = new int [N+1];
        int [] V = new int [N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

//        for(int i=1; i<=N; i++){
//            for(int j=1; j<=K; j++){
//                if(W[i]>j) dp[i][j] = dp[i-1][j];
//                else {
//                    dp[i][j] = Math.max(dp[i-1][j], V[i]+dp[i-1][j-W[i]]);
//                }
//            }
//        }
//        System.out.println(dp[N][K]);
        for(int i=1; i<=N; i++){
            for(int j=K; j>=W[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-W[i]]+V[i]);
            }
        }
        System.out.println(dp[K]);
    }
}


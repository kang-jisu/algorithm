/*
주말동안 코테 푸느라고  .. .
비슷한 유형 문제 나온 기본가볍게풀고 대체.. ㅠ 실버문제지만 ..
카드구매하기 1은 최대 2는 최소 https://www.acmicpc.net/problem/16194 백준 
DP

*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuyCard {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] P = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        for(int i=0; i<=N; i++){
            dp[i] = 10000000;
        }
        for(int i=1; i<=N; i++){
            dp[i] = P[i];
            for(int j=0; j<=i; j++){
                dp[i] = Math.min(dp[i], dp[i-j] + dp[j]);
            }
        }
        System.out.println(dp[N]);
    }
}

/*
최장공통부분수열 2차원배열로 구하기..
이 문제 푸는 방식 익히기.
백준 9251번 LCS https://www.acmicpc.net/problem/9251 골드5
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    // 같으면 i-1, j-1 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int x = A.length();
        int y = B.length();
        System.out.println(dp[x][y]);
        // 만약에 그 수열을 출력해야한다면 아래처럼 ..
        // x-1, y 또는 x, y-1이 자신과 같을 때 까지 이동하다 모두 숫자가 작아지면 sb에 넣고,, 반복
//        StringBuilder sb = new StringBuilder();
//        while (dp[x][y] > 0) {
//            if (dp[x][y] == dp[x - 1][y]) {
//                x = x - 1;
//                continue;
//            } else if (dp[x][y] == dp[x][y - 1]) {
//                y = y - 1;
//                continue;
//            } else {
//                sb.append(B.charAt(y - 1));
//                x = x - 1;
//                y = y - 1;
//            }
//        }
//        System.out.println(sb.reverse());
    }
}

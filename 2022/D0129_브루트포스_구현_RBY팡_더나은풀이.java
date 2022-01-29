package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class RBYPang1 {

    static int N;
    static int[] balls;

    static int min ;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        min = N;
        balls = new int[N];
        int idx=0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            balls[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<N; i++){
            for(int j=1; j<=3; j++){
                if(balls[i]==j) continue;
                pang(i-1, i+1, j);
            }
        }
        System.out.println(min);
    }

    static void pang(int left,  int right, int color ){
        int cnt = N;
        int tmpCnt=1; // 지금 공도 바꾸는 카운트에 포함하므로
        while(true) {
            while(left>=0 ){
                if(balls[left]!=color)break;
                tmpCnt++;
                left--;
            }
            while(right<N) {
                if(balls[right]!=color) break;
                tmpCnt++;
                right++;
            }
            if(tmpCnt<4) break;
            cnt-=tmpCnt;
            if(left<0 || right>=N || balls[left] != balls[right]) break;
            color = balls[left]; // 다음 공 색끼리도 같을경우 초기화
            tmpCnt= 0; // 이땐 0인상태로 숫자 세야함
        }
        min = Math.min(min, cnt);
    }

}


/*
백준 5577
브루트포스로 해서 색깔 1,2,3번 바꿔가면서 다 비교해보는것
채점현황 풀이 보면서 괜찮아보이는 코드 같아서 다시 풀어봤다.
	15260	KB 168ms
*/

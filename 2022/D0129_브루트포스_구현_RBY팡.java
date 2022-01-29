package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class RBYPang {

    static int N;
    static Ball[] balls;

    static class Ball {
        int color;
        int cnt;
        Ball(int color, int cnt){
            this.color = color;
            this.cnt = cnt;
        }
    }
    static int min ;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        min = N;
        balls = new Ball[N];
        int idx=0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            if(i==0) balls[idx] = new Ball(tmp,1);
            else {
                if(balls[idx].color == tmp){
                    balls[idx].cnt++;
                    continue;
                }
                balls[++idx] = new Ball(tmp,1);
            }
        }
        for(int i=1; i<idx; i++){
            pang(balls,i,idx);
        }
        System.out.println(min);
    }

    static void pang(Ball[] balls,int now, int idx) {
        Ball nowBall = balls[now];
        if(balls[now-1].color != balls[now+1].color) return;
        Stack<Ball> left = new Stack<>();
        Stack<Ball> right = new Stack<>();
        for(int i=0; i<now; i++){
            left.push(balls[i]);
        }
        for(int i=idx; i>now; i--){
            right.push(balls[i]);
        }
        boolean change = false;
        while (left.peek().color == right.peek().color) {
            if (left.peek().cnt + right.peek().cnt >= 4) {
                change=true;
                left.pop();
                right.pop();
            }
            else break;
            if(left.isEmpty() || right.isEmpty() ) break;
        }

        if(change) {
            int leftSum = 0;
            int rightSum = 0;
            while(!left.isEmpty())leftSum+=left.pop().cnt;
            while(!right.isEmpty())rightSum+=right.pop().cnt;
            min = Math.min(leftSum+rightSum, min);
        }
    }
}

/*
백준 5577 RBY팡
메모리	215552KB 시간	556ms
힌트에 스택이 나와있길래 왼쪽,오른쪽을 스택에 넣어서 하나씩 빼면서 찾아봤는데 52번 줄 안넣었을땐 메모리초과도 나고
N이 10000인데 너무 비효율적으로 짠거같다..
그래도 답 안보고 혼자 풀어본문제!
*/

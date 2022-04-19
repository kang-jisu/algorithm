/*
백준 https://www.acmicpc.net/problem/2493 2493 탑
작은거 나오면 pop하고 큰거나오면 출력하는식으로 스택을 이용하면 된다.
힌트에 스택인거 보기전까진 감이안왔고
스택인거 보고 좀 복잡하게 풀고있었ㄴ드데 (ArrayList<Stack>>)
그냥 스택 하나가지고 o(n)에 풀수있는 것 같다.
*/

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Top {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Stack<int[]> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int top = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()) {
                if(stack.peek()[1] >= top) {
                    System.out.print(stack.peek()[0]+" ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) {
                System.out.print("0 ");
            }
            stack.push(new int[]{i, top});
        }

    }
}

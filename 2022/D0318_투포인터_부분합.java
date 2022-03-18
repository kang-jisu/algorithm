/*
백준 1806 부분합 골드 4
https://www.acmicpc.net/problem/1806

투포인터
처음엔 큐로 풀었다. 
len값 갱신해주는 부분 안넣어서 84%에서 틀렸었다. 조심..~~ 

*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PartionSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N+1];
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int len = Integer.MAX_VALUE;
        int start = 0 ;
        int end = 0;
        while(start<=N && end<=N) {
            if(sum>=S && len > end-start) len = end-start;
            if(sum<S){
                sum+=nums[end++];
            }
            else {
                sum-=nums[start++];
            }
        }
// 큐로 구하는 방법
//        Queue<Integer> q = new LinkedList<>();
//        for(int i=0; i<N; i++){
//            q.add(nums[i]);
//            sum+=nums[i];
//            while(sum-q.peek()>=S){
//                sum -= q.poll();
//                if(q.size()<len) len = q.size();
//            }
//            if(sum>=S && q.size()<len)len= q.size();
//        }
        System.out.println(len==Integer.MAX_VALUE ? 0 : len);
    }
}



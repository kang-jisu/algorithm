package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1655_가운데를말해요 {

    /*
    mergesort로 하니 시간초과 남
    정렬을 하지 않고 풀어야하는것..

    pq로 mid보다 작은 pq, 큰 pq만들어서 linear하게 풀어야하고
    짝수개수일때 작은걸로 출력하게 해야함.

    출력도 StringBuilder로 해서 시간초과 해결함. 
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N,M;
        N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> lpq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> rpq = new PriorityQueue<>();

        int mid = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            if(i==0){
                mid = M;
                sb.append(mid+"\n");
                continue;
            }
            if(M<mid){
                lpq.add(M);
            }
            else {
                rpq.add(M);
            }
            if(lpq.size()-rpq.size()>0){
                rpq.add(mid);
                mid = lpq.poll();
            }
            else if(rpq.size()-lpq.size()>1){
                lpq.add(mid);
                mid = rpq.poll();
            }
            sb.append(mid+"\n");
        }
        System.out.println(sb.toString());
    }
}

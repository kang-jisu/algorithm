/*
세그먼트 트리.연속적으로 존재하는 데이터의 특정한 범위의  부분합을 구할 때 LogN 시간에 구할 수 있는 방법
이진 트리 구조로 부모노드가 자식 노드의 합을 저장하는 상태

백준 2042 구간합구하기 https://www.acmicpc.net/problem/2042
start,end,left,right 이런거 잘 구분해서 쓰기.. 
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SegmentTree {
    static long[] tree;
    static long[] input;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        input = new long[N+1];
        for(int i=1; i<=N; i++){
            input[i] = Long.parseLong(br.readLine());
        }
        int h = (int) Math.ceil(Math.log(N)/Math.log(2)) ;
        int arrSize = (int) Math.pow(2, h+1);
        tree = new long[arrSize];
        // 부모노드 * 2는 왼쪽 자식, 부모노드 *2+1는 오른쪽 자식으로 구하기 쉽도록 시작 인덱스를 1로 설정함
        makeTree(1, 1,N);
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if(cmd==1) {
                Long diff = b - input[a];
                input[a] = b;
                updateNum(1,1,N,  a, diff);
            }
            else System.out.println(intervalSum(1,1,N,  a, (int) b));
        }
    }

    public static long makeTree(int idx, int left, int right){
        if( left==right) {
            return tree[idx] = input[left];
        }
        int mid = (left+right)/2;
        return tree[idx] = makeTree(idx*2, left, mid) + makeTree(idx*2+1, mid+1, right);
    }

    // start, end-> 지금 탐색중인 노드 범위 , left, right : 구간합 구하고자 하는 범위 , index: tree배열에서 현재 노드 가리키는 인덱스
    public static long intervalSum(int index, int start, int end, int left, int right){
        if( left > end || right < start )return 0;
        if( left <= start && end <= right) return tree[index];
        else {
            int mid = (start+end)/2;
            return intervalSum(index*2,start, mid,  left, right) + intervalSum( index*2+1,mid+1, end, left, right);
        }
    }

    public static void updateNum( int now, int start, int end,int node, long diff) {
        if( node > end || node < start ) return;
        tree[now] += diff;
        if( start != end) {
            int mid = (start+end)/2;
            updateNum(now*2, start, mid, node, diff);
            updateNum(now*2+1,mid+1, end,  node, diff);
        }
    }
}

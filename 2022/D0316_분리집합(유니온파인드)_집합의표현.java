/*
백준 https://www.acmicpc.net/problem/1717 1717번 집합의 표현
유니온파인드 기본문제
return parent[a] = find(parent[a])를 하는 이유는 그러면서 depth를 줄일 수 있음. 자손을 바로 하위자식으로 만들기
이거 출력이 많아서 sb로 하면 시간 줄어듦
*/

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UnionFind {
    static int N,M;
    static int[] parent;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // sb로 시간 줄이기
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=0; i<=N; i++){
            parent[i] = i;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int pa = find(a);
            int pb = find(b);
            if(op==0) {
                // union
                // 번호 작은게 부모가 되게 만들 것
                // 이미 같을경우 넘어가기
                if(pa==pb) continue;
                if(pa>pb) union(pb, pa);
                else union(pa, pb);
            }
            else {
                //find
                if(pa==pb) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
    public static void union(int a, int b) {
        parent[b] = a;
    }
    public static int find(int a){
        if(parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }
}

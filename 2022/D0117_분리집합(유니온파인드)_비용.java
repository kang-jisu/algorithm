import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
 static int find(int n) {
    if(parent[n]==n) return n;
   return parent[n] = find(parent[n]);
 }
 static int N,M;
 static class Edge implements Comparable<Edge> {
   int u;
   int v;
   int w;

   Edge(int u, int v, int w) {
     this.u = u;
     this.v = v;
     this.w = w;
   }
   @Override
   public int compareTo(Edge o) {
     return o.w-this.w;
   }
 }
 static long sum;
 static int[] parent;
 static int[] cnt;
 static ArrayList<Edge> edges;
 static final int MOD = 1000000000;
 public static void main(String[] args) throws IOException {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringTokenizer st = new StringTokenizer(br.readLine());
   N = Integer.parseInt(st.nextToken());
   M = Integer.parseInt(st.nextToken());

   parent = new int[N+1];
   cnt = new int[N+1];
   edges = new ArrayList<>();

    sum=0;
    for(int i=1; i<=N; i++){
     parent[i] = i;
     cnt[i]=1;
   }
    for(int i=0; i<M; i++){
     st = new StringTokenizer(br.readLine());
     int u = Integer.parseInt(st.nextToken());
     int v = Integer.parseInt(st.nextToken());
     int w = Integer.parseInt(st.nextToken());
     edges.add(new Edge(u,v,w));
     sum+=w;
   }
   Collections.sort(edges);
   long result = 0;
    for( Edge cur : edges ){
     result += (sum * union(cur.u, cur.v));
     result%=MOD;
     sum -= cur.w;
   }
   System.out.println(result);
 }


 static long union(int n, int m){
   int pn = find(n);
   int pm = find(m);
    if( pn == pm) return 0;
   long result = (long) cnt[pn] * cnt[pm];
   cnt[pn] += cnt[pm];
   cnt[pm] = 1;
   parent[pm]= pn;
   return result;
 }
}

/*
백준 2463

분리집합 문제 
+ 무엇을 분리하거나 끊는 문제가 나오면 그 문제가 요구사항의 처리 순서를 바꿔도 문제가 없는지 확인한다.
그리고 분리하는것보단 합치는것이 편하다 (union find)
간선을 제거해 나가면서 경우의수를 찾아간다. 
*/

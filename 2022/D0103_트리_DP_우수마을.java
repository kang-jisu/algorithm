import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int[] popular;
  static int[][] dp;
  static int N;
  static ArrayList<Integer>[] edge ;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    popular = new int[N+1];
    edge = new ArrayList[N+1];
    dp = new int[N+1][2];
    st = new StringTokenizer(br.readLine());

    for(int i=1; i<=N; i++){
      popular[i] = Integer.parseInt(st.nextToken());
   }
    for(int i=0; i<=N; i++) {
      edge[i] = new ArrayList<>();
   }
    for(int i=0; i<N-1; i++) {
    st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      edge[a].add(b);
      edge[b].add(a);
   }

    dfs(1,0);
    System.out.println(Math.max(dp[1][0],dp[1][1]));
 }

  public static void dfs(int n , int parent) {
    for( int i : edge[n]){
      if(i!=parent){
        dfs(i, n);
        dp[n][0] += Math.max(dp[i][0], dp[i][1]);
        dp[n][1] += dp[i][0];
     }
   }
    dp[n][1] += popular[n];
 }
}

/*
백준 우수마을
문제링크 : https://www.acmicpc.net/problem/1949
문제티어 : 골드 1
트리구조
1<=N<=10,000
우수 마을로 선정된 마을 주민 수의 총합을 최대로
우수마을 끼리는 이웃 불가, 우수마을과 적어도 하나 인접해있어야함

문제 유형 : 트리, 다이나믹 프로그래밍
dfs방식으로 루트 노드에서 부터 리프 노드까지 내려간 후 , 다시 리프노드에서 부터 위로 올라가며 업데이트
dp배열은 n번 마을이 우수 노드일 경우, 아닐경우로 나눔
top->down (dfs) 후에 leaf에서 bottom up으로 dp
배열 10000*10000 하니 메모리초과 나서 vector이용
vector, arrayList 다 같은데 vector는 쓰레드에 안전한대신 항상 동기화를 하느라고 시간이 오래걸린다고 한다. 멀티쓰레드가 아닌 경우는 ArrayList가 더 빠름.
tree는 부모, 자식으로만 이루어져 있고 한 방향으로만 탐색해면 되니깐 visit 배열 없이 !=parent인것만 파악해서 dfs를 돌리면 되는 것 같다.

참고한 블로그 
https://lotuslee.tistory.com/96 이 블로그 보고 이해했는데 결국 코드도 거의 같아져버렸다.


*/

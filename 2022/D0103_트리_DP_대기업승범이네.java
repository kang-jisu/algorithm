import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SeungBum {

  static int N;
  static int[] skill;
  static ArrayList<Integer>[] edge ;
  static int[][] dp;
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    skill = new int[N+1];
    edge = new ArrayList[N+1];
    dp = new int[N+1][2];

    // 같은 dp를 조회할 경우 이미 계산되어 있으면 바로 그 값을 쓰기 위해
    for(int i=1; i<=N; i++){
      dp[i][0]=-1;
      dp[i][1]=-1;
   }

    for(int i=0; i<=N; i++){
      edge[i] = new ArrayList<>();
   }
    st = new StringTokenizer(br.readLine());
    for(int i=2; i<=N; i++){
      int n = Integer.parseInt(st.nextToken());
      edge[n].add(i);
   }

    st = new StringTokenizer(br.readLine());
    for(int i=1; i<=N; i++){
      skill[i] = Integer.parseInt(st.nextToken());
   }

    System.out.println(dfs(1,0));
 }

  static int dfs(int n, int status) {
    // cache된 값 사용
    if(dp[n][status]!=-1) return dp[n][status];
    dp[n][status]=0;

    if (status==1) { // 부모와 관계를 맺고 있을 경우
      for (int node : edge[n]) {
        dp[n][status] += dfs(node, 0);
     }
   } else {
      int sum = 0;
      // 일단 관계 맺은게 아무것도 없다고 가정
      for (int node : edge[n]) {
        sum += dfs(node, 0);
     }
      dp[n][status]=sum;
      // node는 멘토멘티를 맺은 관계로 다시 지정
      // sum에서 이전에 더해준 값을 빼고, 멘토멘티관계인 값을 더해 MAX와 비교해줌 
      for( int node : edge[n] ){
        dp[n][status] = Math.max(
            dp[n][status],
            sum - dfs(node, 0) + skill[n]*skill[node] + dfs(node,1)
       );
     }
   }
    return dp[n][status];
 }
}

/* 

알고리즘 - 트리 , DP

백준 - 번호 17831 https://www.acmicpc.net/problem/17831

부모-자식 관계가 이루어져 있을 경우/아닌경우로 나누어 `dp[i][0]`, `dp[i][1]`로 구분한다.

dp에 저장된 값을 한번 이상 조회 하기 때문에 dp를 -1로 초기화해주고, -1이 아닌 어떤 값이 있다면 바로 그 값을 사용하는 탈출용 ? 코드가 필요하다.
case 1. 멘토멘티 관계를 맺고있는 노드의 경우 , 그 노드의 자식 노드는 현재 노드의 멘티가 아니므로 dfs(node,0)으로 탐색해준다 ( 이때 반환 되는 값이 `dp[node][0]` 인데 이게 cache되면 바로 구해질 수 있다. )
case2. 멘토멘티 관계를 맺고 있지 않은 노드의 경우,
여러 자식 노드의 값들이 다 sum에 더해질 수 있으므로 모든 자식을 탐색하면서 그 중 max가 되는 특정한 자식만을 선택하여 추가적인 액션을 해야한다.
그래서 일단은 모든 자식과도 멘토멘티 관계가 없는 값으로 `dfs(node,0)`의 sum을 구하고
다시 모든 자식 노드를 탐색하며 `sum - dfs(node,0)` (이전에 sum에 구해놨던 값) `+ dfs(node,1)` (현재 노드와 멘토멘티 관계가 될것이므로 ) + `skill[n]*skill[node]` 이 값을 구해서 Max값으로 업데이트 해주면 된다.
이렇게 구해진 현재 노드의 상태에 따른 값을 return해주면서 탐색을 해준다.


*/

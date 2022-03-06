/*
프로그래머스 코딩테스트연습 네트워크
노드 하나를 탐색했을때 dfs로 방문하지 않은 노드는 연결되어있지 않은 노드 
*/
class Solution {
    int visit[];
    int[][] Computers;
    public int solution(int n, int[][] computers) {
        visit = new int[n];
        Computers = computers;
        int answer = 0;
        for(int i=0; i<n; i++){
            if(visit[i]==0) {
                dfs(i, n);
                answer++;
            }
        }
        return answer;
    }
    public void dfs(int node, int n) {
        visit[node]=1;
        for(int i=0; i<n; i++){
            if( Computers[node][i]==1 &&  visit[i]==0) {
                dfs(i,n);
            }
        }
    }
}

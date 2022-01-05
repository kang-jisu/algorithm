package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Descending {
 static int N, M;
 static int [][] map;
 static int [][] visit;

 static int [] dx = new int[]{-1,1,0,0};
 static int [] dy = new int[]{0,0,-1,1};

 public static void main(String[] args) throws IOException {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringTokenizer st = new StringTokenizer(br.readLine());

   N = Integer.parseInt(st.nextToken());
   M = Integer.parseInt(st.nextToken());

   map = new int[N+1][M+1];
   visit = new int[N+1][M+1];

    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
       visit[i][j]=-1;
     }
   }
    for(int i=0; i<N; i++){
     st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++){
       map[i][j] = Integer.parseInt(st.nextToken());
     }
   }
   System.out.println(explore(0,0));
 }
 static int explore(int x, int y) {
    if(x==N-1 && y == M-1) return 1;
    if(visit[x][y] != -1) return visit[x][y];
   visit[x][y]=0;
    for(int i=0; i<4; i++){
     int A = x+dx[i];
     int B = y+dy[i];
      if(A>=0 && A<N && B>=0 && B<M){
        if( map[x][y] > map[A][B] ){
         visit[x][y] += explore(A,B);
       }
     }
   }
   return visit[x][y];
 }
}
/*
https://www.acmicpc.net/problem/1520
dp,그래프,DFS
그냥 탐색으로하면 시간초과가 난다.
여러번 다닐 수 있으므로 -1로 초기화하고 한번 들린곳은 dp 에 저장해준다.
상하좌우로 갈 수 있으면 그 갈 수 있는데서의 가짓 수를 +1 해주며 목적지에서 출발점까지 다시 dp로 조회하는 문제..
*/

/*
백준 국경 17500
https://www.acmicpc.net/problem/17500
국경이 될 점(선)부분과 나라들을 모두 배열로 넣고 
나라, 바다를 경계(visit[i]=1)로 넣고 visit[0](국경 후보)를 탐색해보면된다.
N->2*N+1으로 늘려서 풀고 
4*N+3으로 늘려서 출력하면 된다. 
*/
package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Border{
    static int N;
    static char[][] map;
    static char[][] newMap;
    static int[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        newMap = new char[2*N+1][2*N+1];
        visit = new int[2*N+1][2*N+1];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i<2*N+1; i++) {
            for(int j=0; j<2*N+1; j++){
                if((i%2==0) && j%2==0) newMap[i][j]='+';
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                newMap[2*i+1][2*j+1] = map[i][j];
                visit[2*i+1][2*j+1]=2;
            }
        }


        //모든 경우를 탐색해서 [2n][2N]에 도착할때 확인
        visit[0][0]=1;
        boolean result = dfs(0,0);
        if(result) {
            System.out.println("yes");
            for(int i=0; i<4*N+3; i++) System.out.print("#");
            System.out.println("");
            for(int i=0; i<2*N+1; i++){
                System.out.print("#");
                if(i%2==0) {
                    for(int j=0; j<2*N+1; j++){
                        if(newMap[i][j]=='+') System.out.print('+');
                        else if(visit[i][j]==1) {
                            System.out.print("---");
                        }
                        else System.out.print("   ");
                    }
                }
                else {
                    for (int j = 0; j < 2 * N + 1; j++) {
                        if (visit[i][j] == 2) System.out.print(" "+newMap[i][j]+" ");
                        else if (visit[i][j] == 1) {
                            System.out.print("|");
                        }
                        else System.out.print(" ");
                    }
                }
                System.out.println("#");
            }
            for(int i=0; i<4*N+3; i++) System.out.print("#");
        }
        else {
            System.out.println("no");
        }

    }
    public static boolean dfs(int x, int y) {
        if( x==2*N && y==2*N) {
          // 끝점에 도착하면 check
            return check();
        }
      // 끝점이 아니라면 갈 수 있는 경로 탐색
        for(int i=0 ;i<4; i++){
            int A = x+dx[i];
            int B = y+dy[i];
            if(A>=0 &&B>=0 && A<=2*N && B<=2*N && visit[A][B]==0) {
              //백트래킹으로 모든 경로 확인 
                visit[A][B]=1;
              // 탐색으로 하나라도 true를 찾았다면 리턴
                if(dfs(A,B)) return true;
                visit[A][B]=0;
            }
        }
        return false;
    }
    public static boolean check(){
      // 만들어낸 국경이 조건에 부합하는지 확인 
        int [][] tmp = new int[2*N+1][2*N+1];
        int M = 2*N+1;
        for(int i=0; i<2*N+1; i++){
            for(int j=0; j<2*N+1; j++){
                tmp[i][j] = visit[i][j];
            }
        }
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                if(tmp[i][j]==2 && newMap[i][j]!='.') {
                  // 나라를 발견하면 국경에 쌓여있는 부분에 해당 나라와 같은것만 있는지 확인. 다른게 있으면 바로 false 리턴 
                    if(checkdfs(i, j, tmp, newMap[i][j])==false)
                        return false;
                }
            }
        }
        return true;
    }
    public static boolean checkdfs(int x, int y, int[][] tmp, char c){
        tmp[x][y]=1;
        for(int i=0; i<4; i++){
            int A= x+dx[i];
            int B=y+dy[i];
            if(A>=0 &&B>=0 && A<=2*N && B<=2*N && tmp[A][B]!=1 ) {
              // .이 아닌 나라가 c와 다르면 false
              if(A%2==1 && B%2==1 && newMap[A][B]!='.' && newMap[A][B]!=c)
                    return false;
                else if(checkdfs(A,B,tmp,c)==false)
                    return false;
            }
        }
        return true;
    }
}

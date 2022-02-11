/*
카카오 21년 신입공채 카드 짝맞추기 
https://programmers.co.kr/learn/courses/30/lessons/72415

https://www.youtube.com/watch?v=aZfzE4jIIMU
잘 못풀겠어서 유튜브 보고 거의 따라쳤다.ㅠ 
4X4에 6! X 2 라서 완탐으로 푼다. BFS로 거기까지 가는데 최단거리 구하면서 ,,

6! -> 첫번째나오는카드 먼저 선택할건지, 두번째나오는카드 먼저선택할건지 에대해서 순열 

-> 한칸씩이동해보기, 끝까지이동해보기로 bfs 최소 구하면 된다..

*/

import java.util.*;
class Solution {
    class Card {
        int x;
        int y;
        int cnt;
        Card(int x, int y, int cnt) {
            this.x = x;
            this.y= y;
            this.cnt = cnt;
        }
    }
    static final int INF = 987654321;
    
    int[][] Board;
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutation(new Card(r,c,0));
    }
    
    public int bfs(Card src, Card dest ){
        boolean[][] visit = new boolean[4][4];
    
        Queue<Card> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()) {
            Card cur = q.poll();
            if(cur.x == dest.x && cur.y == dest.y) {
                return cur.cnt;
            }
            
            for(int i=0; i<4; i++){
                int A = cur.x + dx[i];
                int B = cur.y + dy[i];
                if( A<0 || A>3 || B<0 || B>3 )continue; 
                if( !visit[A][B]) {
                    visit[A][B]=true;
                    q.add(new Card(A,B,cur.cnt+1));
                }

                for(int j=0; j<2; j++){
                    if(Board[A][B]!=0) break;
                    if( A+dx[i]<0 || A+dx[i]>3 || 
                      B+dy[i]<0 || B+dy[i]>3) break;
                    A += dx[i];
                    B += dy[i];
                }
                if(!visit[A][B]) {
                    visit[A][B]=true;
                    q.add(new Card(A,B,cur.cnt+1));
                }
            }
        }
        return INF;
    }
    
    public int permutation(Card src){
        int ret = INF;
        for(int n=1; n<=6; n++){
            List<Card> cards = new ArrayList<>();
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    if(Board[i][j]==n) {
                        cards.add(new Card(i,j,0));
                    }
                }
            }
            if(cards.isEmpty()) continue;

            int one = bfs(src,cards.get(0) )
                + bfs(cards.get(0), cards.get(1))+ 2;
            
            int two = bfs(src,cards.get(1) )
                + bfs(cards.get(1), cards.get(0))+ 2;
            
            for(int i=0; i<2; i++){
                Board[cards.get(i).x][cards.get(i).y]=0;
            }
            
            ret= Math.min(ret, one + permutation(cards.get(1)));
            ret= Math.min(ret, two + permutation(cards.get(0)));
            
                        
            for(int i=0; i<2; i++){
                Board[cards.get(i).x][cards.get(i).y]=n;
            }
        }
        
        if(ret ==INF) return 0;
        return ret;
    }
}

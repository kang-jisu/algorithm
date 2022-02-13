/*
21년 카카오 인턴 거리두기확인하기 
https://programmers.co.kr/learn/courses/30/lessons/81302
5x5라 그냥 확인하는것도 되고 BFS, DFS로 2칸이내에 갈 수 있는 곳에 P가 더 있는지 확인하는 방식을 써도 된다.
*/

import java.util.*;
class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    
    class Node {
        int x;
        int y;
        int cnt ;
        Node(int x, int y, int cnt) {
            this.x=x;
            this.y=y;
            this.cnt=cnt;
        }
    }
    
    public boolean check(String[] place ){
        char[][] room = new char[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                room[i][j] = place[i].charAt(j);
            }
        }
        // dfs, bfs로 2칸이내 갈 수 있는곳에 P가 있는지 확인한다.
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(room[i][j]=='P'){
                    System.out.println(i+","+j);
                    int[][] visit = new int[5][5];
                    Queue<Node> q = new LinkedList();
                    q.add(new Node(i,j,0));
                    visit[i][j]=1;
                    int cnt = 0;
                    while(!q.isEmpty()) {
                        Node poll = q.poll();
                        if(room[poll.x][poll.y]=='P' && (poll.x!=i || poll.y!=j) ) {
                            System.out.println(i+","+j);
                            return false;
                        }
                        if(poll.cnt==2) continue;
                        for(int d=0; d<4; d++){
                            int A = poll.x + dx[d];
                            int B = poll.y + dy[d];
                            if(A<0 || B<0 || A>4 || B>4) continue;
                            if(room[A][B]!='X' && visit[A][B]==0) {
                                visit[A][B]=1;
                                q.add(new Node(A,B,poll.cnt+1));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    // int[] dx = {-1,-1,-1,0,0,1,1,1};
    // int[] dy = {-1,0,1,-1,1,-1,0,1};
    // int[] dx2 = {-2,2,0,0};
    // int[] dy2 = {0,0,-2,2};
    // public boolean check(String[] place) {
    //     char[][] room = new char[5][5];
    //     for(int i=0; i<5; i++){
    //         for(int j=0; j<5; j++){
    //             room[i][j] = place[i].charAt(j);
    //         }
    //     }
    //     for(int i=0; i<5; i++){
    //         for(int j=0; j<5; j++){
    //             if(room[i][j]!='P') continue;
    //             for(int d=0; d<8; d++){
    //                 int A = i+dx[d];
    //                 int B = j+dy[d];
    //                 if(A<0 || B<0 || A>4 || B>4) continue;
    //                 if(room[A][B]=='P') {
    //                     if(i==A || B==j) return false;
    //                     if(room[A][j]!='X'||room[i][B]!='X') return false;
    //                 }
    //             }
    //               for(int d=0; d<4; d++){
    //                 int A = i+dx2[d];
    //                 int B = j+dy2[d];
    //                 if(A<0 || B<0 || A>4 || B>4) continue;
    //                 if(room[A][B]=='P') {
    //                     if(i==A && j<B && room[A][B-1]!='X') return false;
    //                     if(i==A && j>B && room[A][B+1]!='X') return false;
    //                     if(j==B && i<A && room[A-1][B]!='X') return false;
    //                     if(j==B && i>A && room[A+1][B]!='X') return false;
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }
    public int[] solution(String[][] places) {
        
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++){
            answer[i] = check(places[i])?1:0;
        }
        return answer;
    }
}

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Key {

    static int N;
    static int H,W;
    static int[][] visit;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Pos {
        int x;
        int y;
        Pos(int x, int y){
            this.x = x;
            this.y =y;
        }
    }

    static int cnt;
    static Queue<Pos> q;
    static Queue<Pos>[] doorq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        visit = new int[102][102];
        for(int n=0; n<N; n++) {

            doorq = new Queue[26];
            for(int i=0; i<26; i++){
                doorq[i] = new LinkedList<>();
            }
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            int[] key = new int[26];
            char[][] map = new char[H+2][W+2];

            cnt = 0;

            for (int i = 1; i <=H; i++) {
                st = new StringTokenizer(br.readLine());
                String tmp = st.nextToken();
                for (int j = 1; j <=W; j++) {
                    map[i][j] = tmp.charAt(j-1);
                    if(map[i][j]=='*') visit[i][j]=1;
                }
            }
            for(int i=0; i<=H+1; i++){
                map[i][0]='.';
                map[i][W+1]='.';
            }
            for(int j=0; j<=W+1; j++ ){
                map[0][j]='.';
                map[H+1][j]='.';
            }

            st = new StringTokenizer(br.readLine());
            String tmpKey = st.nextToken();
            if(!tmpKey.equals("0")) {
                for (int i = 0; i < tmpKey.length(); i++) {
                    key[tmpKey.charAt(i) - 'a'] = 1;
                }
            }

            q.add(new Pos(0,0));
            while(!q.isEmpty()) {
                Pos pos = q.poll();

                for(int dd=0; dd<4; dd++) {
                    int A = pos.x + dx[dd];
                    int B = pos.y + dy[dd];
                    if(A>=0 && A<=H+1 && B>=0 && B<=W+1) {
                        if(map[A][B]=='*' || visit[A][B]==1 ) continue;
                        visit[A][B]=1;

                        if(map[A][B]>='A' && map[A][B]<='Z') {
                            if(key[map[A][B]-'A']!=0) {
                                q.add(new Pos(A,B));
                            }
                            else {
                                doorq[map[A][B]-'A'].add(new Pos(A,B));
                            }
                        }
                        else if(map[A][B]>='a' && map[A][B]<='z') {
                            q.add(new Pos(A,B));
                            if(key[map[A][B]-'a']==0) {
                                key[map[A][B] - 'a'] = 1;
                                while(!doorq[map[A][B]-'a'].isEmpty()){
                                    q.add(doorq[map[A][B]-'a'].poll());
                                }
                            }

                        }
                        else {
                            q.add(new Pos(A,B));
                            if(map[A][B]=='$') {
                                cnt++;
                            }
                        }
                    }
                }
            }

            System.out.println(cnt);
            for(int i=0; i<H+2; i++){
                for(int j=0; j<W+2; j++){
                    visit[i][j]=0;
                }
            }

        }
    }

}

/*
 백준 9328
 시간초과엄청났다 .. 테두리에 '.'으로 넣고 (0,0)부터 시작하는 아이디어랑
 doorq를 알파벳개수만큼 만들어서 계속 확인해주는거랑, doorq를 열쇠를 만날때만 검색하는걸로 시간을 줄여야함
 
*/

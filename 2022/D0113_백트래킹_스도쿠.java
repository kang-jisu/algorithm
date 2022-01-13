

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sudoku {

    static class Pos{
        int x;
        int y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int [][]board;
    static int [][]res;
    static ArrayList<Pos> zero;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        res = new int[9][9];
        zero = new ArrayList<>();
        flag = false;
        for(int i=0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==0) {
                    zero.add(new Pos(i, j));
                }
            }
        }

        sudoku(0);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void sudoku(int now) {
        if(flag) return;
        if(now==zero.size()) {
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    res[i][j]=board[i][j];
                }
            }
            flag =true;
            return;
        }
        Pos pos = zero.get(now);
        for(int k=1; k<=9; k++){
            if(check(pos,k)) {
                board[pos.x][pos.y] = k;
                sudoku(now+1);
                board[pos.x][pos.y] =0;
            }
            if(flag) return;
        }
    }
    static boolean check(Pos pos, int n) {
         boolean result = true;
         for(int i=0; i<9; i++){
             if(board[pos.x][i]==n) {
                 return false;
             }
             if(board[i][pos.y]==n) {
                 return false;
             }
         }
         int A = pos.x/3;
         int B = pos.y/3;
         for(int i=0; i<3; i++){
             for(int j=0; j<3; j++) {
                 if(board[A*3+i][B*3+j]==n) {
                     return false;
                 }
             }
         }
         return true;
    }
}

/*
백준 2580 백트래킹 

스도쿠를 채우게되면 바로 종료하니까 900초대에서 408ms로 확 줄었다. System.exit(0)으로 종료하기도 하던데 이거로 써또 되는지 모르겠어서 그냥 flag를 주었다..

*/


/*
카카오 2022 신입공채 
https://programmers.co.kr/learn/courses/30/lessons/92344
파괴되지 않은 건물
누적합으로 O(N*M*K)를 O(N*M+K)번만에 해결 
어떻게 이런 생각을하지 ~~ 
*/

class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int [][] degrees = new int[board.length+1][board[0].length+1];
        for(int s = 0; s<skill.length; s++) {
            int degree = skill[s][0]==1 ? -skill[s][5] : (skill[s][5]);
            int x1 = skill[s][1];
            int y1 = skill[s][2];
            int x2 = skill[s][3];
            int y2 = skill[s][4];
            degrees[x1][y1] += degree;
            degrees[x2+1][y2+1] += degree;
            degrees[x1][y2+1] -= degree;
            degrees[x2+1][y1] -= degree;
        }
        for(int i=0; i<degrees.length; i++){
            for(int j=1; j<degrees[i].length; j++) {
                degrees[i][j] = degrees[i][j-1]+degrees[i][j];
            }
        }

        for(int i=0; i<degrees[0].length; i++){
            for(int j=1; j<degrees.length; j++) {
                degrees[j][i] = degrees[j-1][i]+degrees[j][i];
            }
        }

        
        int answer = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j] += degrees[i][j];
                if(board[i][j]>0) answer++;
            }
        }
        return answer;
    }
}

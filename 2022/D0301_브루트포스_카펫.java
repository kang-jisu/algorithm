/**
프로그래머스 브루트포스 LV2 카펫 
공식을 찾아서 풀면 되는거였다.

yellow + brown은 총 칸 넓이랑 같고
yellow는 row-2 * col-2인거.. 
*/
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int S = brown + yellow;
        
        for(int i=1; i<=S; i++){
            int row = i;
            int col = S/row;
            if(row>col) continue;
            if( (row-2)*(col-2) == yellow) {
                answer[0]=col;
                answer[1]=row;
                break;
            }
        }
        return answer;
    }
}

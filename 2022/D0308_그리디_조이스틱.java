/**
프로그래머스 그리디 조이스틱

1. 처음부터 끝까지 쭉 간다
2-1. 오른쪽으로 가다가 A가 많이 반복되면 다시 온만큼돌아가서 거꾸로간다
2-1. 거꾸로 탐색했다가 중간에 A가 많이 반복되면 다시 온만큼 오른쪽으로가서 처음부터시작해서 오른쪽으로 탐색

2에 대한거는 move 변수에 넣고 계속 갱신해주면서 한번만 더하면 된다. 
https://velog.io/@jeeseob5761/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A1%B0%EC%9D%B4%EC%8A%A4%ED%8B%B1
블로그 참고했다.
*/

class Solution {
    public int solution(String name) {
        
        int answer = 0;
        int cur=0;
        int move=name.length()-1; // 처음부터 끝까지 가는 경우 
        int index;
        for(int i=0; i<name.length(); i++){
            
            index = i+1;
            while(index<name.length() && name.charAt(index)=='A') index++;
            
            move = Math.min(move, i*2+name.length()-index);
            move = Math.min(move, (name.length()-index)*2+i);
            
            int c = (int)name.charAt(i)-'A';
            answer += c>13? 26-c : c-0; 
        }
        return answer+move;
    }
}

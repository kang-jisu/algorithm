/*
21년 인턴 Lv3 표 편집
https://programmers.co.kr/learn/courses/30/lessons/81303
런타임에러, 실패를 자꾸 못잡는다 ㅠ 혼자풀어서도 잘 되야할텐데..
연속된값들 계속 삭제,추가 하고 위아래로 움직이니깐 LinkedList로 구현 
약간 프로시험문제같은 스타일이었다.
스택에서 pop한건 그대로 노드 정보가 남아있으니깐 양옆만 다시 설정해주면된다.

*/
import java.util.*;
class Solution {
    
    class Node {
        Node prev;
        Node next;
        boolean removed;
        Node(){
            removed=false;
            prev = null;
            prev = null;
        }
    }
    
    Node[] NodeArr = new Node[1000000];
    public String solution(int n, int k, String[] cmd) {
        
        // 삭제 스택 생성 
        Stack<Node> s = new Stack<>();
        
        // 노드 초기화 
        for(int i=0; i<n; i++){
            NodeArr[i] = new Node();
        }
        
        // 연결관계 설정 
        for(int i=1; i<n; i++){
            NodeArr[i-1].next = NodeArr[i];
            NodeArr[i].prev = NodeArr[i-1];
        }
        
        // 시작 노드 
        Node now = NodeArr[k];
        
        // 임시 변수 
        Node up;
        Node down;
        int cnt = -1;
        for(String info : cmd) {
            String[] key = info.split(" ");
            switch(key[0]){
                case "D":
                    cnt = Integer.parseInt(key[1]);
                    while(cnt-->0){
                        now = now.next;
                    }
                    break;
                case "U":
                    cnt = Integer.parseInt(key[1]);
                    while(cnt-->0) {
                        now = now.prev;
                    }
                    break;
                case "C":
                    s.push(now);
                    now.removed=true;
                    up = now.prev;
                    down = now.next;
                    // 
                    if(up!=null) {
                        up.next = down;
                    }
                    
                    if(down!=null) {
                        down.prev = up; 
                        now = down;
                    }else { // 마지막값일땐 바로 이전값으로 
                        now = up;
                    }
                    break;
                case "Z":
                    Node pop = s.pop();
                    pop.removed=false;
                    up = pop.prev;
                    down = pop.next;
                    if( up!=null ){ // up==null인경우는 pop이 0번
                        up.next = pop;
                    }
                    if( down!=null) { // down==null인경우는 pop이 n-1번 
                        down.prev = pop;
                    }
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(NodeArr[i].removed) {
                sb.append("X");
            }
            else sb.append("O");
        }
        return sb.toString();
    }
}

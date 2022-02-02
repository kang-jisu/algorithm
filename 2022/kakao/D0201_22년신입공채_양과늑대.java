/*
카카오 22년 신입공채 
양과늑대 https://programmers.co.kr/learn/courses/30/lessons/92343
DFS(BFS)로 완탐이나 2^17이므로 상태에대한 모든 경우 비트마스킹으로 푸는 것 같음.
다시 풀어보기 
*/


import java.util.*;
class Solution {
    int[] visit;
    int sheepCnt;
    ArrayList<Integer>[] graph;
    void bfs(int node, int sheep, int wolf, ArrayList<Integer> list, int[] info) {
        
        int nextSheep = sheep + (info[node]^1);
        int nextWolf = wolf + info[node];
        sheepCnt = Math.max(sheepCnt, nextSheep);

        // 현재 노드에 갈 수 없는상태 ( 늑대>=양)이면 return
        if(nextSheep<=nextWolf) return;
        
        // 후보 리스트 생성 
        ArrayList<Integer> nextList = new ArrayList<>();
        nextList.addAll(list);
        for(int nextNode : graph[node]) {
            nextList.add(nextNode);
        }
        // 현재 노드는 지워줌 
        nextList.remove(Integer.valueOf(node));
        System.out.print("node:"+node+", sheep:"+nextSheep+", wolf:"+nextWolf+",list: {");
        for(int i : nextList) {
            System.out.print(i+",");
        }
        System.out.println("}");
        
        // 다음 갈 수 있는 리스트 확인 
        for(int nextNode : nextList) {
            bfs(nextNode, nextSheep, nextWolf, nextList, info);
        }
    }
    
    /*
    info[0] : 양
    info[1] : 늑대
    */
    public int solution(int[] info, int[][] edges) {
        visit = new int[info.length];
        sheepCnt = 0;
        graph = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            graph[i]= new ArrayList<>();
        }
        for(int i=0; i<edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        bfs(0,0,0,list, info);
        int answer = sheepCnt;
        return answer;
    }
}


/**
	테스트를 통과하였습니다.
출력 〉	node:0, sheep:1, wolf:0,list: {1,8,}
node:1, sheep:2, wolf:0,list: {8,2,4,}
node:8, sheep:2, wolf:1,list: {2,4,7,9,}
node:7, sheep:3, wolf:1,list: {2,4,9,}
node:2, sheep:3, wolf:2,list: {4,9,}
node:9, sheep:4, wolf:2,list: {4,10,11,}
node:4, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {4,11,}
node:11, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:3, wolf:2,list: {2,9,3,6,}
node:9, sheep:4, wolf:2,list: {2,3,6,10,11,}
node:2, sheep:4, wolf:3,list: {3,6,10,11,}
node:3, sheep:4, wolf:3,list: {2,6,10,11,}
node:6, sheep:4, wolf:3,list: {2,3,10,11,5,}
node:5, sheep:5, wolf:3,list: {2,3,10,11,}
node:2, sheep:5, wolf:4,list: {3,10,11,}
node:3, sheep:5, wolf:4,list: {2,10,11,}
node:10, sheep:5, wolf:4,list: {2,3,11,}
node:11, sheep:5, wolf:4,list: {2,3,10,}
node:10, sheep:4, wolf:3,list: {2,3,6,11,}
node:11, sheep:4, wolf:3,list: {2,3,6,10,}
node:9, sheep:4, wolf:1,list: {2,4,10,11,}
node:2, sheep:4, wolf:2,list: {4,10,11,}
node:4, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {4,11,}
node:11, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:4, wolf:2,list: {2,10,11,3,6,}
node:2, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {2,11,3,6,}
node:11, sheep:4, wolf:3,list: {2,10,3,6,}
node:3, sheep:4, wolf:3,list: {2,10,11,6,}
node:6, sheep:4, wolf:3,list: {2,10,11,3,5,}
node:5, sheep:5, wolf:3,list: {2,10,11,3,}
node:2, sheep:5, wolf:4,list: {10,11,3,}
node:10, sheep:5, wolf:4,list: {2,11,3,}
node:11, sheep:5, wolf:4,list: {2,10,3,}
node:3, sheep:5, wolf:4,list: {2,10,11,}
node:10, sheep:4, wolf:2,list: {2,4,11,}
node:2, sheep:4, wolf:3,list: {4,11,}
node:4, sheep:4, wolf:3,list: {2,11,3,6,}
node:11, sheep:4, wolf:3,list: {2,4,}
node:11, sheep:4, wolf:2,list: {2,4,10,}
node:2, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:4, wolf:3,list: {2,10,3,6,}
node:10, sheep:4, wolf:3,list: {2,4,}
node:9, sheep:3, wolf:1,list: {2,4,7,10,11,}
node:2, sheep:3, wolf:2,list: {4,7,10,11,}
node:7, sheep:4, wolf:2,list: {4,10,11,}
node:4, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {4,11,}
node:11, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:3, wolf:2,list: {2,7,10,11,3,6,}
node:7, sheep:4, wolf:2,list: {2,10,11,3,6,}
node:2, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {2,11,3,6,}
node:11, sheep:4, wolf:3,list: {2,10,3,6,}
node:3, sheep:4, wolf:3,list: {2,10,11,6,}
node:6, sheep:4, wolf:3,list: {2,10,11,3,5,}
node:5, sheep:5, wolf:3,list: {2,10,11,3,}
node:2, sheep:5, wolf:4,list: {10,11,3,}
node:10, sheep:5, wolf:4,list: {2,11,3,}
node:11, sheep:5, wolf:4,list: {2,10,3,}
node:3, sheep:5, wolf:4,list: {2,10,11,}
node:7, sheep:4, wolf:1,list: {2,4,10,11,}
node:2, sheep:4, wolf:2,list: {4,10,11,}
node:4, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {4,11,}
node:11, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:4, wolf:2,list: {2,10,11,3,6,}
node:2, sheep:4, wolf:3,list: {10,11,3,6,}
node:10, sheep:4, wolf:3,list: {2,11,3,6,}
node:11, sheep:4, wolf:3,list: {2,10,3,6,}
node:3, sheep:4, wolf:3,list: {2,10,11,6,}
node:6, sheep:4, wolf:3,list: {2,10,11,3,5,}
node:5, sheep:5, wolf:3,list: {2,10,11,3,}
node:2, sheep:5, wolf:4,list: {10,11,3,}
node:10, sheep:5, wolf:4,list: {2,11,3,}
node:11, sheep:5, wolf:4,list: {2,10,3,}
node:3, sheep:5, wolf:4,list: {2,10,11,}
node:10, sheep:4, wolf:2,list: {2,4,11,}
node:2, sheep:4, wolf:3,list: {4,11,}
node:4, sheep:4, wolf:3,list: {2,11,3,6,}
node:11, sheep:4, wolf:3,list: {2,4,}
node:11, sheep:4, wolf:2,list: {2,4,10,}
node:2, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:4, wolf:3,list: {2,10,3,6,}
node:10, sheep:4, wolf:3,list: {2,4,}
node:10, sheep:3, wolf:2,list: {2,4,7,11,}
node:7, sheep:4, wolf:2,list: {2,4,11,}
node:2, sheep:4, wolf:3,list: {4,11,}
node:4, sheep:4, wolf:3,list: {2,11,3,6,}
node:11, sheep:4, wolf:3,list: {2,4,}
node:11, sheep:3, wolf:2,list: {2,4,7,10,}
node:7, sheep:4, wolf:2,list: {2,4,10,}
node:2, sheep:4, wolf:3,list: {4,10,}
node:4, sheep:4, wolf:3,list: {2,10,3,6,}
node:10, sheep:4, wolf:3,list: {2,4,}
node:2, sheep:2, wolf:1,list: {8,4,}
node:4, sheep:2, wolf:1,list: {8,2,3,6,}
**/

/*
카카오 2021년 기출 합승택시요금
다익스트라,플로이드와샬로 최단거리 구해서 계산
https://programmers.co.kr/learn/courses/30/lessons/72413

mins[i]+mina[i]+minb[i] 이거의 최소값을 구하면 되는데 조금 애먹었다.
다익스트라 3번만하는게 전체 플로이드돌리는거보다 빨라서 각각 구해줬다. 
*/

import java.util.*;
class Solution {
    class Node implements Comparable<Node> {
        int e;
        int w;
        Node(int e, int w){
            this.e=e;
            this.w=w;
        }
        @Override
        public int compareTo(Node o){
            return this.w -o.w;
        }
    }
    ArrayList<Node>[] list;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        list = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            list[i]= new ArrayList<>();
        }
        for(int[] fare: fares) {
            list[fare[0]].add(new Node(fare[1], fare[2]));
            list[fare[1]].add(new Node(fare[0], fare[2]));
        }
        int []minA = new int[n+1];
        int []minB = new int[n+1];
        int []minS = new int[n+1];
        for(int i=0; i<=n; i++){
            minA[i]= Integer.MAX_VALUE;
            minB[i]= Integer.MAX_VALUE;
            minS[i]= Integer.MAX_VALUE;
        }
        dijkstra(a, minA);
        dijkstra(b, minB);
        dijkstra(s, minS);
        // 이 부분이 포인트
        int answer = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            answer = Math.min( answer, minS[i] + minA[i] + minB[i]);
        }
        return answer;
    }
    public void dijkstra(int node, int[] minArr){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(node, 0));
        minArr[node]=0;
        while(!pq.isEmpty()) {
            Node poll = pq.poll();
            if(minArr[poll.e] < poll.w) continue;
            for( Node next : list[poll.e]) {
                if(minArr[next.e] > poll.w + next.w){
                    minArr[next.e] = poll.w + next.w;
                    pq.add(new Node(next.e, poll.w+next.w));
                }
            }
        }
    }
}

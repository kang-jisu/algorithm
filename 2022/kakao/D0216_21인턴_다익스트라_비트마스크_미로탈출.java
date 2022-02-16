/*
어려웠다. ㅠㅠ 보면서품 
https://programmers.co.kr/learn/courses/30/lessons/81304/solution_groups?language=java
풀이 https://www.youtube.com/watch?v=B1CjIauForM
*/
import java.util.*;
class Solution {
    private final static int MAX_N = 1001;
    private final static int INF = Integer.MAX_VALUE;
    int[][] graph = new int[MAX_N][MAX_N]; // 인접행렬 방식 구현 \
    /**
    * return int 최단경로 반환
    * 출발점, 도착점, 함정 값 입력 받음
    */
    public int dijkstra(int n, int src, int dst, int[] traps) {
        // 우선순위 큐 구현
        // 타입은 int 배열로 하면 됨
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> a[0]-b[0]);
        // 10개 비트 사용한다는 의미는 1을 10번 이동
        boolean[][] visited = new boolean[MAX_N][1 << 10 ]; // visited[node][state]
        pq.add(new int[]{0, src, 0});
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0]; // cost
            int u = curr[1]; // 도착 번호
            int state = curr[2];
            // 지금도착한 번호가 도착점이면 바로 반환
            if( u == dst ) return w;
            // 이미 방문했으면 skip
            if( visited[u][state]) continue;
            visited[u][state]=true;
            // 지금 노드, 가려고하는 노드 -> 둘중 하나라도 함정이면 역방향
            // 둘다 함정 또는 함정발동 X 면 정방향
            boolean currTrapped = false;
            // 지금 도착한 노드 상태 변경하면서
            // 모든 노드에 대해서 확인하기 위해서 미리 넣어둠 들어가있는 노드가 함정이 발동된노드로 나중에 contains로 확인해줄 것
            HashMap<Integer, Boolean> trapped = new HashMap<>();
            for( int i = 0; i < traps.length; i++) {
                // 함정에 해당하는 비트
                int bit =  1 << i;
                // 0이 아니면 그 비트가 켜져있는거 -> 꺼줘야함
                if( (state & bit) != 0) {
                    if(traps[i] == u ) {
                        state &= ~bit; // 반전시킨뒤 앤드연산 . 이 비트만 꺼줌
                    } else {
                        trapped.put(traps[i], true);
                    }
                } else {
                    // 함정 발동되지 않은 상태 -> 도착했으니 켜줘야함
                    if(traps[i]==u) {
                        state |= bit;
                        trapped.put(traps[i], true);
                        // 지금 노드 true로
                        currTrapped = true;
                    }
                }
            }
            for(int v=1; v<=n; v++) {
                if(v==u) continue;
                // 함정 발동되어있는 노드
                boolean nextTrapped = trapped.containsKey(v) ? true : false;
                if(currTrapped == nextTrapped) {
                    // 정방향
                    if( graph[u][v]!= INF) {
                        pq.add(new int[]{w+graph[u][v], v, state});
                    }
                } else {
                    if( graph[v][u]!= INF) {
                        pq.add(new int[]{w+graph[v][u], v, state});
                }
             }
            }
        }
            return INF;
    }
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        // 초기화
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j) graph[i][j]=0;
                else graph[i][j]=INF;
            }
        }
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            graph[u][v] = Math.min(graph[u][v], w);
        }
        return dijkstra(n, start, end, traps);
    }
}

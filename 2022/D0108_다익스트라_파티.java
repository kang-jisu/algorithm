import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{

    static int N,M,X;
    static int [][] dist;
    static ArrayList<Edge>[] road;
    static class Edge {
        int node;
        int weight;
        Edge(int _node, int _weight){
            this.node = _node;
            this.weight = _weight;
        }
    }
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dist[i][j]= Integer.MAX_VALUE;
            }
        }
        pq = new PriorityQueue<Edge>((o1,o2) -> Integer.compare(o1.weight, o2.weight));

        road = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            road[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            road[from].add(new Edge(to, weight));
        }

        for(int i=1; i<=N; i++){
            dijkstra(i);
        }
        int max = 0;
        for(int i=1; i<=N; i++){
            if( dist[i][X] + dist[X][i] > max ){
                max = dist[i][X] + dist[X][i];
            }
        }
        System.out.println(max);
    }

    static void dijkstra(int n){
        //다익스트라 초기화
        dist[n][n]=0;
        pq.add(new Edge(n,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 해당 노드의 비용이 dist에 있는것보다 크다면 스킵
            if(dist[n][cur.node] < cur.weight) continue;

            for( Edge next : road[cur.node]) {
                if( dist[n][next.node] > cur.weight + next.weight) {
                    dist[n][next.node] = cur.weight + next.weight;
                    pq.add(new Edge(next.node, dist[n][next.node]));
                }
            }
        }

    }
}
/*
우선순위큐 이용 , visit 배열 대신 값으로 비교
x와의 거리만 구하면 되는데 나머지 노드에 대해서 다 구해줘야함 
-> reverse graph를 이용해 값을 거꾸로 저장해 X를 시작점으로하는 다익스트라 2번 수행하면 줄일 수 있음 
*/

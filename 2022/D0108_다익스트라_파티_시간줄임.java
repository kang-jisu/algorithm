import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Party {

    static int N,M,X;
    static int [] dist, redist;
    static ArrayList<Edge>[] road;
    static ArrayList<Edge>[] reroad;
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
        dist = new int[N+1];
        redist = new int[N+1];
        for(int i=1; i<=N; i++){
            dist[i]= Integer.MAX_VALUE;
            redist[i] = Integer.MAX_VALUE;
        }
        pq = new PriorityQueue<Edge>((o1,o2) -> Integer.compare(o1.weight, o2.weight));

        road = new ArrayList[N+1];
        reroad = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            road[i] = new ArrayList<>();
            reroad[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            road[from].add(new Edge(to, weight));
            reroad[to].add(new Edge(from, weight));
        }


        dijkstra(X, dist, road);
        dijkstra(X, redist, reroad);
        int max = 0;
        for(int i=1; i<=N; i++){
            if( dist[i]+ redist[i] > max ){
                max = dist[i]+ redist[i];
            }
        }
        System.out.println(max);
    }

    static void dijkstra(int n, int[] dist, ArrayList<Edge>[] road){
        //다익스트라 초기화
        int [] visit = new int[N+1];
        dist[n]=0;
        pq.add(new Edge(n,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visit[cur.node]!=0) continue;;
            visit[cur.node] =1;

            for( Edge next : road[cur.node]) {
                if( dist[next.node] > cur.weight + next.weight) {
                    dist[next.node] = cur.weight + next.weight;
                    pq.add(new Edge(next.node, dist[next.node]));
                }
            }
        }

    }
}


/*
백준 1238
메모리 19292kb 시간 208ms
이거말고 다른 파일 코드는 메모리 60352kb 시간 572

다익스트라를 모든 노드 말고 X에 대해서만 2번 수행함 
*/

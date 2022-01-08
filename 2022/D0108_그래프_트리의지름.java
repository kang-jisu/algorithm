import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int to;
        int w;
        Edge(int _to, int _w){
            this.to =_to;
            this.w = _w;
        }
    }
    static int N;
    static ArrayList<Edge>[] tree;
    static int max;
    static int maxNode;
    static int visit[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visit = new int[N+1];
        tree = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            tree[A].add(new Edge(V,E));
            tree[V].add(new Edge(A,E));
        }

        max=0;
        maxNode=1;
        visit[1]=1;
        dfs(1,0);
        for(int i=0; i<=N; i++){
            visit[i]=0;
        }
        visit[maxNode]=1;
        max = 0;
        maxNode = maxNode;
        dfs(maxNode, 0);
        System.out.println(max );
    }

    static void dfs(int n, int sum){
        if(max < sum ) {
            max= sum;
            maxNode = n;
        }
        for( Edge e : tree[n]) {
            if (visit[e.to]==0) {
                visit[e.to]=1;
                dfs(e.to,  sum + e.w);
            }
        }
    }
}


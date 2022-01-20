package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DrawingTree {

    static int N;
    static ArrayList<Integer>[] inputtree;
    static ArrayList<Integer>[] tree;
    static int[] parent;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        inputtree = new ArrayList[N+1];
        tree = new ArrayList[N+1];
        parent = new int[N+1];
        dp = new int[1000004][20];
        for(int i=0; i<=N; i++){
            tree[i] = new ArrayList<>();
            inputtree[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            inputtree[u].add(v);
            inputtree[v].add(u);
        }

        findParent(1,0);
        int result = 10000000;
        for(int i=1; i<=18; i++){
            result = Math.min(result, find(1, i));
        }
        System.out.println(result);
    }

    static void findParent(int node, int par) {
        for(int i : inputtree[node]){
            if(i!=par) {
                tree[node].add(i);
                findParent(i, node);
            }
        }
    }
    static int find(int node, int color) {
        if(dp[node][color]!=0) return dp[node][color];

        int result = 0;
        for(int i : tree[node]) {
            int minr=10000000;
            for(int co=1; co<=18; co++ ){
                if(color==co)continue;
                minr = Math.min( minr, find(i, co));
            }
            result += minr;
        }
        dp[node][color] = result+color;
        return dp[node][color];
    }

}

/*
트리 DP 백준 1693
[100000][100000] 을 할 수 없으니 color가 되는 후보 수를 줄여야하는데
8:27
color는 결국 100000>2^N 이어서 최대 18개라고한다.
8:29
그다음엔 bottom-up으로 dp cache해주면서 color의 min값 찾고, 그걸 제외한 min값을 계속 찾아주는 방식으로 풀면 된다..
*/

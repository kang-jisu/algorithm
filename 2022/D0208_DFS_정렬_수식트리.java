/*
백준 17501
마이너스의 개수만큼 정렬된 배열에서 작은 값을 빼주면 되는것은 생각했는데
마이너스가 -(-)가되면 플러스가 되니까 리프노드까지 몇번 마이너스가 불렸는지 확인한 후에 더할지 뺄지 봐줘야한다.

*/
package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MathTree {
    static class Node{
        int count;
        int left;
        int right;
        boolean positive;
        Node(int left, int right, boolean positive, int count) {
            this.left=left;
            this.right=right;
            this.positive = positive;
            this.count = count;
        }
    }
    static int front, rear;
    static int N;
    static Node[] nodes;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        list.add(-10001); // index0은 안쓰는값 넣어줌
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
         nodes = new Node[2*N];
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);
            int  a = Integer.parseInt(st.nextToken());
             int b = Integer.parseInt(st.nextToken());
            nodes[N+i] = new Node(a,b, op=='+'?true:false,0);
        }

        front= 1;
        rear= list.size()-1;
        int sum= dfs(2*N-1,0);
        System.out.println(sum);
    }
    static int dfs(int node,int cnt){
        if(node<=N) {
            if( cnt%2==0) return list.get(rear--); // 큰수
            else return list.get(front++); // 홀수->작은수
        }
        Node cur = nodes[node];
        int result = 0;
        if(cur.positive) {
            // plus
            result += dfs(cur.left, cnt);
            result += dfs(cur.right,cnt) ;
        }
        else {
            //minus
            result += dfs(cur.left, cnt);
            result -= dfs(cur.right,cnt+1) ;
        }
        return result;
    }
}

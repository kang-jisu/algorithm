import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CardGame {

    static int N,M,K;
    static boolean[] usable;
    static int[] next;
    static ArrayList<Integer> select;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        usable = new boolean[N+1];
        next = new int[N+1];
        select = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int temp = Integer.parseInt(st.nextToken());
            select.add(temp);
        }
        select.sort(Comparator.naturalOrder());
        int idx =1;
        for(int i : select) {
            usable[i]=true;
            while(idx<=N) {
                if(idx >= i ) break;
                next[idx] = i;
                idx++;
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int temp = Integer.parseInt(st.nextToken());
            System.out.println(find(temp));
        }
    }

    static int find(int num){
        if(usable[next[num]]) {
            usable[next[num]]=false;
            return next[num];
        }
        return next[num] = find(next[num]);
    }
}

/* 
백준 16566
이진탐색, 분리집합
잘 모르겠음
https://velog.io/@wooky9633/%ED%94%8C%EB%9E%985-%EB%B0%B1%EC%A4%80-16566-%EC%B9%B4%EB%93%9C-%EA%B2%8C%EC%9E%84
이거 보고 풀었다.. 
이진탐색 upper_bound사용하기도 한다고함
*/

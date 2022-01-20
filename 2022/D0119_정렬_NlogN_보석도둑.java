package studyJava;

import java.io.*;
import java.util.*;

public class JewerlyThief{

    static int N, K;
    static int[] C;
    static class Jewerly{
        int m;
        int v;
        Jewerly(int m , int v){
            this.m = m;
            this.v = v;
        }
    }
    static Jewerly[] list;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new Jewerly[N];
        C = new int[K];
        pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[i] = new Jewerly(m,v);
        }

        Arrays.sort(list, new Comparator<Jewerly>() {
            @Override
            public int compare(Jewerly o1, Jewerly o2) {
                if( o1.m == o2.m ){
                    return o2.v - o1.v;
                }
                else return o1.m - o2.m;
            }
        });

        for(int i=0; i<K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(C);
        long sum = 0;
        int idx = 0;
        for(int k = 0; k<K; k++ ) {
            while( idx < N && list[idx].m <= C[k] ) {
                pq.add(list[idx++].v);
            }
            if(!pq.isEmpty()) sum += pq.poll();
        }
        bw.write(sum+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}




/*
백준 1202
C가 여러개인줄 몰랐다 . .
C가 작은 것 부터 확인하면서 보석을 가방에 넣기위해 계속 정렬, 뽑기를 해야하는데
시간초과가 남
큐에서 다시 빼는과정을 없애보기.
-> C를 미리 정렬하고 , C보다 작은값만 pq에 넣으면 pq를 업데이트하지 않아도 가능한 값 중에서 찾을 수 있다.
근데 또 시간초과 ㅠ (C정렬을 1번만 하기 )

pq에 넣는게 Jewerly가 아니라 그냥 value값을 넣어도된다. 
*/

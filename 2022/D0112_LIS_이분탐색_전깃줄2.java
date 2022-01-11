import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class PowerCode2 {

    static class Order{
        int index;
        int num;
        Order(int index, int num){
            this.index=index;
            this.num=num;
        }
    }
    static int N;
    static int[][] cost;
    static int[] dp;
    static ArrayList<Integer> pos;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        cost = new int[N+1][2];
        dp = new int[N+1];
        pos = new ArrayList<>();

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cost, new Comparator<int []>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        dp[1]=cost[1][1];
        pos.add(1);
        int idx= 1;
        for(int i=2; i<=N; i++){
            if(dp[idx] < cost[i][1]) {
                dp[++idx] = cost[i][1];
                pos.add(idx);
            }
            else {
                // lower bound로 작은 값 찾기
                int begin = 1;
                int end = idx;
                while(begin<end ){
                    int mid = (begin+end)/2;
                    if(dp[mid]>=cost[i][1]) {
                        end = mid;
                    }
                    else {
                        begin = mid+1;
                    }
                }
                dp[end] = cost[i][1];
                pos.add(end);
            }

        }
        System.out.println(N-idx);

        int now = idx;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=N; i>=1; i--) {
            if(now == pos.get(i-1)){
                now--;
            }
            else {
                temp.add(cost[i][0]);
            }
        }
        for(int i=temp.size()-1; i>=0; i--){
            System.out.println(temp.get(i));
        }

    }
}

/*
LIS를 이분탐색으로 푸는데 인덱스를 저장해야한다
LIS DP를 갱신할때마다 그때의 index 값을 저장해주고
pos배열을  뒤에서부터 읽으면서 이미 채워진 index는 지워주기위해 temp배열에 저장
temp배열을 다시 거꾸로 출력해주면된다.
 */

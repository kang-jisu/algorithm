import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class PowerCode {

    static int N;
    static int[][] cost;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        cost = new int[N+1][2];
        dp = new int[N+1];
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


        for(int i=1; i<=N; i++){
            dp[i]=1;
            for(int j=1; j<i; j++){
                if( cost[i][1] > cost[j][1])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        int max =1;
        for(int i=1; i<=N; i++){
            if(dp[i]>max) max = dp[i];
        }
        System.out.println(N-max);
    }
}
/*
백준 2565
LIS가장 긴 증가하는 부분수열, DP
A 전깃줄을 오름차순으로 정렬하고 B도 오름차순이 되면 엉키지 않으므로 
B의 LIS를 찾아줌 
N이 작아서 DP로 가능하다.. 
답은 제거해야하는 전깃줄 수 이므로 N-max
*/

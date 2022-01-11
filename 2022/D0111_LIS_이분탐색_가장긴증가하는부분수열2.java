import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS2 {

    static int N;
    static int[] array;
    static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        array = new int[N+1];
        lis = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int idx=1;
        lis[1] = array[1];
        for(int i=2; i<=N; i++){
            int value = array[i];
            if( lis[idx]>array[i]) {
                int left = 1;
                int right = idx;
                while(left< right ){
                    int mid = (left+right)/2;
                    if(lis[mid]>=value) {
                        right = mid;
                    }
                    else {
                        left= mid+1;
                    }
                }
                lis[right] = array[i];
            }
            else {
                if( lis[idx]!=array[i]) {
                    lis[++idx] = array[i];
                }
            }
        }

        System.out.println(idx);
    }
}


/*
백준 12015
전깃줄2 기초 문제
DP로 풀기엔 n이 너무 커서 이분탐색 nlogn으로 찾아주어야한다.
lis 배열을 채울때 45, 12 이렇게 있으면 12로 있어야 뒤에 어떤 수 가 나왔을때 더 긴 수열을 만들수있는 가능성이 있다.
3이 나오면 453은 안되지만 123은 되는거처럼.
그래서 array[i]의 수가 지금 lis[idx]보다 작다면 탐색을 통해 대체해주고, 크다면 수열 길이를 증가시켜서 뒤에 추가해주는 식으로 하면 된다..
참고 블로그 https://jason9319.tistory.com/113
*/

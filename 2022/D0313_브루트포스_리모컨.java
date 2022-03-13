/*
백준 1107 브루트포스 리모컨
https://www.acmicpc.net/problem/1107
0부터 999999(500000이 최대이지만 5,6,7,8 다 안되면 999999부터 내려와야함) 까지 다 탐색하면서 버튼고장안난상태에서 숫자 만들고 +,-로 원하는 숫자까지 가는 거리 계산하는 방식..
ㅠㅠ 풀이법 잘 몰라서 결국 해답 찾아봤다.
자릿수 계산을 string이 아니고 %10,/10 (count함수)로 하니까 시간이랑 메모리 훨씬 절약됐음 
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Remote {
    static boolean[] digits;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        digits = new boolean[10];
        if(M!=0) {
            st = new StringTokenizer(br.readLine()); // M==0일땐 입력 안받음
            for (int i = 0; i < M; i++) {
                int a = Integer.parseInt(st.nextToken());
                digits[a] = true;
            }
        }
        // 초기 채널 100번 . +와 -만으로 이동 가능한 것이 최대 이동 횟수
        int result = Math.abs(N - 100);

        // 고장나지 않은 버튼으로 만들 수 있는 가능한 모든 숫자에 대해서 +,- 이동 값 계산해서 비교
        // 완전탐색
        for(int i=0; i<=999999; i++){
           int len = count(i);
           if(len!=0) {
                int min = Math.abs(N - i) + len;
                result = Math.min(min, result);
            }
        }
        System.out.println(result);
    }
    public static int count(int num){
        if( num==0 ) {
            if(digits[0]==true)
                  return 0;
            else return 1;
        }

        int cnt = 0;
        while( num>0) {
            if(digits[num%10]==true) return 0;
            cnt ++;
            num/=10;
        }
        return cnt;
    }
}


// package com.company;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;

// public class Remote {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         int N = Integer.parseInt(st.nextToken());
//         int M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
//         boolean[] digits = new boolean[10];
//         if(M!=0) st = new StringTokenizer(br.readLine()); // M==0일땐 입력 안받음 
//         for(int i=0; i<M; i++){
//             int a = Integer.parseInt(st.nextToken());
//             digits[a]=true;
//         }
//         // 초기 채널 100번 . +와 -만으로 이동 가능한 것이 최대 이동 횟수
//         int result = Math.abs(N - 100);

//         // 고장나지 않은 버튼으로 만들 수 있는 가능한 모든 숫자에 대해서 +,- 이동 값 계산해서 비교
//         // 완전탐색
//         for(int i=0; i<=999999; i++){
//             String str = String.valueOf(i);
//             int len = str.length();

//             boolean isBreak = false;
//             for(int j=0; j<len; j++){
//                 if(digits[str.charAt(j)-'0']) {
//                     isBreak = true;
//                     break;
//                 }
//             }
//             if( !isBreak) {
//                 int min = Math.abs(N - i) + len;
//                 result = Math.min(min, result);
//             }
//         }
//         System.out.println(result);
//     }
// }

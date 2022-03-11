/*
백준 1011 Fly me to the Alpha Centauri
https://www.acmicpc.net/problem/1011
공식으로 풀 수 있다.
주석처리한거 말고 참고 풀이 https://st-lab.tistory.com/79
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class FlyMeToTheAlphaCentauri {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t=0; t<T; t++){
            st= new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            long C = B-A;
            long sqrt = (long)Math.sqrt(C);
            long count = 2*sqrt - 1;
//            C -= sqrt*sqrt;

            if( C == sqrt*sqrt) {
                System.out.println(count);
            }
            else if( C <= sqrt*sqrt + sqrt) {
                System.out.println( sqrt*2);
            }
            else {
                System.out.println(sqrt*2+1);
            }
//            if( C==0) {
//                System.out.println(count);
//                continue;
//            }
//            while(C>0 && sqrt>0){
//                while( C-sqrt>=0) {
//                    C -= sqrt;
//                    count++;
//                }
//                sqrt--;
//            }
//            System.out.println(count+C);
        }
    }
}

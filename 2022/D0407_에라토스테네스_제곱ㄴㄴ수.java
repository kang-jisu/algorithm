/*
에라토스테네스의 채를 long, 제곱수로 해야되고
min~max 사이가 최대 1,000,000이니까 start값을 잘 잡아주면됨 
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SquareNONO {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        int length = (int)(max-min+1);
        long[] array = new long[length];

        for(long i=2; i*i<=max; i++){
            long square = i*i;
            long start = min%square == 0 ? min/square : (min/square) +1;
            for(long j= start; j*square <= max; j++ ) {
                array[(int)(j*square-min)] = 1;
            }
        }
        int count = 0;
        for(int i=0; i<length; i++){
            if(array[i]==0){
                count++;
            }
        }
        System.out.println(count);
    }
}

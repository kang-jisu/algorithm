/*
백준 5430 AC https://www.acmicpc.net/problem/5430
처음에 스택에 넣고 R할때마다 넣고 빼려고했는데 그럼 10만*10만이라 시간초과가 날거같아서 deque 사용
R할때마다 방향 바꿔주고 방향에 따라서 D일때 -- 해주었다.
1. ""일때 array로 만드는 과정에서 nullPointerException 주의
2. 출력할때 size 0 이면 출력 하지 않아야함. -1 건들수 있음 indexOutOfBound
3. StringBuilder사용해서 출력 
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            String cmd = new StringTokenizer(br.readLine()).nextToken();
            int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            String nums = new StringTokenizer(br.readLine()).nextToken();
            int size, first, last;
            List<Integer> array;
            if( N == 0){
                size = N;
                first = 0;
                last = 0;
                array = new ArrayList<>();
            }
            else {
                size = N;
                first = 0;
                last = N-1;
                array = Arrays.stream(nums.substring(1, nums.length() - 1).split(","))
                        .map(Integer::parseInt).collect(Collectors.toList());
            }
            boolean isFirstStart = true;
            boolean isError = false;
            for(int i=0; i<cmd.length(); i++){
                if(cmd.charAt(i)=='R'){
                    isFirstStart = !isFirstStart;
                }
                else {
                    if(size==0) {
                        isError = true;
                        break;
                    }
                    if(isFirstStart){
                        size--;
                        first++;
                    }
                    else {
                        size--;
                        last--;
                    }
                }
            }
            if(isError) {
                System.out.println("error");
                continue;
            }
            else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if(size!=0) {
                    if (isFirstStart) {
                        for (int i = first; i <= last; i++) {
                            sb.append(array.get(i));
                            if (i != last) sb.append(",");
                        }
                    } else {
                        for (int i = last; i >= first; i--) {
                            sb.append(array.get(i));
                            if (i != first) sb.append(",");
                        }
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}

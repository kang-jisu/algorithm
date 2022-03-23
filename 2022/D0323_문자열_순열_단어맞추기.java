/**
https://www.acmicpc.net/problem/9081
백준 9081 단어맞추기 next_permuation 실제로 구현 
오른쪽에서 읽으면서 오름차순이 끊기는 곳을찾고, 그 인덱스 값보다 큰수 중에 제일 작은수(?) 찾고  swap, 나머지 오름차순 정렬 
wwl study next_permuation
https://github.com/week-we-learn/week-we-learn/blob/main/%F0%9F%92%AB%ED%95%98%EB%8A%98%EC%9D%B4/next_permutation.md

*/

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WordQuiz {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            if(word.length>1) {
                int s = word.length - 2;
                while (s >= 0 && word[s] >= word[s + 1]) s--;
                // 뒤에서부터 봤을 때 37654 처럼 오름차순이 깨지는 순간의 s
                if (s >= 0) {
                    int j = word.length - 1;
                    while (word[j] <= word[s]) j--;
                    swap(word, s, j);
                }

                if(s!=-1) {
                    // s+1부터 length-1까지 오름차순 정렬
                    int a = s + 1;
                    int b = word.length - 1;
                    while (a < b) swap(word, a++, b--); // 나머지는 이미 정렬되어있는 상태니깐 swap으로 정렬
                }
            }
            for(int s=0; s<word.length; s++){
                System.out.print(word[s]);
            }
            System.out.println();
        }
    }

    public static void swap(char[] a, int i, int j){
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}


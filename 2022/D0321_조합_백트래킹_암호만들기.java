/*
백준 1759 암호만들기 
2초라서 진짜그냥 정렬하고 조합해서 체크함녀되는데 괜히 복잡하게 생각했다.. 
https://www.acmicpc.net/problem/1759
*/
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MakeSecret {

    static int L, C;
    static ArrayList<Character> alls;
    static boolean[] alphabets;
    static ArrayList<Character> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alls = new ArrayList<>();
        result = new ArrayList<>();
        alphabets = new boolean[26];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++){
            char ch = st.nextToken().charAt(0);
            alls.add(ch);
        }
        Collections.sort(alls);
        permutation(0,0, new ArrayList<Character>());
    }

    public static void permutation( int cnt,int idx, ArrayList<Character> list){
        if( list.size()==L) {
            if(check(list)) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i) );
                }
                System.out.println();
            }
            return;
        }
        for(int i=idx; i<alls.size(); i++){
            if(alphabets[alls.get(i)-'a']==false ) {
                if(!list.isEmpty() && list.get(list.size()-1)>alls.get(i)) continue;
                for (int j = 0; j < result.size(); j++) {
                    list.add(result.get(j));
                }
                list.add(alls.get(i));
                alphabets[alls.get(i)-'a']=true;
                permutation(cnt + 1, idx + 1, list);
                list.remove(list.size() - 1);
                alphabets[alls.get(i)-'a']=false;
            }
        }
    }
    public static boolean check(ArrayList<Character> list) {

        int vowels=0;
        int con=0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i)=='a' ||list.get(i)=='e' ||list.get(i)=='i' ||list.get(i)=='o' ||list.get(i)=='u' )vowels++;
            else con++;
        }
        if(vowels>=1 && con>=2) return true;
        else return false;
    }
}


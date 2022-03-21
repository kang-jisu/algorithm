/*
아직 푸는중 ㅠ_ㅠ 
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
    static ArrayList<Character> vowels;
    static ArrayList<Character> consonants;
    static ArrayList<Character> alls;
    static boolean[] alphabets;
    static boolean[] visitv;
    static boolean[] visitc;
    static ArrayList<Character> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        vowels = new ArrayList<>();
        consonants = new ArrayList<>();
        alls = new ArrayList<>();
        result = new ArrayList<>();
        alphabets = new boolean[26];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++){
            char ch = st.nextToken().charAt(0);
            if(ch == 'a' || ch == 'e'|| ch == 'i'|| ch == 'o'|| ch == 'u') {
                vowels.add(ch);
            }
            else consonants.add(ch);
            alls.add(ch);
        }
        Collections.sort(vowels);
        Collections.sort(consonants);
        visitv = new boolean[vowels.size()];
        visitc = new boolean[consonants.size()];

        for(int i=0; i<vowels.size(); i++){
            result.add(vowels.get(i));
            alphabets[vowels.get(i)-'a']=true;
            visitv[i]=true;
            con(0);
            result.remove(result.size()-1);
            alphabets[vowels.get(i)-'a']=false;
            visitv[i]=false;
        }
    }

    public static void con(int cnt){
        if(cnt==2){
            permutation(3, 0);
        }
        for(int i=0; i<consonants.size(); i++){
            if(visitc[i]==false && alphabets[consonants.get(i)-'a'] ==false) {
                visitc[i]=true;
                alphabets[consonants.get(i)-'a']=true;
                result.add(consonants.get(i));
                con(cnt+1);
                alphabets[consonants.get(i)-'a']=false;
                result.remove(result.size()-1);
                visitc[i]=false;
            }
        }
    }

    public static void permutation( int cnt,int idx){
        if( result.size()==L) {
            Collections.sort(result);
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i)+" ");
            }
            System.out.println();
            return;
        }
        for(int i=0; i<alls.size(); i++){
            if(alphabets[alls.get(i)-'a']==false ){
                alphabets[alls.get(i)-'a']=true;
                result.add(alls.get(i));
                permutation(cnt+1,idx+1);
                result.remove(result.size()-1);
                alphabets[alls.get(i)-'a']=false;
            }
        }
    }
//    public static void permutation(int v, int c, int cnt,int idx){
//        if( result.size()==L) {
//            Collections.sort(result);
//            for (int i = 0; i < result.size(); i++) {
//                System.out.print(result.get(i)+" ");
//            }
//            System.out.println();
//            return;
//        }
//        if(c<2) {
//            for(int i=c; i<consonants.size(); i++){
//                if(visitc[i]==false ) {
//                    visitc[i]=true;
//                    alphabets[consonants.get(i)-'a']=true;
//                    result.add(consonants.get(i));
//                    permutation(v, c+1, cnt+1,idx);
//                    alphabets[consonants.get(i)-'a']=false;
//                    result.remove(result.size()-1);
//                    visitc[i]=false;
//                }
//            }
//        }
//        for(int i=idx; i<alls.size(); i++){
//            if(alphabets[alls.get(i)-'a']==false ){
//                alphabets[alls.get(i)-'a']=true;
//                result.add(alls.get(i));
//                permutation(v,c,cnt+1,idx+1);
//                result.remove(result.size()-1);
//                alphabets[alls.get(i)-'a']=false;
//            }
//        }
//    }
}

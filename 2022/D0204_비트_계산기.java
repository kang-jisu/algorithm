/*
그리디라고 나와있어서 절댓값 -> 제일 차이 덜나는거로 계속 계산하는건줄알았는데 잘 안됨..
0->N이 아닌 N->0을 만드는 방법을 생각
+2, -2, *2, /2  -> 2진수 비트 이동을 생각
비트마스킹으로
x0 : /2로 00 두개 지움
01 : *2로 10으로 만듦
10 : -2로 00으로 만듦
이걸 반복하면된다.
*/

package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Calculator {

    static long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        ArrayList<String> button = new ArrayList<>();
        if(N == 0 ) System.out.println(0);
        else {

            while(N>0){
                if( N%2 == 1) {
                    N*=2;
                    button.add("[/]"); // 반대로 넣음
                }
                else if( (N/2)%2 == 1){
                        N-=2;
                        button.add("[+]");
                }
                else {
                    N/=2;
                    button.add("[*]");
                }
            }

            if(button.size()>99) System.out.println(-1);
            else {
                System.out.println(button.size());
                for(int i=button.size()-1; i>=0; i-- ){
                    System.out.print(button.get(i)+" ");
                }
                System.out.println("");
            }
        }

    }

}

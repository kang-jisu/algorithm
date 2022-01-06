package studyJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR {

  static class Next {
    int num;
    String step;

    Next(int _num, String _step){
      this.num= _num;
      this.step = _step;
   }
 }
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());

      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());


      int[] visit = new int[10000];

      Queue<Next> queue= new LinkedList<>();
      queue.add(new Next(A,""));
      visit[A]=1;
      while(!queue.isEmpty()) {
        Next tar = queue.poll();

        //D
        int nextNum = tar.num*2%10000;
        if(nextNum==B) {
          System.out.println(tar.step+"D");
          break;
       }

        if(visit[nextNum]==0) {
          visit[nextNum]=1;
          queue.add(new Next(nextNum, tar.step+"D"));
       }

        //S
        nextNum = tar.num ==0 ? 9999 : tar.num-1;
        if(nextNum==B) {
          System.out.println(tar.step+"S");
          break;
       }

        if(visit[nextNum]==0) {
          visit[nextNum] = 1;
          queue.add(new Next(nextNum, tar.step + "S"));
       }
        //L
        nextNum = (tar.num%1000)*10 + tar.num/1000;
        if(nextNum==B) {
          System.out.println(tar.step+"L");
          break;
       }

        if(visit[nextNum]==0) {
          visit[nextNum] = 1;
          queue.add(new Next(nextNum, tar.step + "L"));
       }
        //R
        nextNum = (tar.num%10)*1000 + tar.num/10;
        if(nextNum==B) {
          System.out.println(tar.step+"R");
          break;
       }

        if(visit[nextNum]==0) {
          visit[nextNum] = 1;
          queue.add(new Next(nextNum, tar.step + "R"));
       }
     }

      queue.clear();

   }
 }

}
/*
BFS 계속 그 숫자에서 갈 수 있는 D,S,L,R 방식 탐색하는데
이미 탐색한 숫자에서 계속 반복될 수 있으므로 visit 배열을 두어서 한번도 나오지 않은 숫자에 관해서만 탐색해준다.
visit 배열을 만들지 않았을땐 메모리초과가 났다.
문제를 잘 읽어야하는게 S일때 n-1=0 이 아니고 n=0일때 9999로 만든다
        //L
        nextNum = ((tar.num%1000)/100)*1000 + ((tar.num%100)/10)*100 + (tar.num%10)*10 + tar.num/1000;

       }
        //R
        nextNum = ((tar.num%1000)/100)*10 + ((tar.num%100)/10) + (tar.num%10)*1000 + tar.num/1000*100;
이렇게했었는데
3+1로 한번에 하는 방법이 있어서 수정했다.
*/

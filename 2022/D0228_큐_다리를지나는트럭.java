/**
프로그래머스 코딩테스트연습 큐 - 다리를지나는트럭 LV2
다리를 건널때 0을 추가하며 한칸씩 앞으로 보내고 큐의 사이즈가 length와 같으면 앞에거를 빼는 방법 
https://minhamina.tistory.com/241 블로그참고 
*/
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int curWeight=0;
        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<truck_weights.length; i++){
            
            // 대기 트럭
            int tw = truck_weights[i];
            // 큐에 아무것도 들어있지 않을때 - 처음ㅁ 
            if(q.isEmpty()){
                q.add(tw);
                time++;
                curWeight+=tw;
                continue;
            }
            
            // weight이하가 될 때 까지 0을 넣으며 한칸씩 앞으로 나아감
            while(true){
                // 다리를 다 건넜음 
                if(q.size()== bridge_length) {
                    curWeight-= q.poll();
                }
                // 대기 트럭이 들어갈 수 있는지 확인해서 들어갈 수 있으면 넣음
                if(curWeight+tw<=weight) {
                    q.add(tw);
                    time++;
                    curWeight+=tw;
                    break;
                }
                //들어갈 수 없으면 현재 있는 트럭들을 한칸씩 이동시키기 위해 0을 넣음 
                else {
                    q.add(0);
                    time++;
                }
            }
        }
        // 마지막 트럭이 들어가고 나서 다리 길이만큼 추가해야 됨 
        return time+ bridge_length;
    }
}


/**
다른사람풀이에서 좋은 풀이 
시간은 위에 코드가 더 빠르고 큐를 잘 사용한거같고
아래코드는 뭔가 잘짠거같다
*/
// import java.util.*;
// class Solution {
    
//     class Truck{
//         int weight;
//         int move;
//         public Truck(int weight){
//             this.weight = weight;
//             move=1;
//         }
//         public void moving(){
//             this.move++;
//         }
        
//     }
//     public int solution(int bridge_length, int weight, int[] truck_weights) {
//         int curWeight=0;
//         int time = 0;
//         Queue<Truck> WAIT_Q = new LinkedList<>();
//         Queue<Truck> MOVE_Q = new LinkedList<>();
        
//         for(int t: truck_weights){
//             WAIT_Q.offer(new Truck(t));
//         }
//         while(!WAIT_Q.isEmpty() || !MOVE_Q.isEmpty()){
//             time++;
            
//             if(MOVE_Q.isEmpty()){
//                 Truck t = WAIT_Q.poll();
//                 MOVE_Q.offer(t);
//                 curWeight += t.weight;
//                 continue;
//             }
//             for( Truck t : MOVE_Q){
//                 t.moving();
//             }
//             if(MOVE_Q.peek().move > bridge_length){
//                 Truck t = MOVE_Q.poll();
//                 curWeight-= t.weight;
//             }
//             if(!WAIT_Q.isEmpty() && WAIT_Q.peek().weight+curWeight<=weight) {
//                 Truck t = WAIT_Q.poll();
//                 MOVE_Q.offer(t);
//                 curWeight+=t.weight;
//             }
            
//         }
        
//         return time;
//     }
// }

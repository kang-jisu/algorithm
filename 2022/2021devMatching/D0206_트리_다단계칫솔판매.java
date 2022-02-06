/*
프로그래머스 2021 Dev matching 다단계칫솔판매
저번에 풀었던거라 한번 더 풀어봤는데 오히려 시간이 더 늦게나왔다.
Hahsmap으로 string<->integer변환 한번 해준다음에는 int배열을 이용해서 계속 푸는게 나을거같다 
*/

import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int N = enroll.length;
        int M = seller.length;
        HashMap<String, ArrayList<String> > childs = new HashMap<>();
        HashMap<String, String> parents = new HashMap<>();
        HashMap<String, Integer> fee = new HashMap<>();
        for(int i=0; i<N; i++) {
            childs.put(enroll[i], new ArrayList<>());
            fee.put(enroll[i], 0);
        }
        fee.put("-",0);
        for(int i=0; i<N; i++){
            parents.put(enroll[i], referral[i]);
        }
        
        for(int i=0; i<M; i++) {
            String now = seller[i];
            int cost = amount[i]*100;
            while(true) {
                if(parents.containsKey(now)==false) break; // 루트
                
                int distribute = cost/10;
                int remain;
                if(distribute==0) {
                    // 1원 미만일 경우
                    remain = cost;
                    fee.put(now, fee.get(now)+remain);
                    break;
                }
                else {
                    remain = cost - distribute;
                    fee.put(now, fee.get(now)+remain);
                    cost = distribute;
                    now = parents.get(now);
                }
            }

        }
        int[] answer = new int[N];
        for(int i=0; i<N; i++){
            answer[i] = fee.get(enroll[i]);
        }
        return answer;
    }
}

// ---

// import java.util.HashMap;
// class Solution {
//     public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
//         HashMap<String, Integer> list = new HashMap<>();
//         int[] recommander = new int[enroll.length];
//         int[] answer = new int[enroll.length];

//         for(int i=0; i<enroll.length; i++){
//             list.put(enroll[i], i);
//             answer[i]=0;
//         }

//        for(int i=0; i<referral.length; i++){

//             if(referral[i].equals("-")){
//                 recommander[i]=-1;
//                 continue;
//             }

//             recommander[i]= list.get(referral[i]);
//       }

//         for(int i=0; i<seller.length; i++){

//             int earnMoney = amount[i]*100;
//             int distributeMoney =0;
//             int currentMember = list.get(seller[i]);

//             while(currentMember!=-1){
//                 distributeMoney = earnMoney / 10;
//                 earnMoney = earnMoney - distributeMoney;
//                 answer[currentMember]+= earnMoney;
//                 currentMember = recommander[currentMember];
//                 earnMoney = distributeMoney;
//                 if(earnMoney<1) break;
//             }
//         }


//         return answer;
//     }


// }

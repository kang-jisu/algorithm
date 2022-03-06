/**
프로그래머스 코딩테스트 연습 단어변환
 1. dfs로 글자 하나만 다른 값으로 탐색해서 찾고 여러개 나올 수 있으니 min값으로 구한다. 
 2.배열에서 한글자만 다른 값을 queue에 넣어주면서 결과적으로 target이 나오는지 찾아준다. 
 1이 더 ㅃㅏ름 
*/
class Solution {
    int[] visit;
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        visit = new int[words.length];
        
        dfs(begin, target, words, 0);
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    public void dfs(String begin, String target, String[] words, int cnt ){
        if(begin.equals(target)){
            if(answer>cnt) answer = cnt;
            return;
        }
        for(int i=0; i<words.length; i++){
            if(visit[i]!=0) continue;
            int spellCheck=0;
            for(int j=0; j<begin.length(); j++){
                if(begin.charAt(j)!= words[i].charAt(j)) spellCheck++;
            }
            if(spellCheck==1) {
                visit[i]= 1;
                dfs(words[i], target, words, cnt+1);
                visit[i]=0;
            }
        }
    }
}


// import java.util.*;
// class Solution {
//     class Word {
//         String word;
//         int cnt;
//         Word( String word, int cnt) {
//             this.word = word;
//             this.cnt = cnt;
//         }
//     }
//     public int solution(String begin, String target, String[] words) {
//         Queue<Word> q = new LinkedList<>();
//         int[] visit = new int[words.length];
        
//         boolean fastReturn = true ;
//         for(String word : words) {
//             if(word.equals(target)) {
//                 fastReturn = false;
//                 break;
//             }
//         }
//         if(fastReturn) return 0;
//         q.offer(new Word(begin,0));
//         int answer = 0;
//         while(!q.isEmpty()){
//             Word poll = q.poll();
//             if( poll.word.equals(target) ) {
//             answer = poll.cnt;
//                 break;
//             }
//             for(int i=0; i<words.length; i++){
//                 if( visit[i]==0 && canConvert(poll.word, words[i])){
//                     visit[i]=1;
//                     q.add(new Word(words[i], poll.cnt+1));
//                 }
//             }
//         }
//         return answer;
//     }
//     boolean canConvert(String a, String b){
//         int cnt = 0;
//         for(int i=0; i<a.length(); i++){
//             if(a.charAt(i)!=b.charAt(i)) cnt++;
//         }
//         if(cnt==1) return true;
//         else return false;
//     }
// }

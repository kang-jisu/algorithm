import java.util.*;
class Solution {
    int[] nums;
    int primeCnt=0;
    HashSet<Integer> alreadyChecked= new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        
        nums = new int[numbers.length()];
        for(int i=0; i<numbers.length(); i++){
            nums[i] = numbers.charAt(i)-'0';
        }
        
        for(int i=1; i<=numbers.length(); i++){
            boolean[] visit = new boolean[numbers.length()];
            permute(i, visit);
        }
        return alreadyChecked.size();
    }
    public void permute(int size, boolean[] visit) {
        permutation(size, 0, 0, visit);
    }
    public void permutation(int size, int cnt, int num, boolean[] visit){
        if( size== cnt) {
            if(alreadyChecked.contains(num)) {return;
            }
            if(checkPrime(num)) {
                alreadyChecked.add(num);
                return;
            }
        }
        for(int i=0; i<visit.length; i++){
            if(visit[i]==false) {
                visit[i]=true;
                permutation(size, cnt+1, num*10+nums[i], visit);
                visit[i]=false;
            }
        }
    }
    public boolean checkPrime(int num){
        if(num==0 || num==1) return false;
        for(int i=2; i*i<=num; i++){
            if(num%i==0) return false;
        }
        return true;
    }
}

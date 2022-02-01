/*
카카오 22년 공채 K진수에서 소수 개수 구하기
https://programmers.co.kr/learn/courses/30/lessons/92335
n진법 변환, 소수 구하기 ( i*i<=n ), Long 범위 주의

소수가 될 수 있는 수가 int범위를 넘어가서 Long으로 바꿔줬는데 Long.parseLong을 안해서 런타임에러가 났었다.
주의 ~_~ 
*/

class Solution {
    
    public boolean isPrime(String numStr) {
        long num = Long.parseLong(numStr);
        if(num==1) return false;
        
        boolean result = true;
        for(long i=2; i*i<=num; i++){
            if(num%i==0) {
                result = false;
            }
        }
        return result;
    }
    public int solution(int n, int k) {
        
        // make 10->k
        StringBuilder divideStr = new StringBuilder();
        StringBuilder reverseStr = new StringBuilder();
        int p = n;
        while( p > 1) {
            divideStr.append(p%k);
            p /= k;
        }
        divideStr.append(p);
        for(int i= divideStr.length()-1; i>=0; i--){
            reverseStr.append(divideStr.charAt(i));
        }
        
        String [] nums = reverseStr.toString().split("0");

        int answer = 0;
        for( String num : nums ){
            if(num.length()>0 && isPrime(num)) answer++; 
        }
        return answer;
    }
}

/*
카카오 2021년

메뉴리뉴얼 https://programmers.co.kr/learn/courses/30/lessons/72411

조합
예제3번처럼 XW / WX를 같은걸로 구분하려면 알파벳순으로 문자를 정렬한 후에 처리해주는거 필요함
조합으로 가장많이 나온거(2개이상) 뽑아서 다시 정렬 
*/
import java.util.*;
class Solution {
    class Order implements Comparable<Order>{
        String set;
        int cnt;
        Order(String set, int cnt) {
            this.set=set;
            this.cnt=cnt;
        }
        @Override
        public int compareTo(Order o1) {
            return o1.cnt - this.cnt;
        }
    }
    HashMap<String, Integer> map;
    ArrayList<String> results;
    public String[] solution(String[] orders, int[] course) {
        results= new ArrayList<>();
        for(int i=0; i<course.length; i++) {
            permute(orders, course[i]);
        }
        
        Collections.sort(results);
        String[] answer = new String[results.size()];
        for(int i=0; i<results.size(); i++){
            answer[i] = results.get(i);
        }
        return answer;
    }
    
    public void permute(String[] orders, int len) {
        map = new HashMap<>();
        for(int i=0; i<orders.length; i++){
            int [] visit = new int[orders[i].length()];
            if(orders[i].length() < len) continue;
            for(int j=0; j<visit.length; j++) {
                visit[j]=1;
                dfs( j, 1, len, visit, orders[i]);
                visit[j]=0;
            }
        }
        ArrayList<Order> lists = new ArrayList<>();
        for(Map.Entry<String,Integer> entry: map.entrySet()) {
            lists.add(new Order(entry.getKey(), entry.getValue()));
        }
        Collections.sort(lists);
        int maxcnt =0;
        if(lists.size()>0) maxcnt = lists.get(0).cnt;
        if(maxcnt>=2){
            for(Order order : lists) {
                if(order.cnt==maxcnt) results.add(order.set);
                else break;
            }
        }
        
    }
    
    public void dfs(int start, int r, int len, int[] visit, String order) {
        if(r==len) {
            StringBuilder sb = new StringBuilder();
            char[] orderCharArr = order.toCharArray();
            Arrays.sort(orderCharArr);
            for(int i=0; i<visit.length; i++){
                if(visit[i]==1) {
                    sb.append(orderCharArr[i]);
                }
            }
            if(map.containsKey(sb.toString())) {
                map.put(sb.toString(), map.get(sb.toString())+1);
            }
            else {
                map.put(sb.toString(),1);
            }
            return;
        }
        for(int i=start+1; i<visit.length; i++){
            visit[i]=1;
            dfs(i, r+1, len, visit, order);
            visit[i]=0;
        }
    }
    
    
}

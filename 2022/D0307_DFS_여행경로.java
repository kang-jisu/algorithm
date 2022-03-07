import java.util.*;
class Solution {
    String[][] Tickets;
    boolean[] visit;
    ArrayList<String> list;
    String[] answer = {};
    public String[] solution(String[][] tickets) {
        Tickets = tickets;
        visit = new boolean[tickets.length];
        list = new ArrayList<>();

        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b){
                if(a[0].equals(b[0])){
                    return a[1].compareTo(b[1]);
                }
                else {
                    return a[0].compareTo(b[0]);
                }
            }
        });
    
        list.add("ICN");
        dfs("ICN", 0);
        return answer;
        
    }
    public void dfs(String node, int size) {
        // 이미 answer이 채워졌으면 알파벳 순서가 앞서는 경로일것임 
        if(answer.length>0) return;
        // 모든 도시를 방문했다면 반환
        if(size == visit.length ) {
            answer = new String[list.size()];
            for(int i=0; i<list.size(); i++){
                answer[i] = list.get(i);
            }
            return;
        }
        
        for(int i=0; i<Tickets.length; i++){
            //해당 경로 방문하지 않은 상태일때 
            if(visit[i]==false && Tickets[i][0].equals(node)) {
                // 방문 
                visit[i]=true;
                list.add(Tickets[i][1]);
                dfs(Tickets[i][1], size+1);
                
                //모든 경로 방문하지 못하는 경우일 수 있으니 다른 시작점으로 시작해봐야함.
                //초기화(백트래킹)
                list.remove(list.size()-1);
                visit[i]=false;
            }
        }
    }
}

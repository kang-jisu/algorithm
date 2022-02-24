/**
프로그래머스 코딩테스트 연습 베스트앨범 

*/
import java.util.*;
class Solution {
    
    class Play implements Comparable<Play> { 
        int id;
        int plays;
        
        Play(int id, int plays) { 
        this.id= id;
        this.plays = plays;
        }
        @Override
        public int compareTo(Play o1) {
            if(o1.plays == this.plays) {
                return this.id - o1.id;
            }
            return o1.plays - this.plays;
        }
    }
    class Genre implements Comparable<Genre> {
        int sum=0;
        PriorityQueue<Play> pq = new PriorityQueue<>();

        @Override
        public int compareTo(Genre o1) {
            return o1.sum - this.sum;
        }
        Genre add(Play play) {
            sum+= play.plays;
            pq.add(play);
            return this;
        }
    }
    public int[] solution(String[] genres, int[] plays) {

        HashMap<String, Genre > map = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            map.put( genres[i], map.getOrDefault(genres[i], new Genre()).add(new Play(i, plays[i])));
        }
        
        ArrayList<Genre> list = new ArrayList<>();
        for(String key : map.keySet()) {
            list.add( map.get(key));
        }
        
        Collections.sort(list);

        ArrayList<Integer> result = new ArrayList<>();
        for(Genre genre : list ) {
            int cnt =2;
            while(!genre.pq.isEmpty() && cnt>0) {
                result.add(genre.pq.poll().id);
                cnt--;
            }
        }
        
        int[] answer = result.stream().mapToInt(i->i).toArray();
        return answer;
    }
}

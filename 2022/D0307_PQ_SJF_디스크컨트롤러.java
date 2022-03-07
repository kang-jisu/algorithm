/**
미리 정렬해줘야하는거, 디스크 빈값 등 주석처리한 예외처리를 못해서 엄청 헤맸다.
처음 구현하기전에 미리 상태들 잘 확인하고 짜는수밖에 ㅠ
프로그래머스 코딩테스트연습 힙 디스크컨트롤러 
https://programmers.co.kr/learn/courses/30/lessons/42627#
*/

import java.util.*;

// waitq = 들어온 순으로 , 시간순 정렬
// selectq = 현재 끝난 시간보다 들어온시간이 적은 경우 여기 넣고 소요되는 작업시간순 정렬
class Solution {
    class Disk {
        int start;
        int time;
        Disk(int start, int time) {
            this.start = start;
            this.time = time;
        }
    }
    public int solution(int[][] jobs) {
        Queue<Disk> waitq = new LinkedList<>();
        PriorityQueue<Disk> selectq = new PriorityQueue<>(new Comparator<Disk>(){
        
        @Override
        public int compare(Disk a, Disk b) {
            if(a.time==b.time) {
                return a.start - b.start;
            }
            return a.time - b.time;
        }
        });
        
        // jobs 정렬해줘야함 
        Arrays.sort(jobs, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0]==b[0]) {
                    return a[1]-b[1];
                }
                return a[0]-b[0];
            }
        });
        for(int[] job : jobs){
            waitq.offer(new Disk(job[0],job[1]));
        }
        // 초기값 넣어줌
        int curTime=0;
        int timeSum=0;
        while(!waitq.isEmpty()) {
            if(waitq.peek().start==curTime) selectq.add(waitq.poll());
            else break;
        }
        while(!waitq.isEmpty() || !selectq.isEmpty()) {
            // select q에서 꺼내서 curTime 늘리고 timeSum더해줌 
                if(!selectq.isEmpty()){
                Disk poll = selectq.poll();
                curTime += poll.time;
                timeSum += curTime-poll.start;
            }
            
            // 하나 처리할때마다 계속 다시 갱신해줘야함 
            while(!waitq.isEmpty() && waitq.peek().start<=curTime) {
                selectq.add(waitq.poll());
            }

            // 작업수행하고 있지 않은 상태 있을 수 있으니 처리 
            if(selectq.isEmpty() && !waitq.isEmpty()){
                curTime = waitq.peek().start;
                selectq.add(waitq.poll());
            }
        }
        int answer = timeSum/jobs.length;
        return answer;
    }

}

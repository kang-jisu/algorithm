import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RailRoad {

    static int N;
    static int D;
    static class Pos {
        int home;
        int office;

        Pos(int home, int office) {
            this.home= home;
            this.office = office;
        }
    }
    static ArrayList<Pos> list;
    static PriorityQueue<Pos> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        pq = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                if( o1.home == o2.home) {
                    return o1.office - o2.office;
                }
                else return o1.home - o2.home;
            }
        });
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(A>B) list.add(new Pos(B,A));
            else list.add(new Pos(A,B));
        }
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());

        Collections.sort(list, new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                if(o2.office== o1.office) {
                    return o1.home - o2.home;
                }
                else return o1.office - o2.office;
            }
        });
        int max = 0;
        for(Pos pos : list) {
            pq.add(pos);
            while( !pq.isEmpty()) {
                Pos temp = pq.peek();
                if(pos.office - temp.home >D) {
                     pq.poll();
                }
                if(pos.office - temp.home <=D) {
                    break;
                }
            }
            if( pq.size() > max) max = pq.size();
        }
        System.out.println(max);
    }
}
/*
문제에 접근하는 방법

https://chanhuiseok.github.io/posts/baek-28/ 이 블로그 엄청 잘 나와있음 그림 

길이가 L로 고정된 상태에서, 답이 정렬된 배열에서 O(N)만에 나오도록 하는법

라인스위핑

- 정렬된 어떤 자료에서 시간 복잡도를 줄일 수 있도록 선을 한번만 훑으면서 최종결과를 찾는 방식
- **우선순위 큐**가 자주 사용된다.

정수 쌍을 도착지점 오름차순으로 정렬한 뒤, 도착지점이 같을 경우 출발지점의 오름차순으로 정렬한다. 

정렬한 상태로 하나씩 읽어가면서, 우선순위 큐에 넣고 큐에 넣은 다음 지금 들어있는 값들 중 L보다 작은 범위로 있는 값만 남도록 나머지를 pop해준다. 그리고 그때의 pq 사이즈를 계산한다.



카카오 광고삽입은 L이 배열로 선언할 수 있는 정도여서 모든 값을 넣어주고 배열을 슬라이딩 윈도우로 탐색해가면서 풀었었는데, 이거는 총 숫자 범위가 2억이라서 그렇게는 안되고

우선순위큐로 N만에 읽으면서 해결해주었다.

문제는 이런 접근방식을 모르면 절대 못풀 거같은데 알고나면 생각보다 이해가 금방돼서 빨리풀었다.



그리고 주의할 점은 집-회사, 회사-집 어떤경우도 될 수 있으니 A보다 B가 크도록 일관성있게 입력을 받아주어야한다.

*/

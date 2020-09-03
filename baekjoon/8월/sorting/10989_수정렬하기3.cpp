#include <iostream>
#include <algorithm>
using namespace std;

/*
* 2750,2571 수 정렬하기 들은 정렬 파일들이 답
* 10989 수 정렬하기 3
* 첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 숫자가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.
* 10000라는 조건을 사용해서 그 안에서 Count하면서 입력받은 후에 개수만큼 작은 인덱스부터 출력해주면 됨
* 시간제한은 3초인데 메모리제한이 8mb!
*/
int arr[10001];
int main(){
    std::ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N;
    cin >> N;
    
    for(int i = 0 ; i < N ; i++ ){
        int tmp;
        cin>>tmp;
        arr[tmp]++;
    }
    for(int i=0; i<=10000; i++){
        for(int j=0; j<arr[i]; j++){
            cout<<i<<"\n";
        }
    }

    return 0;
}
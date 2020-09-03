#include <iostream>
#include <algorithm>
using namespace std; 

/*
백준 11650,11651 좌표 정렬하기
x좌표가 같으면 y좌표 증가하는순으로 ( 11650 )
or y좌표가 같으면 x좌표가 증가하는순으로 ( 11651 )
*/

struct location{
    int x;
    int y;
};
bool sortLocation(location a, location b){
    if(a.x==b.x){
        return a.y<b.y;
    }else return a.x<b.x;
}
location arr[100000];
int main(){
    int N;
    cin>>N;
    for(int i=0; i<N; i++){
        location tmp;
        cin>>tmp.x>>tmp.y;
        arr[i]=tmp;
    }
    sort(arr,arr+N,sortLocation);
    for(int i=0; i<N; i++){
        cout<<arr[i].x<<" "<<arr[i].y<<"\n";
    }
    return 0;
}
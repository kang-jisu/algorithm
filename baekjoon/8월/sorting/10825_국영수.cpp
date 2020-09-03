#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

// 점수순 정렬. 국어점수 감소, 영어점수 증가, 수학점수 감소, 이름 사전순
struct student{
    string name;
    int kor;
    int math;
    int eng;
};
bool customSort(student a, student b){
    if(a.kor==b.kor){
        if(a.eng==b.eng){
            if(a.math==b.math){
                return a.name<b.name;
            }
            return a.math>b.math;
        }
        return a.eng<b.eng;
    }
    return a.kor>b.kor;
}
student arr[100001];
int main(){
    int n;
    cin >> n;
    for(int i=0; i<n; i++){
        cin>>arr[i].name>>arr[i].kor>>arr[i].eng>>arr[i].math;
    }
    sort(arr,arr+n, customSort);
    for(int i=0; i<n; i++){
        cout<<arr[i].name<<"\n";
    }
    return 0;

}
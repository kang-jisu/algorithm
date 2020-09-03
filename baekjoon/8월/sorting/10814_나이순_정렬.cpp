#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

// 나이순 정렬,나이가 같을 땐 가입한 순으로 
struct member{
    int age;
    string name;
    int idx;
};
bool customSort(member a, member b){
    if(a.age==b.age){
        return a.idx<b.idx;
    }
    return a.age<b.age;
}
member arr[100001];
int main(){
    int n;
    cin >> n;
    for(int i=0; i<n; i++){
        cin>>arr[i].age>>arr[i].name;
        arr[i].idx=i;
    }
    sort(arr,arr+n, customSort);
    for(int i=0; i<n; i++){
        cout<<arr[i].age<< " "<<arr[i].name<<"\n";    }
    return 0;

}
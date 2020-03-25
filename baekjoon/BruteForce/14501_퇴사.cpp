#include <iostream>
using namespace std;

int task[16][2]; //task[t][p] 1<=t<=5 , 1<=p<=1000
int n; //1<=n<=15
int m=0;

//재귀함수 이용
void cal(int start, int val){
    for(int i=start+task[start][0]; i<=n; i++){
            cal(i,val+task[i][1]);
    }
    if(start+task[start][0]-1<=n && m<val)m=val;
}
int main(){

    cin >>n;
    for(int i=1; i<=n; i++){
        cin>>task[i][0]>>task[i][1];
    }

    for(int i=1; i<=n; i++){
        cal(i,task[i][1]);
    }

    cout<<m<<"\n";
    return 0;
}
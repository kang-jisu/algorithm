#include <iostream>
#include <algorithm>
using namespace std;

int d[1000000];
int main(){
    int n;
    cin>>n;

    d[1]=0;
    d[2]=1;
    d[3]=1;
    if(n<=3)cout<<d[n]<<"\n";
    else {
        for(int i=4; i<=n; i++){
            int minVal = d[i-1]+1;
            if(i%3==0)minVal=min(minVal,d[i/3]+1);
            if(i%2==0)minVal=min(minVal,d[i/2]+1);
            d[i]=minVal;
        }
        cout<<d[n]<<"\n";
    }


    return 0;
}
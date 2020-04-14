#include <iostream>
using namespace std;

long dp[101]={0,1,1,1,2,2,3,4,5,7,9};
int main(){
    int T;
    cin>>T;

    for(int i=11; i<=100; i++){
        dp[i]=dp[i-1]+dp[i-5];
    }
    for(int t=0; t<T; t++){
        int N;
        cin>>N;
        cout<<dp[N]<<"\n";
    }

    return 0;

}
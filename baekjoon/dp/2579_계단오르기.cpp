#include <iostream>
#include <algorithm>
using namespace std;

long stair[302];
long dp[302];
int main(){

    int n;
    cin>>n; 
    for(int i=0; i<n; i++)cin>>stair[i];

    dp[0]=stair[0];
    for(int i=1; i<n; i++){
        dp[i]= max(dp[i-2]+stair[i],dp[i-3]+stair[i-1]+stair[i]);
    }
    cout<<dp[n-1]<<"\n";
    return 0;
}

#include <iostream>
#include <cmath>
using namespace std;

int dp[109000];
int main(){
    long long n;
    cin>>n;

    dp[1]=1;
    dp[2]=2;
    
    for(int i=1; i<=330; i++){
        dp[i*i]=1;
    }

    for(int i=3; i<=n; i++){
        if(dp[i]==1)continue;
        else {
            int min=100000;
            for(int j = sqrt(i); j>0; j-- ){
                if(min>1+dp[i-j*j])min=1+dp[i-j*j];
            }
            dp[i]=min;
        }
    }
    cout<<dp[n]<<"\n";

    return 0;
}

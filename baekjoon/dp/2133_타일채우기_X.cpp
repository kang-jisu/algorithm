#include <iostream>
#include <cmath>
using namespace std;

long dp[31];
int main(){

    int n;
    cin>>n;
    dp[0]=1;
    dp[2]=3;
    for(int i=4; i<=n; i+=2){
        dp[i]=dp[i-2]*3;
		for(int j=i; j>=4; j-=2){
			dp[i]+=dp[i-j]*2;
		}
    }
    
    cout<<dp[n]<<"\n";
    return 0;
}

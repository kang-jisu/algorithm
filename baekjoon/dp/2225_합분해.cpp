#include <iostream>
using namespace std;

long long dp[201][201];
int main(){
    int N,K;
    cin>>N>>K;
    dp[0][0]=1;

    for(int j=1; j<=K; j++){
         for(int i=0; i<=N; i++){
            for(int k=0; k<=i; k++){
                dp[i][j]+=dp[i-k][j-1];
                dp[i][j]%=1000000000;
            }
        }
    }
    cout<<dp[N][K]%1000000000<<"\n";
    return 0;
}
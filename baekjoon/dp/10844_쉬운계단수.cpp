#include <iostream>
using namespace std;

long long dp[101][10];
int main(){

    for(int i=1; i<10; i++){
        dp[1][i]=1;
    }
    int N;
    cin>>N;

    for(int i=2; i<=N; i++){
        for(int j=0; j<10; j++){
            if(j==0)dp[i][j]=dp[i-1][1];
            else if(j==9)dp[i][j]=dp[i-1][8];
            else dp[i][j]= (dp[i-1][j-1]+dp[i-1][j+1])%1000000000;
        }
    }
    long long sum = 0;
    for(int i=0; i<10; i++){
        sum+=dp[N][i]% 1000000000;
    }
    cout<<sum%1000000000<<"\n";
      
    
    return 0;
}

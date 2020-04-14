#include <iostream>
using namespace std;

int dp[1001];
int p[1001];
int main(){

    int N;
    cin >>N;

    for(int i=1; i<=N; i++)cin>>p[i];

    for(int i=1; i<=N; i++){
        int max = p[i];
        for(int j=i-1; j>=i/2; j--){
            if(max<dp[j]+dp[i-j]){max=dp[j]+dp[i-j];
            }
            
        }
        dp[i]=max;
    }

    cout<<dp[N]<<"\n";



    return 0;
}



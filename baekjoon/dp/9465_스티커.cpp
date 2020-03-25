#include <iostream>
#include <algorithm>
using namespace std;

int arr[2][100001];
int dp[2][100001];
int main(){
    

std::ios::sync_with_stdio(false);
cin.tie(NULL);
    int T;
    cin>>T;
    for(int t=0; t<T; t++){
        int n;
        cin>>n;

        for(int i=0; i<2; i++){
            for(int j=0; j<n; j++){
                cin>>arr[i][j];
                dp[i][j]=0;
            }
        }
        dp[0][0]=arr[0][0];
        dp[1][0]=arr[1][0];
        if(n>1){
        dp[0][1]=arr[1][0]+arr[0][1];
        dp[1][1]=arr[0][0]+arr[1][1];

        for(int i=2; i<n; i++){
            dp[0][i]=max(dp[0][i-2]+arr[0][i],dp[1][i-2]+arr[0][i]);
            dp[0][i]=max(dp[0][i],dp[1][i-1]+arr[0][i]);

            dp[1][i]=max(dp[0][i-2]+arr[1][i],dp[1][i-2]+arr[1][i]);
            dp[1][i]=max(dp[1][i],dp[0][i-1]+arr[1][i]);
        }
        }

        cout<<max(dp[0][n-1],dp[1][n-1])<<"\n";
        
    }
    return 0;
}
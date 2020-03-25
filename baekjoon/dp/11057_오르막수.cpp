#include <iostream>
using namespace std;

int dp[1000][10];
int main(){

    int N;
    cin>>N;

    for(int i=0; i<10; i++){
        dp[1][i]=1;
    }
    for(int i=2; i<=N; i++){
        for(int j=0; j<10; j++){
            for(int k=0; k<=j; k++){
                dp[i][j]+=dp[i-1][k];
            }
            // dp[i][j] 는 바로이전 (dp[i][j-1]+dp[i-1][j] 또는 dp[i-1][k]의 합)
            // 이전 경우에서 계속 가지쳐서 경우가 나오니깐 
            dp[i][j]%=10007;
        }
    }
    int sum=0;
    for(int i=0; i<10; i++){
        sum+=dp[N][i];
    }
    cout<<sum%10007<<"\n";
/*    1 2 3
dp[0] 1 1 1       
dp[1] 1 2 1+2
dp[2] 1 3 1+2+3
dp[3] 1 4 1+2+3+4
dp[4] 1 5
dp[5] 1 6
dp[6] 1 7
dp[7] 1 8
dp[8] 1 9
dp[9] 1 10
*/
    
    return 0;
}

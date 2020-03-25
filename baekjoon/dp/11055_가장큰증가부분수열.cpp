#include <iostream>
using namespace std;

int sec[1001];
int dp[1001];
int main(){
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    int N;
    cin>>N;

    for(int i=0; i<N; i++){
        cin>>sec[i];
    }
    dp[0]=sec[0];
    for(int i=1; i<N; i++){
        int max = sec[i];
        for(int j=0; j<i; j++){
            if(sec[j]<sec[i] &&max<dp[j]+sec[i]){
                max=dp[j]+sec[i];
            }
        }
        dp[i]=max;
    }
    int max = 0;
    for(int i=0; i<N; i++){
        if(max<dp[i])max=dp[i];
    }
    cout<<max<<"\n";
    return 0;
}
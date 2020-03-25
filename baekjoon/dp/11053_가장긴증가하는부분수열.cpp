#include <iostream>
using namespace std;

int sec[1001];
int dp[1001];
int main(){
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    int N;
    cin>>N;

    dp[0]=1;
    for(int i=0; i<N; i++){
        cin>>sec[i];

        if(i>0){
        int max = 1;
        for(int j=0; j<i; j++){
            if(sec[j]<sec[i] &&max<=dp[j]){
                max=dp[j]+1;
            }
        }
        dp[i]=max;
        }
    }
    int max = 0;
    for(int i=0; i<N; i++){
        if(max<dp[i])max=dp[i];
    }
    cout<<max<<"\n";
    return 0;
}
#include <iostream>
#include <algorithm>
using namespace std;

int glass[10001];
int dp[10001];
int main(){

    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin>>n;
    for(int i=0; i<n; i++){
        cin>>glass[i];
    }
    dp[0]=glass[0];
    if(n>1){
        dp[1]=glass[0]+glass[1];
    }
    if(n>2){
        dp[2]=max(max(glass[1]+glass[2],glass[0]+glass[2]),glass[0]+glass[1]);
    }
    if(n>3){
    for(int i=3; i<n; i++){
        dp[i]= max(max(dp[i-3]+glass[i-1]+glass[i],dp[i-2]+glass[i]),dp[i-1]);
    }
    }
    

    cout<<dp[n-1]<<"\n";

    return 0;                                

}
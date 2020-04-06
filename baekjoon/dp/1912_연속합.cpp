#include <iostream>
#include <algorithm>
using namespace std;

long arr[100001];
long dp[100001];
int main(){
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin>>n;

    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    dp[0]=arr[0];
    for(int i=1; i<n; i++){
        dp[i] = max(dp[i-1]+arr[i],arr[i]);
    }
    long max = -2147483648;
    for(int i=0; i<n; i++){
        if(dp[i]>max)max=dp[i];
    }
    cout<<max<<"\n";
    return 0;
}

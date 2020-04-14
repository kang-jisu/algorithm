#include <iostream>
#include <string.h>
using namespace std;

char arr[5001];
long long dp[5001];
int main(){

    cin>>arr;
    
    dp[0]=1;
    if(arr[0]=='0')cout<<0<<"\n";
    else {
    for(int i=1; i<strlen(arr); i++){
        if(arr[i]!='0')dp[i]=dp[i-1];
        if(arr[i-1]=='1'|| (arr[i-1]=='2'&&arr[i]<='6')){
            if(i>1)dp[i]+=dp[i-2];
            else dp[i]++;
        }
        dp[i]%=1000000;
    }
    cout<<dp[strlen(arr)-1]<<"\n";
    }

    return 0;
}
#include <iostream>
using namespace std;

int gcd(int a, int b){
    if(b==0) return a;
    else return gcd(b,a%b);
}
int main(){

    int arr[101];
    
    int t;
    cin>>t;
    for(int T=0; T<t; T++){
        int n;
        cin>>n;
        for(int i=0; i<n; i++){
            cin>>arr[i];
        }
        long long sum = 0 ;
        if(n==1) sum=arr[0];
        else {
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++ ){
                sum+=gcd(arr[i],arr[j]);
            }
        }
        }
        cout<<sum<<"\n";
        
    }
    return 0;
}
#include <iostream>
#include <string>
using namespace std;

int main(){

    long long n;
    int b;
    cin>>n>>b;
    string s ="";

    while(n>=b){
        int tmp = n%b;
        n=n/b;
        if(tmp<10)s+=to_string(tmp);
        else s+=(char)(tmp+55);

    }
     if(n<10)s+=to_string(n);
        else s+=(char)(n+55);
    for(int i=s.length()-1; i>=0; i--){
        cout<<s[i];
    }cout<<"\n";
    return 0;
}

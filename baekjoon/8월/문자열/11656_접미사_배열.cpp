#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

bool comp(string a , string b){
    return a<b;
}

int main(){

    string pre[1001];
    string word;
    cin>>word;
    for(int i=0; i<word.length(); i++){
        pre[i]=word.substr(i,word.length()-i);
    }
    sort(pre,pre+word.length(),comp);
    for(int i=0; i<word.length(); i++){
        cout<<pre[i]<<"\n";
    }
 
    return 0;
}
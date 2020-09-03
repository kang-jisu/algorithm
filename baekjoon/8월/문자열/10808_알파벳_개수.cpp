
#include <iostream>
#include <string>
using namespace std;

int main()
{
    string word;
    cin>>word;
    int arr[26]={0,};
    for(int i=0; i<word.length(); i++){
        arr[(int)(word[i]-'a')]++;
    }
    for(int i=0; i<26; i++){
        cout<<arr[i]<<" ";
    }cout<<"\n";
    return 0;
}
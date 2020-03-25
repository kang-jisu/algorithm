#include <iostream>
using namespace std;

int main(){
    int n; //1<=n<=1000
    cin>>n;

    int count=0;
    if(n<100)count=n;
    else {
        count=99;
    for(int i=100; i<=n; i++){
        int a = i/100;
        int b = (i%100)/10;
        int c = (i%10);
        if(a-b == b-c) count++;
    }
    }

    cout<<count<<"\n";

    return 0;
}

/*
처음 푼 코드
#include <iostream>
#include <string>
using namespace std;

int main(){
    int n; //1<=n<=1000
    cin>>n;

    int count=0;
    for(int i=1; i<=n; i++){
        if(i<100){
            count++;
            continue;
        }
        string temp = to_string(i);
        int diff=temp[0]-temp[1];
        for(int j=2; j<temp.length();j++){
            if(temp[j-1]-temp[j]==diff)count++;
        }
    }

    cout<<count<<"\n";

    return 0;
}
*/
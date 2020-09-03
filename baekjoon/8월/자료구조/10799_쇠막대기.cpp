#include <iostream>
#include <string>
using namespace std;

int main(){
    string stick;
    cin>>stick;

    int sum=0;
    int num=0;
    if(stick[0]=='(')num++;
    for(int i=1; i<stick.length(); i++){
        if(stick[i]=='(')num++;
        else{
            if(stick[i-1]=='('){
            num--;
            sum+=num;
            }
            else {
                num--;
                sum++;
            }
        }
    }
    cout<<sum<<"\n";
    return 0;
    
}


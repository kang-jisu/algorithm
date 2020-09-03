#include <iostream>
using namespace std;

int main(){
    int n;
    cin>>n;
    int two=0;
    int five=0;
    for(int i=1; i<=n; i++){
        int tmp=i;
    while(1){
        if(tmp%2==0){
            two++;
            tmp/=2;
        }
        else break;
    }
        while(1){
        if(tmp%5==0){
            five++;
            tmp/=5;
        }
        else break;
    }
    }
    if(two>five)cout<<five<<"\n";
    else cout<<two<<"\n";
    return 0;
}
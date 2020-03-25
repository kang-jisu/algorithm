#include <iostream>
using namespace std;

int main(){
    int N;
    cin>>N;

    int max = -1000001;
    int min =  1000001;

    int a;

    for(int i=0; i<N; i++){
        cin>>a;
        if(a>max) max=a;
        if(a<min) min=a;
    }
    cout<<min<<" "<<max<<"\n";


    return 0;
}
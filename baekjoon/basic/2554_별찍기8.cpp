#include <iostream>
using namespace std;

int main(){

    int N;
    cin>>N;

    // for(int i=1;i<N; i++){
    //     for(int j=0; j<i; j++)cout<<"*";
    //     for(int j=0; j<(N-i)*2; j++)cout<<" ";
    //     for(int j=0; j<i; j++)cout<<"*";
    //     cout<<"\n";
    // }
    // for(int i=N;i>0; i--){
    //     for(int j=0; j<i; j++)cout<<"*";
    //     for(int j=0; j<(N-i)*2; j++)cout<<" ";
    //     for(int j=0; j<i; j++)cout<<"*";
    //     cout<<"\n";
    // }    

    //별찍기 12

    // for(int i=1; i<=N; i++){
    //     for(int j=0; j<N-i; j++)cout<<" ";
    //     for(int j=0; j<i; j++)cout<<"*";
    //     cout<<"\n";
    // }
    // for(int i=N-1; i>0; i--){
    //     for(int j=0; j<N-i; j++)cout<<" ";
    //     for(int j=0; j<i; j++)cout<<"*";
    //     cout<<"\n";
    // }

    //별찍기9
    // for(int i=N; i>0; i--){
    //     for(int j=0; j<N-i; j++)cout<<" ";
    //     for(int j=0; j<i*2-1; j++)cout<<"*";
    //     cout<<"\n";
    // }
    // for(int i=2; i<=N; i++){
    //     for(int j=0; j<N-i; j++)cout<<" ";
    //     for(int j=0; j<i*2-1; j++)cout<<"*";
    //     cout<<"\n";
    // }
    // return 0;

    //별찍기 16
    if(N==1){
        cout<<"*\n";
    }
    else {
    for(int j=0; j<N-1; j++)cout<<" ";
    for(int j=0; j<1; j++)cout<<"*\n";
    for(int i=2; i<=N-1; i++){
        for(int j=0; j<N-i; j++)cout<<" ";
        cout<<"*";
        for(int j=0; j<2*(i-1)-1; j++)cout<<" ";
        cout<<"*\n";
    }
    for(int i=0; i<2*N-1; i++)cout<<"*";
    cout<<"\n";
    }

}
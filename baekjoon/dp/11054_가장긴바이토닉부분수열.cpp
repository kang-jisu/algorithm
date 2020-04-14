#include <iostream>
using namespace std;

int ldp[1001];
int sdp[1001];
int arr[1001];
int main(){


    int N;
    cin>>N;

    for(int i=0; i<N; i++){
        cin>>arr[i];
    }

    ldp[0]=1;
    sdp[0]=1;
    for(int i=1; i<N; i++){
        int max =1;
        for(int j=0; j<i; j++){
            if(arr[j]<arr[i]&&ldp[j]+1>max)max=ldp[j]+1;
        }
        ldp[i]=max;
    }

    for(int i=N-1; i>=0; i--){
        int max =1;
        for(int j=N-1; j>i; j--){
            if(arr[j]<arr[i]&&sdp[j]+1>max)max=sdp[j]+1;
        }
        sdp[i]=max;
    }
    int max = 0;
    for(int i=0; i <N; i++){
        if(ldp[i]+sdp[i]-1>max)max=ldp[i]+sdp[i]-1;
    }
    cout<<max<<"\n";
    return 0;
}
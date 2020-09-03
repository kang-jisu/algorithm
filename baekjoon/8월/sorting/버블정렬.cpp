#include <iostream>
using namespace std;

void bubbleSort(int *arr, int n){
    for(int i=0; i<n-1; i++){
        for(int j=1; j<n-i; j++){
            if(arr[j]<arr[j-1]){
                int tmp = arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=tmp;
            }
        }
    }
}

int arr[1000000];
int main(){
    int n;
    cin>> n;
    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    bubbleSort(arr, n);
    for(int i=0; i<n; i++){
        cout<<arr[i]<<"\n";
    }
    return 0;
}
#include <iostream>
using namespace std;

void insertionSort(int *arr, int n){
    for(int i=1; i<n; i++){
        int cur = arr[i];
        int j = i-1;
        while(j>=0 && arr[j]>cur){
            int tmp = arr[j+1];
            arr[j+1] = arr[j];  
            arr[j] = tmp;
            j--;
        }
        arr[j+1] = cur;
    }
}

int arr[1000000];
int main(){
    int n;
    cin>> n;
    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    insertionSort(arr, n);
    for(int i=0; i<n; i++){
        cout<<arr[i]<<"\n";
    }
    return 0;
}
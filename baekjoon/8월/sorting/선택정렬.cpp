#include <iostream>
using namespace std;

void selectionSort(int *arr, int n){
    int curIdx = 0;
    int minIdx = 0;
    for(int i=0; i<n-1; i++){
    int min = 1000001;
        minIdx=i;
        for(int j=curIdx; j<n; j++){
            if(min>arr[j]){
                min=arr[j];
                minIdx = j;
            }
        }
        //swap
        int tmp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = tmp;
        curIdx++;
    }
}

int arr[1000000];
int main(){
    int n;
    cin>> n;
    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    selectionSort(arr, n);
    for(int i=0; i<n; i++){
        cout<<arr[i]<<"\n";
    }
    return 0;
}
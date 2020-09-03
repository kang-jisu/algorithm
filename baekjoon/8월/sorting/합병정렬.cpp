#include <iostream>
using namespace std; 


int arr[1000000];
int arr2[1000000];
void merge(int left , int right){
    int mid = (left+right)/2;
    int i = left;
    int j = mid +1;
    int k = left;
    while(i<=mid && j <=right){
        if(arr[i]<=arr[j]){
            arr2[k++]=arr[i++];
        }
        else{
            arr2[k++]=arr[j++];
        }
    }
    while(i<=mid){
        arr2[k++]=arr[i++];
    }
    while(j<=right){
        arr2[k++]=arr[j++];
    }
    for(int x=left; x<=right; x++){
        arr[x] = arr2[x];
    }
}
void mergeSort(int left, int right){
    
    if(left<right){
        int mid = (left+right)/2;
        mergeSort(left,mid);
        mergeSort(mid+1,right);
        merge(left,right);
    }
}

int main(){
    int n;
    cin>> n;
    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    mergeSort(0,n-1);
    for(int i=0; i<n; i++){
        cout<<arr[i]<<"\n";
    }
    return 0;
}


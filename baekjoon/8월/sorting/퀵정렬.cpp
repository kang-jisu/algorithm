#include <iostream>
using namespace std;
int arr[1000000];
void quickSort(int s , int e){
    if(s>=e)return;
    int mid = (s+e)/2;
    int pivot = arr[mid];
    int i = s;
    int j = e;
    while(i<=j){
        while(pivot<arr[j])j--;
        while(pivot>arr[i])i++;
        if(i<=j){
            int tmp = arr[i];
            arr[i]= arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
    quickSort(i,e);
    quickSort(s,j);

}
int main(){

    int n;
    cin>>n;
    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    quickSort(0,n-1);
    for(int i=0; i<n; i++){
        cout<<arr[i]<<"\n";
    }
    return 0;
}

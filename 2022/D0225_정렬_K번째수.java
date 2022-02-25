/*
프로그래머스 코딩테스트 연습 - 정렬 LV1
merge sort, insertion sort 
*/
class Solution {
    
    public void merge(int[] arr, int left, int right){
        int[] tmp = new int[arr.length];
        if(left<right){
            int mid = (left+right)/2;
            int i = left;
            int j = mid+1;
            int k = left;
            while(i<=mid && j<=right) {
                if(arr[i]>arr[j]){
                    tmp[k++]=arr[j++];
                }
                else tmp[k++]=arr[i++];
            }
            while(i<=mid){
                tmp[k++]=arr[i++];
            }
            while(j<=right){
                tmp[k++]=arr[j++];
            }
            for(int idx=left;idx<=right; idx++){
                arr[idx] = tmp[idx];
            }
        }
    }
    public void mergesort(int[] arr, int left, int right){
        if(left<right){
            int mid = (left+right)/2;
            mergesort(arr, left, mid);
            mergesort(arr, mid+1, right);
            merge(arr, left, right);
        }
    }
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        for(int c=0; c<commands.length; c++) {
            
            int[] command = commands[c];
            
            int len = command[1]-command[0]+1;
            int[] newArray = new int[len];
            for(int i=0; i<len; i++){
                newArray[i]=array[command[0]+i-1];
                
                // 삽입정렬 
                // for(int j=i-1; j>=0; j--){
                //     if(newArray[j]> newArray[j+1]){
                //         int tmp = newArray[j];
                //         newArray[j]=newArray[j+1];
                //         newArray[j+1] = tmp;
                //     }
                // }
            }
            mergesort(newArray, 0,len-1);
            
            answer[c] = newArray[command[2]-1];
        }
        return answer;
    }
}

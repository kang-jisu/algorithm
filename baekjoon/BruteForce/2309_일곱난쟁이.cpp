#include <iostream>
#include <algorithm>
using namespace std;

//조합으로 해서 아홉난쟁이의 합에서 두 난쟁이를 뺐을때 100이나온경우 추출
//밑에 코드는 두 난쟁이를 제외한 합이 100이나오는 경우 (먼저풀었다가 위에코드로 수정함)

int height[9];
int x,y;
int main(){

    //9개의 난쟁이 중 일곱 난쟁이의 키의 합이 100인 경우 찾기
    int max=0;
    for(int i=0; i<9; i++){
        cin>>height[i];  //난쟁이 키 입력 (1~100자연수, 모두 다른 수)
        max+=height[i];
    }

    sort(height,height+9);

     for(int i=0; i<9; i++){
        for(int j=i+1; j<9; j++){
            
            if(max-height[i]-height[j]==100){
                x=i;y=j;
                break;
            }
        }
    }
    for(int i=0; i<9; i++){
        if(i==x||i==y)continue;
        else cout<<height[i]<<"\n";
    }


    return 0;

}

// #include <iostream>
// #include <algorithm>
// using namespace std;

// int height[9];
// int x,y;
// int main(){

//     //9개의 난쟁이 중 일곱 난쟁이의 키의 합이 100인 경우 찾기

//     for(int i=0; i<9; i++){
//         cin>>height[i];  //난쟁이 키 입력 (1~100자연수, 모두 다른 수)
//     }

//     sort(height,height+9); 

//     for(int i=0; i<9; i++){
//         for(int j=i+1; j<9; j++){
//             int count = 0;
//             for(int k=0; k<9; k++){
//                 if(i==k||j==k)continue;
//                 else count+=height[k];
//             }
//             if(count==100){
//                 x=i; y=j;
//                 break;
//             }
//         }
//     }
//     for(int i=0; i<9; i++){
//         if(i==x||i==y)continue;
//         else cout<<height[i]<<"\n";
//     }


//     return 0;

// }z
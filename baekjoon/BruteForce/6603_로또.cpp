#include <iostream>
#include <algorithm>
using namespace std;

int S[13];
int tmp[13];
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int k;
    while(cin>>k){
        if(k==0)break; //6<k<13;
        for(int i=0; i<k; i++){
            cin>>S[i];
            if(i<6) tmp[i]=0;
            else tmp[i]=1;
        }
        do{
            for(int i=0; i<k; i++){
                if(tmp[i]==0)cout<<S[i]<<" ";
            }
            cout<<"\n";
        }
        while(next_permutation(tmp,tmp+k));

        cout<<"\n";
            
        
    }

    return 0;

}
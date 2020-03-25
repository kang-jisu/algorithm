#include <iostream>
using namespace std;

int main(){

    int pay; //타로가 지불할 돈 (1<=pay<1000)
    int list[6]={500,100,50,10,5,1};
    cin >> pay;
    pay= 1000-pay;
    //거스름돈 500,100,50,10,5,1
    int charge=0;
    for(int i=0; i<6; i++){
        if(pay>=list[i]){
            charge+= pay/list[i];
            pay=pay%list[i];

        }
    }
    cout<<charge<<"\n";

    return 0;
}
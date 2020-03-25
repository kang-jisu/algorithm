#include <iostream>
using namespace std;

int main(){
    
    int x, y;
    cin>>x>>y;
    int sum=0;
    for(int i=1; i<=x; i++){
        if(i==x)sum+=y;
        else {
        if(i==2) sum+=28;
        else if(i==4||i==6||i==9||i==11)sum+=30;
        else sum+=31;
        }
    }
    sum=sum%7;
    switch(sum){
        case 0:
            cout<<"SUN\n";
            break;
        case 1:
            cout<<"MON\n";
            break;
        case 2:
            cout<<"TUE\n";
            break;
        case 3:
            cout<<"WED\n";
            break;
        case 4:
            cout<<"THU\n";
            break;
        case 5:
            cout<<"FRI\n";
            break;
        case 6:
            cout<<"SAT\n";
            break;

    }

    return 0;
}
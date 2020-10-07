#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int pow(int a, int p){
    int result = 1;
    for(int i=0; i<p; i++){
        result*=a;
    }
    return result;
}
int main()
{
    int a, p;
    cin >> a >> p;
    int v[10000];
    v[0]=a;

    int idx = 0;
    while (1)
    {
        int num = 0;
        int tmp = v[idx++];
        while (tmp > 0)
        {
            num += pow(tmp % 10, p);
            tmp /= 10;
        }
        int isExist = -1;
        for(int i=0; i<idx-1; i++){
            if(v[i]==num){
                isExist=i;
                break;
            }
        }
        if (isExist==-1)
        {
            v[idx]=num;
        }
        else
        {
            cout<<isExist<<"\n";
            break;
        }
    }
    return 0;
}
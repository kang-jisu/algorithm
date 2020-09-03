#include <iostream>
using namespace std;

int gcd(int a, int b)
{
    if (b > a)
    {
        int tmp = b;
        b = a;
        a = tmp;
    }
    int result = 1;
    for (int i = b; i >= 1; i--)
    {
        if (a % i == 0 && b % i == 0)
        {
            result = i;
            break;
        }
    }
    return result;
}
int lcm ( int a, int b){
    return (int)(a*b)/gcd(a,b);
}
int main()
{
    int n;
    cin>>n;
    for(int i=0; i<n; i++){
    int a, b;
    cin >> a >> b;
    cout << lcm(a,b)<<"\n";
    }
    return 0;
}
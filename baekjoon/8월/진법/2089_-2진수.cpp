#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int main()
{
    long long n;
    cin >> n;
    string s = "";
    if (n == 0)
    {
        cout << "0\n";
        return 0;
    }
    while (n != 1)
    {
        //나눠질때
        if (n % -2 == 0)
        {
            s += '0';
            n = n / -2;
        }
        else
        {
            if (n < 0)
            { //n==음수면
                n--;
                s += '1';
                n = n / -2;
            }
            else
            {
                s += '1';
                n = n / -2;
            }
        }
    }
    s += '1';

    for (int i = s.length() - 1; i >= 0; i--)
    {
        cout << s[i];
    }
    cout << "\n";
    return 0;
}

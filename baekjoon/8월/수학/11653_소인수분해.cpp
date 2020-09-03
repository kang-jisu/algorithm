#include <iostream>
using namespace std;

int main()
{

    int n;
    cin >> n;

    int p = 2;
    while (n > 1)
    {
        if (n % p == 0)
        {
            cout << p << "\n";
            n /= p;
        }
        else
            p++;
    }

    return 0;
}
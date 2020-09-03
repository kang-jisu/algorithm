#include <iostream>
using namespace std;

bool isPrime(int n)
{
    for (int i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
        {
            return false;
        }
    }
    return true;
}

int main()
{

    int n;
    cin >> n;
    int cnt = 0;
    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        if (a == 1)
            continue;
        if (isPrime(a))
            cnt++;
    }
    cout << cnt << "\n";
    return 0;
}
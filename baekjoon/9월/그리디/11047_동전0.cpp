#include <iostream>
using namespace std;

int main()
{
    int n;
    long long k;
    cin >> n >> k;
    int coin[10];
    for (int i = 0; i < n; i++)
    {
        cin >> coin[i];
    }

    int cnt = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        if (coin[i] <= k)
        {
            cnt += k / coin[i];
            k = k % coin[i];
        }
    }
    cout << cnt << "\n";
    return 0;
}
#include <iostream>
using namespace std;

int main()
{
    int n, m, k;
    cin >> n >> m >> k;

    int team = 0;
    if (n / 2 >= m)
    {
        team += m;
        n = n - m * 2;
        m = 0;
    }
    else
    {
        team += n / 2;
        m = m - (n / 2);
        n = 0;
    }
    if (n + m >= k)
        cout << team << "\n";
    else
    {
        k -= m;
        k -= n;
        if (k > 0)
        {
            int minus;
            if (k % 3 == 0)
                minus = k / 3;
            else
                minus = k / 3 + 1;
            team -= minus;
            cout << team << "\n";
        }
    }

    return 0;
}

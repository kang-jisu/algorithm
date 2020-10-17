#include <iostream>
using namespace std;

int T[16];
int D[16];
int P[16];
int main()
{
    std::ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> T[i] >> P[i];
    }

    for (int i = n; i >= 1; i--)
    {
        if (T[i] + i - 1 > n)
            D[i] = D[i + 1]; // 지금까지의 최대 이익 계산해줘야함
        else
        {
            D[i] = D[i + 1];
            if (D[i] < D[i + T[i]] + P[i])
                D[i] = D[i + T[i]] + P[i];
        }
    }

    int max = 0;
    for (int i = 1; i <= n; i++)
    {
        if (D[i] > max)
            max = D[i];
    }
    cout << max << "\n";

    return 0;
}
#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int A[102][102];
int G[102][102];
int cnt[6];
int n;
int diff = 10000000;
void cal(int x, int y, int d1, int d2)
{
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            G[i][j] = 0;
        }
    }
    for (int i = 1; i <= 5; i++)
    {
        cnt[i] = 0;
    }

    for (int i = 0; i <= d1; i++)
    {
        G[x + i][y - i] = 5;
        G[x + d2 + i][y + d2 - i] = 5;
    }
    for (int i = 0; i <= d2; i++)
    {
        G[x + i][y + i] = 5;
        G[x + d1 + i][y - d1 + i] = 5;
    }

    // for (int i = 1; i <= n; i++)
    // {
    //     for (int j = 1; j <= n; j++)
    //     {
    //         cout << G[i][j] << " ";
    //     }
    //     cout << "\n";
    // }
    // cout << "\n";
    for (int i = x; i <= x + d1 + d2; i++)
    {
        int s, e;
        for (int j = 1; j <= n; j++)
        {
            if (G[i][j] == 5)
            {
                s = j;
                break;
            }
        }
        for (int j = n; j >= 1; j--)
        {
            if (G[i][j] == 5)
            {
                e = j;
                break;
            }
        }
        for (int j = s + 1; j < e; j++)
        {
            G[i][j] = 5;
        }
    }

    for (int i = 1; i < x + d1; i++)
    {
        for (int j = 1; j <= y; j++)
        {
            if (G[i][j] != 5)
                G[i][j] = 1;
        }
    }
    for (int i = 1; i <= x + d2; i++)
    {
        for (int j = y + 1; j <= n; j++)
        {
            if (G[i][j] != 5)
                G[i][j] = 2;
        }
    }

    for (int i = x + d1; i <= n; i++)
    {
        for (int j = 1; j < y - d1 + d2; j++)
        {
            if (G[i][j] != 5)
                G[i][j] = 3;
        }
    }

    for (int i = x + d2 + 1; i <= n; i++)
    {
        for (int j = y - d1 + d2; j <= n; j++)
        {
            if (G[i][j] != 5)
                G[i][j] = 4;
        }
    }

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cnt[G[i][j]] += A[i][j];
        }
    }

    sort(cnt + 1, cnt + 6);

    if (diff > cnt[5] - cnt[1])
        diff = cnt[5] - cnt[1];
}

int main()
{

    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> A[i][j];
        }
    }
    for (int x = 1; x <= n - 2; x++)
    {
        for (int y = 2; y <= n - 1; y++)
        {
            for (int i = 2; i <= n - x; i++)
            {
                // cout << "====i:" << i << "===\n";

                for (int j = 1; j < y && i - j > 0; j++)
                {
                    if (y + i - j <= n)
                    {
                        // cout << x << " " << y << " " << j << " " << i - j << "\n";
                        cal(x, y, j, i - j);
                    }
                }
            }
        }
    }
    cout << diff << "\n";

    return 0;
}
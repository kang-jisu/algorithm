#include <iostream>

using namespace std;

int g[5][8];
int ng[5][8];
int d[5];
int sum[4] = {1, 2, 4, 8};
void CW(int num, int dir)
{
    // cout << num << "번째" << dir << "방향\n";
    if (dir == 0)
    {
        for (int i = 0; i < 8; i++)
        {
            ng[num][i] = g[num][i];
        }
    }
    else if (dir == 1)
    {
        int tmp = g[num][7];
        for (int i = 7; i > 0; i--)
        {
            ng[num][i] = g[num][i - 1];
        }
        ng[num][0] = tmp;
    }
    else
    {
        int tmp = g[num][0];
        for (int i = 1; i <= 7; i++)
        {
            ng[num][i - 1] = g[num][i];
        }
        ng[num][7] = tmp;
    }
    // for (int i = 0; i < 8; i++)
    // {
    //     cout << ng[num][i];
    // }
    // cout << "\n";
}
int main()
{

    int t;

    for (int i = 1; i <= 4; i++)
    {
        for (int j = 0; j < 8; j++)
        {
            char a;
            cin >> a;
            g[i][j] = a - '0';
        }
    }
    cin >> t;
    while (t--)
    {
        int num;
        int dir;
        cin >> num >> dir;
        CW(num, dir);
        if (num == 1)
        {
            d[1] = dir;
            for (int i = 2; i <= 4; i++)
            {
                if (g[i - 1][2] != g[i][6])
                {
                    d[i] = d[i - 1] * -1;
                    CW(i, d[i]);
                }
                else
                {
                    d[i] = 0;
                    CW(i, d[i]);
                }
            }
        }
        else if (num == 4)
        {
            d[4] = dir;
            for (int i = 3; i >= 1; i--)
            {
                if (g[i + 1][6] != g[i][2])
                {
                    d[i] = d[i + 1] * -1;
                    CW(i, d[i]);
                }
                else
                {
                    d[i] = 0;
                    CW(i, d[i]);
                }
            }
        }
        else
        {
            d[num] = dir;
            for (int i = num + 1; i <= 4; i++)
            {
                if (g[i - 1][2] != g[i][6])
                {
                    d[i] = d[i - 1] * -1;
                    CW(i, d[i]);
                }
                else
                {
                    d[i] = 0;
                    CW(i, d[i]);
                }
            }
            for (int i = num - 1; i >= 1; i--)
            {
                if (g[i + 1][6] != g[i][2])
                {
                    d[i] = d[i + 1] * -1;
                    CW(i, d[i]);
                }
                else
                {
                    d[i] = 0;
                    CW(i, d[i]);
                }
            }
        }
        // cout << "\n";
        for (int i = 1; i <= 4; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                // cout << ng[i][j];
                g[i][j] = ng[i][j];
            }
            // cout << "\n";
        }
    }
    int result = 0;
    for (int i = 1; i <= 4; i++)
    {
        if (g[i][0] == 1)
        {
            result += sum[i - 1];
        }
    }
    cout << result << "\n";
    return 0;
}
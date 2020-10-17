#include <iostream>
#include <cmath>
using namespace std;

int check[22];
int A[22][22];
int n;
int val = 2147483647;
void dfs(int cur, int idx)
{
    if (idx == n / 2)
    {
        int ssum = 0;
        int lsum = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (check[i] && check[j])
                {
                    ssum += A[i][j];
                    ssum += A[j][i];
                }
                else if (!check[i] && !check[j])
                {
                    lsum += A[i][j];
                    lsum += A[j][i];
                }
            }
        }
        if (abs(lsum - ssum) < val)
            val = abs(lsum - ssum);
    }
    else
    {
        for (int i = cur; i < n; i++)
        {
            if (check[i] == 0)
            {
                check[i] = 1;
                dfs(i, idx + 1);
                check[i] = 0;
            }
        }
    }
}
int main()
{

    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> A[i][j];
        }
    }

    dfs(1, 0);
    cout << val << "\n";
    return 0;
}
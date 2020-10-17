#include <iostream>
#include <algorithm>
using namespace std;

int n, m;
int P[501][501];
int V[501][501];

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int result;

int dfs(int i, int j, int depth)
{
    if (depth == 4)
        return P[i][j];
    int sum = 0;
    int A, B;
    for (int dir = 0; dir < 4; dir++)
    {
        A = i + dx[dir];
        B = j + dy[dir];
        if (A >= 0 && A < n && B >= 0 && B < m && V[A][B] == 0)
        {
            V[A][B] = 1;
            sum = max(sum, P[i][j] + dfs(A, B, depth + 1));
            V[A][B] = 0;
        }
    }
    return sum;
}
int ex(int i, int j)
{
    int res = 0;
    // ㅜ
    if (i >= 0 && i < n - 1 && j > 0 && j < m - 1)
    {
        res = max(res, P[i][j - 1] + P[i][j] + P[i][j + 1] + P[i + 1][j]);
    }
    // ㅏ
    if (i > 0 && i < n - 1 && j >= 0 && j < m - 1)
    {
        res = max(res, P[i - 1][j] + P[i][j] + P[i + 1][j] + P[i][j + 1]);
    }
    // ㅓ
    if (i > 0 && i < n - 1 && j > 0 && j < m)
    {
        res = max(res, P[i - 1][j] + P[i][j] + P[i + 1][j] + P[i][j - 1]);
    }
    // ㅗ
    if (i > 0 && i < n && j > 0 && j < m - 1)
    {
        res = max(res, P[i][j - 1] + P[i][j] + P[i][j + 1] + P[i - 1][j]);
    }
    return res;
}
int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> P[i][j];
        }
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            V[i][j] = 1;
            result = max(result, dfs(i, j, 1));
            result = max(result, ex(i, j));
            V[i][j] = 0;
        }
    }

    cout << result << "\n";
    return 0;
}

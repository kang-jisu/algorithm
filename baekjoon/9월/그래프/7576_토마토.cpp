#include <iostream>
#include <queue>
using namespace std;

int box[1001][1001];
int visit[1001][1001];
int xshift[4] = {-1, 0, 0, 1};
int yshift[4] = {0, -1, 1, 0};
int m, n;
queue<pair<int, int>> q;
void bfs(int x, int y)
{
    visit[x][y] = 1;
    int A, B;
    for (int i = 0; i < 4; i++)
    {
        A = x + xshift[i];
        B = y + yshift[i];
        if (A >= 0 && A <= n - 1 && B >= 0 && B <= m - 1)
        {
            if (visit[A][B] == 0 && box[A][B] == 0)
            {
                box[A][B] = box[x][y] + 1;
                q.push(make_pair(A, B));
            }
        }
    }
}
int main()
{

    cin >> m >> n;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> box[i][j];
            if (box[i][j] == 1)
                q.push(make_pair(i, j));
        }
    }

    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        bfs(x, y);
    }

    int max = 0;
    bool notAll = false;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (box[i][j] == 0)
            {
                notAll = true;
                break;
            }
            else if (box[i][j] > 0)
            {
                if (max < box[i][j])
                    max = box[i][j];
            }
        }
        if (notAll)
            break;
    }
    if (notAll)
        cout << "-1\n";
    else
        cout << max - 1 << "\n";
    return 0;
}

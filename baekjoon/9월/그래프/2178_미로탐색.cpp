#include <iostream>
#include <queue>
using namespace std;

int maze[101][101];
queue< pair<int, int> > q;
int xshift[4] = {-1, 0, 0, 1};
int yshift[4] = {0, -1, 1, 0};
int n, m;

void bfs(int x, int y)
{
    int A, B;
    for (int i = 0; i < 4; i++)
    {
        A = x + xshift[i];
        B = y + yshift[i];
        if (A >= 0 && A <= n - 1 && B >= 0 && B <= m - 1)
        {
            if (maze[A][B] == 1)
            {
                maze[A][B] = maze[x][y] + 1;
                q.push(make_pair(A, B));
            }
        }
    }
}
int main()
{

    cin >> n >> m;
    char tmp;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> tmp;
            if (tmp == '1')
                maze[i][j] = 1;
        }
    }
    q.push(make_pair(0, 0));
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        bfs(x, y);
    }
    cout << maze[n - 1][m - 1] << "\n";
    return 0;
}
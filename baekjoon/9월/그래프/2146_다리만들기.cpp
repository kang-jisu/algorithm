#include <iostream>
#include <queue>
using namespace std;

int map[101][101];
bool visit[101][101];
int n;
int cnt;
queue<pair<int, int>> q;
int xshift[4] = {-1, 0, 0, 1};
int yshift[4] = {0, -1, 1, 0};
void bfs()
{

    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        map[x][y] = cnt;
        q.pop();
        int A, B;
        for (int i = 0; i < 4; i++)
        {
            A = x + xshift[i];
            B = y + yshift[i];
            if (A >= 0 && A <= n - 1 && B >= 0 && B <= n - 1)
            {
                if (map[A][B] == 1 && visit[A][B] == 0)
                {
                    visit[A][B] = 1;
                    q.push(make_pair(A, B));
                }
            }
        }
    }
}

int find(int cnt)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (map[i][j] == cnt)
            {
                visit[i][j] = 1;
                // cout << cnt << ":" << i << ", " << j << "\n";
                q.push(make_pair(i, j));
            }
        }
    }
    int result = 0;
    while (!q.empty())
    {

        int qsize = q.size();
        for (int k = 0; k < qsize; k++)
        {
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            int A, B;
            for (int i = 0; i < 4; i++)
            {
                A = x + xshift[i];
                B = y + yshift[i];
                if (A >= 0 && A <= n - 1 && B >= 0 && B <= n - 1)
                {
                    if (map[A][B] != 0 && map[A][B] != cnt)
                    {
                        // cout << map[A][B] << "\n";
                        // cout << cnt << " " << A << "," << B << "\n";
                        return result;
                    }
                    else if (map[A][B] == 0 && visit[A][B] == 0)
                    {
                        visit[A][B] = 1;
                        q.push(make_pair(A, B));
                    }
                }
            }
        }
        result++;
    }
    return -1;
}

int main()
{

    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (map[i][j] == 1 && visit[i][j] == 0)
            {
                cnt++;
                q.push(make_pair(i, j));
                bfs();
            }
        }
    }

    int result = 10000000;
    for (int i = 1; i <= cnt; i++)
    {
        for (int x = 0; x < n; x++)
        {
            for (int y = 0; y < n; y++)
            {
                visit[x][y] = 0;
            }
        }
        while (!q.empty())
            q.pop();
        int fresult = find(i);
        if (fresult != 0 && fresult < result)
            result = fresult;
    }
    cout << result << "\n";

    return 0;
}
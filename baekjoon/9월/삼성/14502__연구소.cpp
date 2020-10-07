#include <iostream>
#include <queue>
using namespace std;

int n;
int m;
int arr[8][8];
int visit[8][8];
int maxCnt;
int copyArr[8][8];
int copyv[8][8];
queue<pair<int, int>> q;
queue<pair<int, int>> copyq;

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

void bfs()
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (arr[i][j] == 2)
            {
                q.push(make_pair(i, j));
            }
        }
    }
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        int A, B;
        for (int i = 0; i < 4; i++)
        {
            A = x + dx[i];
            B = y + dy[i];
            if (A >= 0 && A < n && B >= 0 && B < m && arr[A][B] == 0 && visit[A][B] == 0)
            {
                visit[A][B] = 1;
                q.push(make_pair(A, B));
            }
        }
    }
}
int main()
{

    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> arr[i][j];
            if (arr[i][j] != 0)
            {
                visit[i][j] = 1;
            }
        }
    }

    // bfs();

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            copyArr[i][j] = arr[i][j];
            copyv[i][j] = visit[i][j];
        }
    }

    for (int i = 0; i < n * m; i++)
    {
        int x = i / m;
        int y = i % m;
        if (arr[x][y] == 0)
        {
            for (int j = i + 1; j < n * m; j++)
            {
                int xx = j / m;
                int yy = j % m;
                if (arr[xx][yy] == 0)
                {
                    for (int k = j + 1; k < n * m; k++)
                    {
                        int xxx = k / m;
                        int yyy = k % m;
                        if (arr[xxx][yyy] == 0)
                        {

                            // cout << x << "," << y << "\n";
                            // cout << xx << "," << yy << "\n";
                            // cout << xxx << "," << yyy << "\n";
                            // cout << "\n";

                            arr[x][y] = 3;
                            arr[xx][yy] = 3;
                            arr[xxx][yyy] = 3;
                            visit[x][y] = 3;
                            visit[xx][yy] = 3;
                            visit[xxx][yyy] = 3;
                            bfs();
                            int cnt = 0;
                            for (int a = 0; a < n; a++)
                            {
                                for (int b = 0; b < m; b++)
                                {
                                    if (visit[a][b] == 0)
                                        cnt++;
                                }
                            }
                            if (maxCnt < cnt)
                            {
                                maxCnt = cnt;
                            }

                            for (int a = 0; a < n; a++)
                            {
                                for (int b = 0; b < m; b++)
                                {
                                    arr[a][b] = copyArr[a][b];
                                    visit[a][b] = copyv[a][b];
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    cout << maxCnt << "\n";

    return 0;
}

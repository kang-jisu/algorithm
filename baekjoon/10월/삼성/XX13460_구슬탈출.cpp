#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

// 강의보고하긴했는데 이해 잘 안됨.. 흑흑
struct info
{
    int rx, ry, bx, by, count;
};
info start;
char board[11][11];

// int result = 20000000;
int bfs()
{

    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};

    int visit[11][11][11][11] = {
        0,
    };
    visit[start.rx][start.ry][start.bx][start.by] = 1;

    queue<info> q;
    q.push(start);
    int res = -1;
    while (!q.empty())
    {
        info top = q.front();
        q.pop();
        if (top.count > 10)
            break;
        if (board[top.rx][top.ry] == 'O' && board[top.bx][top.by] != 'O')
        {
            res = top.count;
            break;
        }
        for (int dir = 0; dir < 4; dir++)
        {

            int nrx = top.rx;
            int nry = top.ry;
            int nbx = top.bx;
            int nby = top.by;
            while (1)
            {
                if (board[nrx][nry] != '#' && board[nrx][nry] != 'O')
                {
                    nrx += dx[dir];
                    nry += dy[dir];
                }
                else
                {
                    if (board[nrx][nry] == '#')
                    {
                        nrx -= dx[dir];
                        nry -= dy[dir];
                    }
                    break;
                }
            }
            while (1)
            {
                if (board[nbx][nby] != '#' && board[nbx][nby] != 'O')
                {
                    nbx += dx[dir];
                    nby += dy[dir];
                }
                else
                {
                    if (board[nbx][nby] == '#')
                    {
                        nbx -= dx[dir];
                        nby -= dy[dir];
                    }
                    break;
                }
            }

            if (nrx == nbx && nry == nby)
            {
                if (board[nrx][nry] != 'O')
                {
                    int rlen = abs(top.rx - nrx) + abs(top.ry - nry);
                    int blen = abs(top.bx - nbx) + abs(top.by - nby);
                    if (rlen > blen)
                    {
                        nrx -= dx[dir];
                        nry -= dy[dir];
                    }
                    else
                    {
                        nbx -= dx[dir];
                        nby -= dy[dir];
                    }
                }
            }
            if (visit[nrx][nry][nbx][nby] == 0)
            {
                visit[nrx][nry][nbx][nby] = 1;
                info next;
                next.rx = nrx;
                next.ry = nry;
                next.bx = nbx;
                next.by = nby;
                next.count = top.count + 1;
                q.push(next);
            }
        }
    }
    return res;
}
int main()
{
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> board[i][j];
            if (board[i][j] == 'R')
            {
                start.rx = i;
                start.ry = j;
            }
            if (board[i][j] == 'B')

            {
                start.bx = i;
                start.by = j;
            }
        }
    }
    start.count = 0;
    int result = bfs();
    cout << result << "\n";
    return 0;
}

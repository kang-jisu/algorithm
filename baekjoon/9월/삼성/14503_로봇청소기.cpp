#include <iostream>
using namespace std;

// 입력받는 d와 dx 방향 왼쪽으로 회전하는 순서가 달라서 틀렸음

int A[51][51];
int V[51][51];
int cleannum = 0;
int n, m;
int cnt = 0;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

int bx[] = {1, 0, -1, 0};
int by[] = {0, 1, 0, -1};
int dd[] = {0, 3, 2, 1};
void clean(int r, int c, int d)
{

    if (V[r][c] != 1)
        cnt++;
    V[r][c] = 1; // 현재 있는 칸 청소;
    int dir = d;
    bool any = false;

    for (int i = 0; i < 4; i++)
    {
        dir = (dir + 1) % 4;
        int x = r + dx[dir];
        int y = c + dy[dir];
        if (x >= 0 && x < n && y >= 0 && y < m && A[x][y] == 0 && V[x][y] == 0)
        {
            clean(x, y, dir);
            any = true;
            break;
        }
        if (dir == d)
        {
            break;
        }
    }
    if (any == false)
    {
        int x = r + bx[d];
        int y = c + by[d];
        if (x >= 0 && x < n && y >= 0 && y < m && A[x][y] == 0)
        {
            clean(x, y, dir);
        }
    }
}
int main()
{

    cin >> n >> m;
    int r, c, d;
    cin >> r >> c >> d;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> A[i][j];
            if (A[i][j] == 1)
                V[i][j] = -1;
        }
    }

    clean(r, c, dd[d]);
    cout << cnt << "\n";

    return 0;
}
#include <iostream>
using namespace std;

int NS[4];
// 남, 북 방향으로 움직였을 때 shift저장값 1.5.6.2위치
// 남쪽으로움직이면 ->로 shfit. 북쪽으로움직이면 <-
int EW[4];
// 서, 동으로 움직였을때 1.4.6.3 위치
// 서쪽 <- 동동쪽 -> shift

// shift하고 6번에 A[a][b]값 저장하고 안움직인부분에 1, 6 업데이트해줌
// 그러고 1번자리 출력

int A[21][21];
int n, m, x, y, k;

int dx[] = {0, 0, -1, 1}; // 동 서 북 남
int dy[] = {1, -1, 0, 0};

void go(int d)
{
    int a, b;
    a = x + dx[d];
    b = y + dy[d];
    if (a >= 0 && a < n && b >= 0 && b < m)
    {
        //범위를 벗어나지 않을때만 계산, 출력

        int val = A[a][b];
        x = a;
        y = b;
        if (d <= 1)
        {
            if (d == 0)
            { // 동쪽이동. 값 오른쪽으로 shift
                int zidx = EW[0];
                for (int i = 1; i <= 3; i++)
                {
                    EW[i - 1] = EW[i];
                }
                EW[3] = zidx;
            }
            else if (d == 1)
            { // 서쪽이동 값 왼쪽으로 shift
                int tidx = EW[3];
                for (int i = 3; i >= 1; i--)
                {
                    EW[i] = EW[i - 1];
                }
                EW[0] = tidx;
            }
            // 지도 값이 0이면 주사위 6번자리 넣어주고
            // 0이아니면 지도를 0으로만들고 그 값 주사위 6번에 주기.
            // 그다음 1, 6번자리 업데이트
            if (val == 0)
            {
                A[x][y] = EW[2];
            }
            else
            {
                A[x][y] = 0;
                EW[2] = val;
            }
            NS[0] = EW[0];
            NS[2] = EW[2];
        }
        else
        {
            if (d == 2)
            { // 북쪽이동 값 왼쪽 shift
                int tidx = NS[3];
                for (int i = 3; i >= 1; i--)
                {
                    NS[i] = NS[i - 1];
                }
                NS[0] = tidx;
            }
            else
            { //남
                int zidx = NS[0];
                for (int i = 1; i <= 3; i++)
                {
                    NS[i - 1] = NS[i];
                }
                NS[3] = zidx;
            }
            if (val == 0)
            {
                A[x][y] = NS[2];
            }
            else
            {
                A[x][y] = 0;
                NS[2] = val;
            }
            EW[0] = NS[0];
            EW[2] = NS[2];
        }

        cout << NS[0] << "\n";
    }
    else
        return;
}
int main()
{

    cin >> n >> m >> x >> y >> k;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> A[i][j];
        }
    }
    for (int i = 0; i < k; i++)
    {
        int dir;
        cin >> dir;
        go(dir - 1);
    }

    return 0;
}
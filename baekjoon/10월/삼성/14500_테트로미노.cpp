#include <iostream>
using namespace std;
int n, m;
int P[501][501];
pair<int, int> T[7][4];

int result;

void init()
{
    T[0][1] = make_pair(0, 1);
    T[0][2] = make_pair(0, 2);
    T[0][3] = make_pair(0, 3);

    T[1][1] = make_pair(0, 1);
    T[1][2] = make_pair(1, 0);
    T[1][3] = make_pair(1, 1);

    T[2][1] = make_pair(1, 0);
    T[2][2] = make_pair(2, 0);
    T[2][3] = make_pair(2, 1);

    T[3][1] = make_pair(1, 0);
    T[3][2] = make_pair(1, 1);
    T[3][3] = make_pair(2, 1);

    T[4][1] = make_pair(0, 1);
    T[4][2] = make_pair(0, 2);
    T[4][3] = make_pair(1, 1);

    T[5][0] = make_pair(0, 1);
    T[5][1] = make_pair(1, 1);
    T[5][2] = make_pair(2, 0);
    T[5][3] = make_pair(2, 1);

    T[6][0] = make_pair(0, 1);
    T[6][1] = make_pair(1, 0);
    T[6][2] = make_pair(1, 1);
    T[6][3] = make_pair(2, 0);
}
void rotate(int ori[][501], int N, int M)
{
    int tmp[501][501] = {
        0,
    };
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            tmp[j][N - i - 1] = ori[i][j];
        }
    }
    if (n > m)
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                P[i][j] = tmp[i][j];
            }
        }
    }
    else
    {
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < M; j++)
            {
                P[i][j] = tmp[i][j];
            }
        }
    }
    n = M;
    m = N;
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
    init();
    // for (int t = 0; t < 5; t++)
    // {
    //     int idx = 0;
    //     for (int i = 0; i < 4; i++)
    //     {
    //         for (int j = 0; j < 4; j++)
    //         {
    //             if (T[t][idx].first == i && T[t][idx].second == j)
    //             {
    //                 idx++;
    //                 cout << "1 ";
    //             }
    //             else
    //                 cout << "0 ";
    //         }
    //         cout << "\n";
    //     }
    //     cout << "\n";
    // }

    for (int t = 0; t < 4; t++)
    {
        rotate(P, n, m);
        // --- 모양
        if (t <= 1)
        {

            for (int i = 0; i <= n - 1; i++)
            {
                for (int j = 0; j <= m - 4; j++)
                {
                    int sum = 0;
                    for (int k = 0; k < 4; k++)
                    {
                        int x = T[0][k].first;
                        int y = T[0][k].second;
                        sum += P[i + x][j + y];
                    }
                    if (sum > result)
                        result = sum;
                }
            }
        }
        // 정사각형모양
        if (t == 0)
        {
            for (int i = 0; i <= n - 2; i++)
            {
                for (int j = 0; j <= m - 2; j++)
                {
                    int sum = 0;
                    for (int k = 0; k < 4; k++)
                    {
                        int x = T[1][k].first;
                        int y = T[1][k].second;
                        sum += P[i + x][j + y];
                    }
                    if (sum > result)
                        result = sum;
                }
            }
        }
        // L모양

        for (int i = 0; i <= n - 3; i++)
        {
            for (int j = 0; j <= m - 2; j++)
            {
                int sum = 0;
                for (int k = 0; k < 4; k++)
                {
                    int x = T[2][k].first;
                    int y = T[2][k].second;
                    sum += P[i + x][j + y];
                }
                if (sum > result)
                    result = sum;
            }
        }
        // 지그재그모양
        for (int i = 0; i <= n - 3; i++)
        {
            for (int j = 0; j < m - 2; j++)
            {
                int sum = 0;
                for (int k = 0; k < 4; k++)
                {
                    int x = T[3][k].first;
                    int y = T[3][k].second;
                    sum += P[i + x][j + y];
                }
                if (sum > result)
                    result = sum;
            }
        }

        // L모양반대

        for (int i = 0; i <= n - 3; i++)
        {
            for (int j = 0; j <= m - 2; j++)
            {
                int sum = 0;
                for (int k = 0; k < 4; k++)
                {
                    int x = T[5][k].first;
                    int y = T[5][k].second;
                    sum += P[i + x][j + y];
                }
                if (sum > result)
                    result = sum;
            }
        }
        // 지그재그모양
        for (int i = 0; i <= n - 3; i++)
        {
            for (int j = 0; j < m - 2; j++)
            {
                int sum = 0;
                for (int k = 0; k < 4; k++)
                {
                    int x = T[6][k].first;
                    int y = T[6][k].second;
                    sum += P[i + x][j + y];
                }
                if (sum > result)
                    result = sum;
            }
        }
        // ㅜ 모양
        for (int i = 0; i <= n - 2; i++)
        {
            for (int j = 0; j <= m - 3; j++)
            {
                int sum = 0;
                for (int k = 0; k < 4; k++)
                {
                    int x = T[4][k].first;
                    int y = T[4][k].second;
                    sum += P[i + x][j + y];
                }
                if (sum > result)
                    result = sum;
            }
        }
    }
    cout << result << "\n";
    return 0;
}
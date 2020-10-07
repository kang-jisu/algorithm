#include <iostream>
using namespace std;

int r, c;
int air;
int A[52][52];
int D[52][52];

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int main()
{
    int t;
    cin >> r >> c >> t;

    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= c; j++)
        {
            cin >> A[i][j];
        }
    }

    for (int i = 1; i <= r; i++)
    {
        if (A[i][1] == -1)
        {
            air = i;
            break;
        }
    }
    while (t--)
    {
        for (int i = 1; i <= r; i++)
        {
            for (int j = 1; j <= c; j++)
            {
                D[i][j] = 0;
            }
        }

        for (int i = 1; i <= r; i++)
        {
            for (int j = 1; j <= c; j++)
            {
                if (A[i][j] >= 5)
                {
                    int a, b;
                    int cnt = 0;
                    for (int k = 0; k < 4; k++)
                    {
                        a = i + dx[k];
                        b = j + dy[k];
                        if (a >= 1 && a <= r && b >= 1 && b <= c && A[a][b] != -1)
                        {
                            D[a][b] += A[i][j] / 5;
                            cnt++;
                        }
                    }
                    D[i][j] -= cnt * (A[i][j] / 5);
                }
            }
        }
        for (int i = 1; i <= r; i++)
        {
            for (int j = 1; j <= c; j++)
            {
                A[i][j] += D[i][j];
            }
        }
        for (int i = 1; i <= r; i++)
        {
            for (int j = 1; j <= c; j++)
            {
                D[i][j] = A[i][j];
            }
        }
        for (int i = 2; i < c; i++)
        {
            A[air][i + 1] = D[air][i];
            A[air + 1][i + 1] = D[air + 1][i];
        }
        for (int i = air; i > 1; i--)
        {
            A[i - 1][c] = D[i][c];
        }
        for (int i = air + 1; i < r; i++)
        {
            A[i + 1][c] = D[i][c];
        }

        for (int i = c; i > 1; i--)
        {
            A[1][i - 1] = D[1][i];
            A[r][i - 1] = D[r][i];
        }
        for (int i = 2; i <= air - 1; i++)
        {
            A[i][1] = D[i - 1][1];
        }
        for (int i = r-1; i >= air + 2; i--)
        {
            A[i][1] = D[i + 1][1];
        }
        A[air][2] = 0;
        A[air + 1][2] = 0;
    }

    int sum = 0;
    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= c; j++)
        {
            // cout << A[i][j] << " ";
            if (A[i][j] != -1)
                sum += A[i][j];
        }
        // cout << "\n";
    }
    cout << sum << "\n";

    return 0;
}
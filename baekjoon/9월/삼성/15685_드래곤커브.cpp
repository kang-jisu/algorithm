#include <iostream>
#include <stack>
#include <cmath>
#include <queue>
using namespace std;

int V[101][101];
int dx[] = {0, -1, 0, 1};
int dy[] = {1, 0, -1, 0};

int D[1050];

int main()
{

    int n;
    cin >> n;

    while (n--)
    {
        int x, y, d, g;
        cin >> y >> x >> d >> g;

        D[0] = d;

        for (int i = 1; i <= g; i++)
        {
            int k = pow(2, i) - 1;
            for (int j = 0; j <= k / 2; j++)
            {
                D[k - j] = (D[j] + 1) % 4;
            }
        }
        V[x][y] = 1;
        for (int i = 0; i <= pow(2, g) - 1; i++)
        {
            x = x + dx[D[i]];
            y = y + dy[D[i]];
            if (x >= 0 && x <= 100 && y >= 0 && y <= 100)
                V[x][y] = 1;
        }
    }

    int cnt = 0;
    for (int i = 0; i < 100; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            if (V[i][j] != 0 && V[i + 1][j] != 0 && V[i][j + 1] != 0 && V[i + 1][j + 1] != 0)
                cnt++;
        }
    }
    cout << cnt << "\n";
    return 0;
}